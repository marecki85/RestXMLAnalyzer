package com.marek.rest.service.standard;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marek.rest.entity.AnalyzedContent;
import com.marek.rest.service.AnalyzerService;
import com.marek.rest.service.RowXMLParser;

@Service
public class StandardAnalyzerService implements AnalyzerService {

	@Autowired
	RowXMLParser rowXMLparser;

	@Override
	public AnalyzedContent getAnalyzedContent(String url) {
		final AnalyzerStats analyzerStats = new AnalyzerStats();

		rowXMLparser.parse(url, (row) -> {
			analyzerStats.updateFirstPost(row);
			analyzerStats.updateLastPost(row);
			analyzerStats.updateNumberOfTotalPosts(row);
			analyzerStats.updateNumberOfTotalAcceptedPosts(row);
			analyzerStats.updateAverageScore(row);
		});
		return new AnalyzedContent(LocalDateTime.now(), analyzerStats.getAnalyzedAttributes());
	}
}
