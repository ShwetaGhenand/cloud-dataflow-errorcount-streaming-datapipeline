package com.example.test;

import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.ValueProvider.StaticValueProvider;
import org.apache.beam.sdk.testing.NeedsRunner;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.example.StarterPipeline;
import com.example.config.MyOptions;
import com.example.subprocess.ExtractErrorFn;
import com.example.subprocess.FormatAsTextFn;
import com.example.util.DummyData;
import com.example.util.OutputChecker;

public class StarterPieplineEndToEndTest {

  @Rule
  public final transient TestPipeline testPipeLine = TestPipeline.create();

  @Test
  @Category(NeedsRunner.class)
  public void testErrorCount() {

    PCollection<String> input = testPipeLine.apply(Create.of(DummyData.LOG_DATA));

    PCollection<String> error = input.apply(ParDo.of(new ExtractErrorFn()));
    PCollection<Long> count = error.apply(Count.globally());
    PCollection<String> output = count.apply(MapElements.via(new FormatAsTextFn()));

    // PAssert.that(output).containsInAnyOrder(DummyData.OUTPUT_ARRAY);
    PAssert.that(output).satisfies(new OutputChecker());

    // Run the pipeline.
    testPipeLine.run();

  }

  @Test
  @Category(NeedsRunner.class)
  public void testErrorCountFn() throws Exception {

    PipelineOptionsFactory.register(MyOptions.class);
    MyOptions options = PipelineOptionsFactory.as(MyOptions.class);
    options.setTopicName(StaticValueProvider.of(DummyData.TOPIC_NAME));

    StarterPipeline.runErrorCount(options);

  }
}
