# SideInput/Grouping problem

This is an example of values not matching when using an AvroCoder.

## Scenario
We have N values of `(timestamp, key, value)`. We want to
compute standard deviation for each key. We use sample standard deviation
formula (https://en.wikipedia.org/wiki/Standard_deviation):

![sampled standard deviation](stddev.svg)

What we expect to get is a list of values: `(key, std dev)` for a given time 
window (or global). 

## Implementation.

1. Compute mean and count per key.
2. Compute (x-mean)^2 per value.
3. Sum values per key.
4. Divide sum by count and compute square root of it.

The implementation could be found in [StdDevTransform](./src/main/java/com/datainq/playground/StdDevTransform.java).


## Problem
When using a custom class with AvroCoder as a key the side input values
are not grouped properly. There is no side input value available.

One should check the tests [TestStdDevTransform](./src/test/java/com/datainq/playground/StdDevTransform.java).
When run, it fails StdDevAvro test with a log:
```
java.lang.AssertionError: StdDev/DivByCount/ParMultiDo(Anonymous).output: 
Expected: iterable over [<KV{com.datainq.playground.TestSdtDevTransform$Key@4806d18a, 1.4142135623730951}>] in any order
     but: Not matched: <KV{com.datainq.playground.TestSdtDevTransform$Key@10f2594b, 0.0}>

	at org.apache.beam.sdk.testing.PAssert$PAssertionSite.capture(PAssert.java:166)
	at org.apache.beam.sdk.testing.PAssert.that(PAssert.java:358)
	at org.apache.beam.sdk.testing.PAssert.that(PAssert.java:350)
	at com.datainq.playground.TestSdtDevTransform.StdDevAvro(TestSdtDevTransform.java:69)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.apache.beam.sdk.testing.TestPipeline$1.evaluate(TestPipeline.java:324)
	at org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
Caused by: java.lang.AssertionError: 
Expected: iterable over [<KV{com.datainq.playground.TestSdtDevTransform$Key@4806d18a, 1.4142135623730951}>] in any order
     but: Not matched: <KV{com.datainq.playground.TestSdtDevTransform$Key@10f2594b, 0.0}>
	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:20)
	at org.junit.Assert.assertThat(Assert.java:956)
	at org.junit.Assert.assertThat(Assert.java:923)
	at org.apache.beam.sdk.testing.PAssert$AssertContainsInAnyOrder.apply(PAssert.java:1302)
	at org.apache.beam.sdk.testing.PAssert$AssertContainsInAnyOrder.apply(PAssert.java:1281)
	at org.apache.beam.sdk.testing.PAssert$CheckRelationAgainstExpected.apply(PAssert.java:946)
	at org.apache.beam.sdk.testing.PAssert$CheckRelationAgainstExpected.apply(PAssert.java:926)
	at org.apache.beam.sdk.testing.PAssert.doChecks(PAssert.java:1230)
	at org.apache.beam.sdk.testing.PAssert$GroupedValuesCheckerDoFn.processElement(PAssert.java:1195)
	at org.apache.beam.sdk.testing.PAssert$GroupedValuesCheckerDoFn$DoFnInvoker.invokeProcessElement(Unknown Source)
	at org.apache.beam.runners.direct.repackaged.runners.core.SimpleDoFnRunner.invokeProcessElement(SimpleDoFnRunner.java:177)
	at org.apache.beam.runners.direct.repackaged.runners.core.SimpleDoFnRunner.processElement(SimpleDoFnRunner.java:141)
	at org.apache.beam.runners.direct.repackaged.runners.core.SimplePushbackSideInputDoFnRunner.processElementInReadyWindows(SimplePushbackSideInputDoFnRunner.java:72)
	at org.apache.beam.runners.direct.ParDoEvaluator.processElement(ParDoEvaluator.java:179)
	at org.apache.beam.runners.direct.DoFnLifecycleManagerRemovingTransformEvaluator.processElement(DoFnLifecycleManagerRemovingTransformEvaluator.java:51)
	at org.apache.beam.runners.direct.DirectTransformExecutor.processElements(DirectTransformExecutor.java:161)
	at org.apache.beam.runners.direct.DirectTransformExecutor.run(DirectTransformExecutor.java:125)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
```

