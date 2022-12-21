package com.pengsoo.pengboard.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AnswerForm {

	@NotEmpty(message = "답변 내용은 넣어야지이이!")
	@Size(min = 10, message = "답변 내용 10이상만 가능하지롱")
	private String content;
}
