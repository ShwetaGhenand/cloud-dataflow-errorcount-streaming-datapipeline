package com.example.subprocess;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertPubsubMsgToString extends DoFn<PubsubMessage, String> {
  private static final Logger LOG = LoggerFactory.getLogger(ConvertPubsubMsgToString.class);
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @ProcessElement
  public void processElement(ProcessContext c) {
    LOG.debug("PubSubMessage to String conversion is started");
    PubsubMessage mesage = c.element();
    String payload = new String(mesage.getPayload());
    c.output(payload);
    LOG.debug("PubSubMessage to String conversion is finished");
  }
}
