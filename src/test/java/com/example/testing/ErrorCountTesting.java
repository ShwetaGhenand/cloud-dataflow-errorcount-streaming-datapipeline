package com.example.testing;

import static org.junit.Assert.assertEquals;

import org.apache.beam.sdk.testing.NeedsRunner;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.example.common.Constants;
import com.example.subprocess.ExtractErrorFn;
import com.example.subprocess.FormatAsTextFn;
import com.example.util.DummyData;

public class ErrorCountTesting {

	@Rule
	public final transient TestPipeline testPipeLine = TestPipeline.create();

	@Test
	@Category(NeedsRunner.class)
	public void extractErrorFnTesting() throws Exception {
		PCollection<String> logs = testPipeLine.apply(Create.of(DummyData.LOG_DATA));
		PCollection<String> output = logs.apply(ParDo.of(new ExtractErrorFn()));
		PAssert.that(output).containsInAnyOrder(DummyData.Error_ARRAY);

		// Run the pipeline.
		testPipeLine.run();
	}

	@Test
	public void formatAsTextFnTesting() throws Exception {
		FormatAsTextFn formatAsTextFnObj = new FormatAsTextFn();

		String result = formatAsTextFnObj.apply(DummyData.value);

		assertEquals(result, Constants.ERROR_COUNT_MSG + DummyData.value);

	}
}
