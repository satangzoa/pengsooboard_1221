package com.pengsoo.pengboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pengsoo.pengboard.entity.Answer;
import com.pengsoo.pengboard.entity.Question;
import com.pengsoo.pengboard.entity.SiteMember;
import com.pengsoo.pengboard.exception.DataNotFoundException;
import com.pengsoo.pengboard.repository.AnswerRepository;
import com.pengsoo.pengboard.repository.MemberRepository;
import com.pengsoo.pengboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.Return;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final MemberService memberService;
	
	public void answerCreate(Integer id, String content,String username) {
		
		Optional<Question> optQuestion	= questionRepository.findById(id);
		Question question = optQuestion.get();
		
		 SiteMember siteMember = memberService.getMemberInfo(username);
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateTime(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setWriter(siteMember);
		
		answerRepository.save(answer);
	}
	
	
	public Answer getAnswer(Integer id) {
		 Optional<Answer> optAnswer = answerRepository.findById(id);
		 
		 if(optAnswer.isPresent()) {
			  return optAnswer.get();
			  
		 } else {
			 
			 throw new DataNotFoundException("해당 답변이 없습니다.");
		 }
		 }

	
public void answerModify(String content, Answer answer) {
		
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		answerRepository.save(answer);
	}
	
	public void answerDelete(Integer id) {
		
		answerRepository.deleteById(id);
	}
	
	public void answerLike(Answer answer, SiteMember siteMember) {
		answer.getLiker().add(siteMember);
		answerRepository.save(answer);
	}
	
}














