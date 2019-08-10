package com.example.subprocess;

import org.apache.beam.sdk.transforms.SimpleFunction;

import com.example.common.Constants;

public class FormatAsTextFn extends SimpleFunction<Long, String> {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public String apply(Long input) {
    return Constants.ERROR_COUNT_MSG + input.toString();

  }
}
