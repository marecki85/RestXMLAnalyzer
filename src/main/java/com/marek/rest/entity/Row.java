package com.marek.rest.entity;

import java.time.LocalDateTime;

public class Row {

	private String content;
	private Integer id;
	private Integer PostTypeId;
	private Integer AcceptedAnswerId;
	private LocalDateTime CreationDate;
	private Integer Score;
	private Integer ViewCount;
	private String Body;
	private Integer OwnerUserId;
	private LocalDateTime LastActivityDate;
	private String title;
	private String Tags;
	private Integer AnswerCount;
	private Integer CommentCount;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPostTypeId() {
		return PostTypeId;
	}

	public void setPostTypeId(Integer postTypeId) {
		PostTypeId = postTypeId;
	}

	public Integer getAcceptedAnswerId() {
		return AcceptedAnswerId;
	}

	public void setAcceptedAnswerId(Integer acceptedAnswerId) {
		AcceptedAnswerId = acceptedAnswerId;
	}

	public LocalDateTime getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		CreationDate = creationDate;
	}

	public Integer getScore() {
		return Score;
	}

	public void setScore(Integer score) {
		Score = score;
	}

	public Integer getViewCount() {
		return ViewCount;
	}

	public void setViewCount(Integer viewCount) {
		ViewCount = viewCount;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public Integer getOwnerUserId() {
		return OwnerUserId;
	}

	public void setOwnerUserId(Integer ownerUserId) {
		OwnerUserId = ownerUserId;
	}

	public LocalDateTime getLastActivityDate() {
		return LastActivityDate;
	}

	public void setLastActivityDate(LocalDateTime lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public Integer getAnswerCount() {
		return AnswerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		AnswerCount = answerCount;
	}

	public Integer getCommentCount() {
		return CommentCount;
	}

	public void setCommentCount(Integer commentCount) {
		CommentCount = commentCount;
	}
}
