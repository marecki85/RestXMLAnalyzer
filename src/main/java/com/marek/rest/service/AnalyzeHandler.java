package com.marek.rest.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.marek.rest.entity.Row;
import com.marek.rest.util.Constants;

public class AnalyzeHandler extends DefaultHandler {

	private Consumer<Row> rowConsumer;
	private Row row = null;

	public AnalyzeHandler(Consumer<Row> rowConsumer) {
		super();
		this.rowConsumer = rowConsumer;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals(Constants.ROW)) {
			row = new Row();
			row.setId(parseIfNotNull(attributes, Constants.ID));
			row.setPostTypeId(parseIfNotNull(attributes, Constants.POST_TYPE_ID));
			row.setAcceptedAnswerId(parseIfNotNull(attributes, Constants.ACCEPTED_ANSWER_ID));
			row.setCreationDate(parseDate(attributes.getValue(Constants.CREATION_DATE)));
			row.setScore(parseIfNotNull(attributes, Constants.SCORE));
			row.setViewCount(parseIfNotNull(attributes, Constants.VIEW_COUNT));
			row.setBody(attributes.getValue(Constants.BODY));
			row.setOwnerUserId(parseIfNotNull(attributes, Constants.OWNER_USER_ID));
			row.setLastActivityDate(parseDate(attributes.getValue(Constants.LAST_ACTIVITY_DATE)));
			row.setTitle(attributes.getValue(Constants.TITLE));
			row.setTags(attributes.getValue(Constants.TAGS));
			row.setAnswerCount(parseIfNotNull(attributes, Constants.ANSWER_COUNT));
			row.setCommentCount(parseIfNotNull(attributes, Constants.COMMENT_COUNT));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equals("row")) {
			rowConsumer.accept(row);
		}
	}

	private static LocalDateTime parseDate(String date) {
		LocalDateTime localDateTime = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
		if (date != null) {
			localDateTime = LocalDateTime.parse(date, formatter);
		}
		return localDateTime;
	}

	private static Integer parseIfNotNull(Attributes attributes, String key) {
		Integer result = null;
		result = attributes.getValue(key) == null ? null : Integer.parseInt(attributes.getValue(key));
		return result;
	}
}