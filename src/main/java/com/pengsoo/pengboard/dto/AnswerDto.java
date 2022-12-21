package com.pengsoo.pengboard.dto;

import java.time.LocalDateTime;

import com.pengsoo.pengboard.entity.Question;

import lombok.Data;

@Data
public class AnswerDto {

	private Integer id;//답변 게시판 번호
	
	private String content;
	
	private LocalDateTime createTime; // 답변게시판 등록일
	
	private Question qustion; // 질문게시판에 id를 가져와야 함
}
