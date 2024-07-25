package com.humancloud.task_management_tool.auth_service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response 
{
	private Date date;
	private Integer httpStatusCode;
	private HttpStatus httpStatusMessage;
	private String customMessage;
	private String apiPath;
}
