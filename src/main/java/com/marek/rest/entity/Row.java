package com.marek.rest.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
}
