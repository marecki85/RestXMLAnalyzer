package com.marek.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.marek.rest.entity.AnalyzedContent;
import com.marek.rest.entity.Request;
import com.marek.rest.service.standard.StandardAnalyzerService;
import com.marek.rest.util.Constants;

public class RestAnalyzerTest {

	@Mock
	private StandardAnalyzerService standardRowAnalyzeService;

	@InjectMocks
	private RestAnalyzer restAnalyzer;

	private AnalyzedContent analyzedContent;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		@SuppressWarnings("serial")
		Map<String, Object> details = new HashMap<String, Object>() {
			{
				put(Constants.FIRST_POST, LocalDateTime.of(2014, 12, 14, 12, 30));
				put(Constants.LAST_POST, LocalDateTime.of(2014, 12, 15, 12, 30));
				put(Constants.TOTAL_POSTS, 222);
				put(Constants.TOTAL_ACCEPTED_POSTS, 55);
				put(Constants.AVERAGE_SCORE, 2.23);
			}
		};
		analyzedContent = new AnalyzedContent(LocalDateTime.of(2014, 12, 13, 12, 30), details);
	}

	@Test
	public void getStandardAnalyzedContent() {
		when(standardRowAnalyzeService.getAnalyzedContent("uri")).thenReturn(analyzedContent);
		Request request = new Request();
		request.setUrl("uri");
		AnalyzedContent restAnalyzedContent = restAnalyzer.getStandardAnalyzedContent(request);
		assertEquals(LocalDateTime.of(2014, 12, 13, 12, 30), restAnalyzedContent.getAnalyseDate());
		assertEquals(LocalDateTime.of(2014, 12, 14, 12, 30),
				restAnalyzedContent.getDetails().get(Constants.FIRST_POST));
		assertEquals(LocalDateTime.of(2014, 12, 15, 12, 30),
				restAnalyzedContent.getDetails().get(Constants.LAST_POST));
		assertEquals(222, restAnalyzedContent.getDetails().get(Constants.TOTAL_POSTS));
		assertEquals(55, restAnalyzedContent.getDetails().get(Constants.TOTAL_ACCEPTED_POSTS));
		assertEquals(2.23, restAnalyzedContent.getDetails().get(Constants.AVERAGE_SCORE));
	}
}
