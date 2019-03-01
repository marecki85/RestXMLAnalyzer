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

	AnalyzeHandler(Consumer<Row> rowConsumer) {
		super();
		this.rowConsumer = rowConsumer;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes){
		if (qName.equals(Constants.ROW)) {
			row = Row.builder()
					.id(parseIfNotNull(attributes, Constants.ID))
					.PostTypeId(parseIfNotNull(attributes, Constants.POST_TYPE_ID))
					.AcceptedAnswerId(parseIfNotNull(attributes, Constants.ACCEPTED_ANSWER_ID))
					.CreationDate(parseDate(attributes.getValue(Constants.CREATION_DATE)))
					.Score(parseIfNotNull(attributes, Constants.SCORE))
					.ViewCount(parseIfNotNull(attributes, Constants.VIEW_COUNT))
					.Body(attributes.getValue(Constants.BODY))
					.OwnerUserId(parseIfNotNull(attributes, Constants.OWNER_USER_ID))
					.LastActivityDate(parseDate(attributes.getValue(Constants.LAST_ACTIVITY_DATE)))
					.title(attributes.getValue(Constants.TITLE))
					.Tags(attributes.getValue(Constants.TAGS))
					.AnswerCount(parseIfNotNull(attributes, Constants.ANSWER_COUNT))
					.CommentCount(parseIfNotNull(attributes, Constants.COMMENT_COUNT))
					.build();
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
		Integer result;
		result = attributes.getValue(key) == null ? null : Integer.parseInt(attributes.getValue(key));
		return result;
	}
}