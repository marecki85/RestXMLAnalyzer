package com.marek.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marek.rest.entity.AnalyzedContent;
import com.marek.rest.entity.Request;
import com.marek.rest.service.AnalyzerService;
import com.marek.rest.service.XMLNotFoundException;

@RestController
public class RestAnalyzer {

	@Autowired
	private AnalyzerService analyzerService;

	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AnalyzedContent getStandardAnalyzedContent(@RequestBody Request request) {
		return analyzerService.getAnalyzedContent(request.getUrl());
	}

	@ExceptionHandler(XMLNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Could not retrieve the document from the given URL")
	public void handleXMLNotFound() {
	}
}
