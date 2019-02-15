package com.marek.rest.service.standard;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import com.marek.rest.entity.Row;
import com.marek.rest.util.Constants;

public class AnalyzerStats {

	private final Map<String, Object> analyzedAttributes = new LinkedHashMap<>();

	private LocalDateTime minDate;
	private LocalDateTime maxDate;
	private int scoreSum = 0;
	private int scoreNumber = 0;

	public void updateNumberOfTotalPosts(Row row) {
		Integer count = row.getId();
		Long currentCount = 0L;
		if (count != null) {
			currentCount = (Long) getAnalyzedAttributes().getOrDefault(Constants.TOTAL_POSTS, 0L) + 1;
			getAnalyzedAttributes().put(Constants.TOTAL_POSTS, currentCount);
		}
	}

	public void updateNumberOfTotalAcceptedPosts(Row row) {
		Integer count = row.getAcceptedAnswerId();
		Long currentCount = 0L;
		if (count != null) {
			currentCount = (Long) getAnalyzedAttributes().getOrDefault(Constants.TOTAL_ACCEPTED_POSTS, 0L) + 1;
			getAnalyzedAttributes().put(Constants.TOTAL_ACCEPTED_POSTS, currentCount);
		}
	}

	public void updateFirstPost(Row row) {
		LocalDateTime currentDate = row.getCreationDate();
		if (maxDate == null || currentDate != null && currentDate.isBefore(maxDate)) {
			maxDate = currentDate;
			getAnalyzedAttributes().put(Constants.FIRST_POST, maxDate);
		}
	}

	public void updateLastPost(Row row) {
		LocalDateTime currentDate = row.getCreationDate();
		if (minDate == null || currentDate != null && currentDate.isAfter(minDate)) {
			minDate = currentDate;
			getAnalyzedAttributes().put(Constants.LAST_POST, minDate);
		}
	}

	public void updateAverageScore(Row row) {
		Integer currentScore = row.getScore();
		if (currentScore != null) {
			scoreSum += currentScore;
			scoreNumber++;
			getAnalyzedAttributes().put(Constants.AVERAGE_SCORE, (float) scoreSum / scoreNumber);
		}
	}

	public Map<String, Object> getAnalyzedAttributes() {
		return analyzedAttributes;
	}

}
