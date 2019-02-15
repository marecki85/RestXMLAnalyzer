package com.marek.rest.service;

import com.marek.rest.entity.AnalyzedContent;

public interface AnalyzerService {
	AnalyzedContent getAnalyzedContent(String url);
}
