package com.marek.integration;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marek.rest.MainAppConfig;
import com.marek.rest.entity.AnalyzedContent;
import com.marek.rest.service.XMLNotFoundException;
import com.marek.rest.service.standard.StandardAnalyzerService;
import com.marek.rest.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MainAppConfig.class)
public class AnalyzerServiceIntegrationTest {

	@Autowired
	StandardAnalyzerService service;

	@Test
	public void shouldProperlyAnalyzeXML() {
		AnalyzedContent analyzedContent = service.getAnalyzedContent("src/test/resources/testXML.xml");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
		String firstPost = "2015-07-14T18:39:27.757";
		String lastPost = "2015-09-14T12:46:52.053";
		LocalDateTime actualFirstPost = (LocalDateTime) analyzedContent.getDetails().get(Constants.FIRST_POST);
		LocalDateTime actualLastPost = (LocalDateTime) analyzedContent.getDetails().get(Constants.LAST_POST);
		assertEquals(LocalDateTime.parse(firstPost, formatter), actualFirstPost);
		assertEquals(LocalDateTime.parse(lastPost, formatter), actualLastPost);
		assertEquals(80L, analyzedContent.getDetails().get(Constants.TOTAL_POSTS));
		assertEquals(7L, analyzedContent.getDetails().get(Constants.TOTAL_ACCEPTED_POSTS));
	}

	@Test(expected = XMLNotFoundException.class)
	public void shouldThrowXMLNotFoundExceptionForNonExistingFile() {
		service.getAnalyzedContent("src/test/resources/testXML.xmls");
	}
}
