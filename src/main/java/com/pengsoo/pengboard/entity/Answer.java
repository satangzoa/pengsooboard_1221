package com.pengsoo.pengboard.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id; //답변 게시판 번호
	
	@Column(length = 1000)
	private String content; // 답변 게시판 내용
	
	
	private LocalDateTime createTime; // 답변 게시판 등록일
	
	@ManyToOne//n :1 구조 (질문 1개에 답변 여러개가 달리는 구조-부모(질문) 자식(답변)관계)
	private Question question;// 질문 게시판 객체(질문게시판의 id 획득) 아주 중요/  연동 질문과 답변 두 게시판 연동 //id(외래키)를 가져오는 필드가 생성됨
	
	
	@ManyToOne
	private SiteMember writer;//글쓴이
	
	private LocalDateTime modifyDate; //글 수정 일시
	
	@ManyToMany
	private Set<SiteMember> liker;//좋아요 누른 아이디
	
}












