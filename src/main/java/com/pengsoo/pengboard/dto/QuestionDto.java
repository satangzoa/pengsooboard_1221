package com.pengsoo.pengboard.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.pengsoo.pengboard.entity.Answer;

import lombok.Data;

@Data
public class QuestionDto {

	private Integer id;
	private String subject;
	private String content;
	private LocalDateTime CreateDate;
	
	private List<Answer> answers;
	
	
	
}
