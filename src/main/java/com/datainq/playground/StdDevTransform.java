package com.datainq.playground;

import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionView;

import java.util.Map;

public class StdDevTransform<KeyT>
        extends PTransform<
        PCollection<KV<KeyT, Double>>,
        PCollection<KV<KeyT, Double>>> {

    @Override
    public PCollection<KV<KeyT, Double>> expand(PCollection<KV<KeyT, Double>> input) {
        final PCollectionView<Map<KeyT, Double>> meanView = input.apply("ComputeMean", Mean.perKey())
                .apply("MeanAsView", View.asMap());
        final PCollectionView<Map<KeyT, Long>> countView = input
                .apply("Count", Count.perKey()).apply(View.asMap());

        return input.apply("SubMean", ParDo.of(new DoFn<KV<KeyT, Double>, KV<KeyT, Double>>() {
            @ProcessElement
            public void processElement(ProcessContext ctx) {
                KV<KeyT, Double> el = ctx.element();
                Double mean = ctx.sideInput(meanView).get(el.getKey());
                if (mean != null) {
                    ctx.output(KV.of(el.getKey(), Math.pow(el.getValue() - mean, 2.)));
                } else {
                    ctx.output(KV.of(el.getKey(), 0.));
                }
            }}).withSideInputs(meanView))
                .apply(Sum.doublesPerKey())
                .apply("DivByCount", ParDo.of(new DoFn<KV<KeyT, Double>, KV<KeyT, Double>>() {
                    @ProcessElement
                    public void processElement(ProcessContext ctx) {
                        KV<KeyT, Double> el = ctx.element();
                        Long count = ctx.sideInput(countView).get(el.getKey());
                        if (count != null && count > 1) {
                            double stdDev = Math.sqrt(el.getValue() / (double) (count - 1));
                            ctx.output(KV.of(el.getKey(), stdDev));
                        } else {
                            ctx.output(KV.of(el.getKey(), 0.));
                        }
                    }
                }).withSideInputs(countView));
    }
}