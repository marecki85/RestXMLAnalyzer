package com.marek.rest.entity;

import java.time.LocalDateTime;
import java.util.Map;

public class AnalyzedContent {

	private LocalDateTime analyseDate;
	private Map<String, Object> details;

	public AnalyzedContent(LocalDateTime analyseDate, Map<String, Object> details) {
		super();
		this.analyseDate = analyseDate;
		this.details = details;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public LocalDateTime getAnalyseDate() {
		return analyseDate;
	}
}
