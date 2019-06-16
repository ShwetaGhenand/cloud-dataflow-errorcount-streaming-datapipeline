package com.example.subprocess;

import org.apache.beam.repackaged.beam_sdks_java_core.org.apache.commons.lang3.StringUtils;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.common.Constants;

public class ExtractErrorFn extends DoFn<String, String> {

	private static final Logger LOG = LoggerFactory.getLogger(ExtractErrorFn.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ProcessElement
	public void processElement(ProcessContext c) {
		LOG.debug("extract error transform is started");
		try {
			String[] lines = StringUtils.split(c.element(), Constants.TOKENIZER_PATTERN);

			for (String line : lines) {
				if (line.contains(Constants.ERROR_PATTERN)) {
					c.output(line);
				}
			}
		} catch (Exception e) {
			LOG.error("ExtractError transform failed:" + e.getLocalizedMessage(), e);
		}
		LOG.debug("extract error transform is finished");
	}
}
