package com.outlook.dev.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outlook.dev.service.event.Recipient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
	private String id;
	private Date receivedDateTime;
	private Recipient from;
	private Boolean isRead;
	private String subject;
	private String bodyPreview;
}
