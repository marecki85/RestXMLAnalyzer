package com.marek.rest.service;

import java.io.IOException;
import java.util.function.Consumer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.marek.rest.entity.Row;

@Service
public class RowXMLParser {

	public void parse(String url, Consumer<Row> rowConsumer) {
		AnalyzeHandler analyzeHandler = new AnalyzeHandler(rowConsumer);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(url, analyzeHandler);
		} catch (IOException e) {
			throw new XMLNotFoundException();
		} catch (SAXException | ParserConfigurationException e) {
			throw new RuntimeException("Cannot parse document", e);
		}
	}
}
