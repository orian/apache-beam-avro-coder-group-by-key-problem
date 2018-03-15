package com.datainq.playground;

import com.google.common.collect.Lists;
import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.testing.ValidatesRunner;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TimestampedValue;
import org.joda.time.Instant;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;

public class TestSdtDevTransform {
    private static final ArrayList<String> WORDS = Lists.newArrayList("raz", "dwa");
    @Rule
    public final transient TestPipeline pipeline = TestPipeline.create();

    @Test
    @Category(ValidatesRunner.class)
    public void StdDevZero() throws Exception {
        ArrayList<TimestampedValue<KV<String, Double>>> itms = Lists.newArrayList(
                TimestampedValue.of(KV.of("Olsztyn", 5.), new Instant(1520602080000L)),
                TimestampedValue.of(KV.of("Olsztyn", 5.), new Instant(1520602081000L)));

        PCollection<KV<String, Double>> input = pipeline.apply("Create", Create.timestamped(itms));
        PAssert.that(input.apply("StdDev", new StdDevTransform<>())).containsInAnyOrder(
                KV.of("Olsztyn", 0.));
        pipeline.run();
    }

    @Test
    @Category(ValidatesRunner.class)
    public void StdDev() throws Exception {
        ArrayList<KV<String, Double>> itms = Lists.newArrayList(
                KV.of("Olsztyn", 4.), KV.of("Olsztyn", 6.));

        PCollection<KV<String, Double>> input = pipeline.apply("Create", Create.of(itms));
        PAssert.that(input.apply("StdDev", new StdDevTransform<>())).containsInAnyOrder(
                KV.of("Olsztyn", 1.4142135623730951));
        pipeline.run().waitUntilFinish();
    }

    @DefaultCoder(AvroCoder.class)
    static class Key {
        String part0;

        static Key create(String p) {
            Key key = new Key();
            key.part0 = p;
            return key;
        }
    }

    @Test
    @Category(ValidatesRunner.class)
    public void StdDevAvro() throws Exception {
        ArrayList<KV<Key, Double>> itms = Lists.newArrayList(
                KV.of(Key.create("Olsztyn"), 4.),
                KV.of(Key.create("Olsztyn"), 6.));

        PCollection<KV<Key, Double>> input = pipeline.apply("Create", Create.of(itms));
        PAssert.that(input.apply("StdDev", new StdDevTransform<>())).containsInAnyOrder(
                KV.of(Key.create("Olsztyn"), 1.4142135623730951));
        pipeline.run().waitUntilFinish();
    }

    @Test
    @Category(ValidatesRunner.class)
    public void StdDevPb() throws Exception {
        ArrayList<KV<KeyOuterClass.Key, Double>> itms = Lists.newArrayList(
                KV.of(KeyOuterClass.Key.newBuilder().setProp0("Olsztyn").build(), 4.),
                KV.of(KeyOuterClass.Key.newBuilder().setProp0("Olsztyn").build(), 6.));

        PCollection<KV<KeyOuterClass.Key, Double>> input = pipeline.apply("Create", Create.of(itms));
        PAssert.that(input.apply("StdDev", new StdDevTransform<>())).containsInAnyOrder(
                KV.of(KeyOuterClass.Key.newBuilder().setProp0("Olsztyn").build(), 1.4142135623730951));
        pipeline.run().waitUntilFinish();
    }

}