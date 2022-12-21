package com.pengsoo.pengboard.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data//롬복의 게터 세터
public class QuestionForm {
	
	@NotEmpty(message = "제목은 필수입력사항입니다!")
	@Size(max = 100, message = "제목은 100자 이하만 가능하지롱")//최소값 최대값을 넣을 수 있다 200바이트를 넘기면 에러
	private String subject;
	
	@NotEmpty(message = "내용은 필수입력사항입니다!")
	@Size(min = 10, message = "내용은 10자 이상 써야하지롱")//20byte가 안되면 에러
	private String content;
	
}
