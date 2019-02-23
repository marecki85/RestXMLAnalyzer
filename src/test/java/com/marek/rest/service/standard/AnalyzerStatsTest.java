package com.marek.rest.service.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.marek.rest.entity.Row;
import com.marek.rest.util.Constants;

public class AnalyzerStatsTest {

	private AnalyzerStats analyzerStats;

	@Before
	public void setUp() {
		analyzerStats = new AnalyzerStats();
	}

	@Test
	public void updateAverageScoreOnOneRowTest() {
		analyzerStats.updateAverageScore(row1());
		assertEquals(50.0f, analyzerStats.getAnalyzedAttributes().get(Constants.AVERAGE_SCORE));
	}

	@Test
	public void updateAverageScoreInManyRowsTest() {
		analyzerStats.updateAverageScore(row1());
		analyzerStats.updateAverageScore(row2());
		analyzerStats.updateAverageScore(row3());
		analyzerStats.updateAverageScore(row4());
		assertEquals(60.0f, analyzerStats.getAnalyzedAttributes().get(Constants.AVERAGE_SCORE));
	}

	@Test
	public void updateAverageScoreOnOneRowWithNoAttributeTest() {
		analyzerStats.updateAverageScore(row4());
		assertNull(analyzerStats.getAnalyzedAttributes().get(Constants.AVERAGE_SCORE));
	}

	@Test
	public void updateFirstPostInOneRowTest() {
		analyzerStats.updateFirstPost(row2());
		assertEquals(LocalDateTime.of(1999, 12, 22, 12, 30),
				analyzerStats.getAnalyzedAttributes().get(Constants.FIRST_POST));
	}

	@Test
	public void updateFirstPostInManyRowsTest() {
		analyzerStats.updateFirstPost(row1());
		analyzerStats.updateFirstPost(row2());
		analyzerStats.updateFirstPost(row3());
		analyzerStats.updateFirstPost(row4());
		assertEquals(LocalDateTime.of(1833, 12, 13, 12, 30),
				analyzerStats.getAnalyzedAttributes().get(Constants.FIRST_POST));
	}

	@Test
	public void updateFirstPostInOneRowWithNoAttributeTest() {
		analyzerStats.updateFirstPost(row3());
		assertNull(analyzerStats.getAnalyzedAttributes().get(Constants.FIRST_POST));
	}

	@Test
	public void updatelastPostInOneRowTest() {
		analyzerStats.updateLastPost(row2());
		assertEquals(LocalDateTime.of(1999, 12, 22, 12, 30),
				analyzerStats.getAnalyzedAttributes().get(Constants.LAST_POST));
	}

	@Test
	public void updatelastPostInManyRowsTest() {
		analyzerStats.updateLastPost(row1());
		analyzerStats.updateLastPost(row2());
		analyzerStats.updateLastPost(row3());
		analyzerStats.updateLastPost(row4());
		assertEquals(LocalDateTime.of(2018, 12, 13, 12, 31),
				analyzerStats.getAnalyzedAttributes().get(Constants.LAST_POST));
	}

	@Test
	public void updatelastPostInOneRowWithNoAttributeTest() {
		analyzerStats.updateLastPost(row3());
		assertNull(analyzerStats.getAnalyzedAttributes().get(Constants.LAST_POST));
	}

	@Test
	public void updateNumberOfTotalAcceptedPostsInOneRowTest() {
		analyzerStats.updateNumberOfTotalAcceptedPosts(row2());
		assertEquals(1L, analyzerStats.getAnalyzedAttributes().get(Constants.TOTAL_ACCEPTED_POSTS));
	}

	@Test
	public void updateNumberOfTotalAcceptedPostsInManyRowsTest() {
		analyzerStats.updateNumberOfTotalAcceptedPosts(row1());
		analyzerStats.updateNumberOfTotalAcceptedPosts(row2());
		analyzerStats.updateNumberOfTotalAcceptedPosts(row3());
		analyzerStats.updateNumberOfTotalAcceptedPosts(row4());
		assertEquals(3L, analyzerStats.getAnalyzedAttributes().get(Constants.TOTAL_ACCEPTED_POSTS));
	}

	@Test
	public void updateNumberOfTotalAcceptedPostsInOneRowWithNoAttributeTest() {
		analyzerStats.updateNumberOfTotalAcceptedPosts(row4());
		assertNull(analyzerStats.getAnalyzedAttributes().get(Constants.TOTAL_ACCEPTED_POSTS));
	}

	@Test
	public void updateNumberOfTotalPostsInOneRowTest() {
		analyzerStats.updateNumberOfTotalPosts(row2());
		assertEquals(1L, analyzerStats.getAnalyzedAttributes().get(Constants.TOTAL_POSTS));
	}

	@Test
	public void updateNumberOfTotalPostsInManyRowsTest() {
		analyzerStats.updateNumberOfTotalPosts(row1());
		analyzerStats.updateNumberOfTotalPosts(row2());
		analyzerStats.updateNumberOfTotalPosts(row3());
		analyzerStats.updateNumberOfTotalPosts(row4());
		assertEquals(3L, analyzerStats.getAnalyzedAttributes().get(Constants.TOTAL_POSTS));
	}

	@Test
	public void updateNumberOfTotalPostsInOneRowWithNoAttributeTest() {
		analyzerStats.updateNumberOfTotalPosts(row4());
		assertNull(analyzerStats.getAnalyzedAttributes().get(Constants.TOTAL_POSTS));
	}

	private Row row1() {
		return Row.builder().id(1).PostTypeId(2).AcceptedAnswerId(4)
				.CreationDate(LocalDateTime.of(2018, 12, 13, 12, 31)).Score(50).ViewCount(1000).OwnerUserId(22)
				.LastActivityDate(LocalDateTime.of(2014, 12, 13, 12, 30)).title("title").Tags("<TAGS>").AnswerCount(3)
				.CommentCount(2).build();
	}

	private Row row2() {
		return Row.builder().id(2).AcceptedAnswerId(4).CreationDate(LocalDateTime.of(1999, 12, 22, 12, 30))
				.Score(100).build();
	}

	private Row row3() {
		return Row.builder().id(3).AcceptedAnswerId(88).Score(30).build();
	}

	private Row row4() {
		return Row.builder().CreationDate(LocalDateTime.of(1833, 12, 13, 12, 30)).build();
	}
}
