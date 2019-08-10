package com.example.util;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.apache.beam.sdk.transforms.SerializableFunction;

public class OutputChecker implements SerializableFunction<Iterable<String>, Void> {

  /**
   * 
   */
  private static final long serialVersionUID = -6863392862498639121L;

  @Override
  public Void apply(Iterable<String> output) {
    Iterator<String> outputIterator = output.iterator();
    while (outputIterator.hasNext()) {
      assertEquals(outputIterator.next(), DummyData.ERROR_COUNT);
    }

    return null;
  }
}
