package com.example.config;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

public interface MyOptions extends PipelineOptions {
  @Description("Topic name command line argument.")
  @Default.String("projects/lucky-lead-240003/topics/my-pubsub")
  ValueProvider<String> getTopicName();

  void setTopicName(ValueProvider<String> topicName);
}
