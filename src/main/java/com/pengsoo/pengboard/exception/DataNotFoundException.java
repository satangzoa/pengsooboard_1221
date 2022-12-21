package com.pengsoo.pengboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "해당 데이터 없음")
public class DataNotFoundException extends RuntimeException{// 에러처리하는 RuntimeException을 상속받는다
	
	public DataNotFoundException(String message) {
		super(message);//throw new DataNotFoundException("해당 질문이 없습니당.");이 메시지를 불러오게한다 QuestionServce.java
	}
	
}
