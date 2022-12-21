package com.pengsoo.pengboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pengsoo.pengboard.entity.Question;

public interface QuestionRepository  extends JpaRepository<Question, Integer>{ //Question 은 엔티티 Integer는 기본키 타입
	public List<Question> findBySubject(String subject);
	//제목과 정확히 모두 일치하는 글 찾기
	
	public List<Question> findBySubjectLike(String subject);
	//제목에 특정 낱말이 포함되어 있는 글 찾기
	
	public List<Question> findBySubjectOrderByIdDesc(String subject);
	//제목과 정확히 모두 일치하는 글 찾은 후 id의 내림차순으로 정렬하여 반환

	public Page<Question> findAll(Pageable pageable);//Pageable 은 스프링 도메인으로 임포트
}
