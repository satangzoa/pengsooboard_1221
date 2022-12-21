package com.pengsoo.pengboard.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pengsoo.pengboard.dto.QuestionDto;
import com.pengsoo.pengboard.entity.Answer;
import com.pengsoo.pengboard.entity.Question;
import com.pengsoo.pengboard.entity.SiteMember;
import com.pengsoo.pengboard.exception.DataNotFoundException;
import com.pengsoo.pengboard.repository.AnswerRepository;
import com.pengsoo.pengboard.repository.MemberRepository;
import com.pengsoo.pengboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	private final MemberService memberService;
//	public List<Question> getQuestionList() {
//		List<Question> questionList = questionRepository.findAll();
//		
//		return questionList;//엔티티로 반환한다.
//	}
//	
//	public Question getQuestion(Integer id) {
//		
//		Optional<Question> optQquestion = questionRepository.findById(id);
//		
//		if(optQquestion.isPresent()) {
//			Question question = optQquestion.get();
//			
//			return question;
//		} else {
//			throw new DataNotFoundException("해당 질문이 없습니당.");
//		}
//		
//	}

	public Page<Question> getList(int page) {
		
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("id"));//최근글이 먼저 오게한다.
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));//페이지당 10개가 보여진다.
		
		Page<Question> pages =questionRepository.findAll(pageable);
		return pages;
		
		
	}
	
public List<Question> getQuestionList() {
		
		List<Question> questionList = questionRepository.findAll();
		
		
//		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		
		
//		for(int i=0;i<questionList.size();i++) {
//			//System.out.println("list인덱스:"+i);
//			Question question = questionList.get(i);
//			QuestionDto questionDto = new QuestionDto();
//			
//			questionDto.setId(question.getId());
//			questionDto.setContent(question.getContent());
//			questionDto.setSubject(question.getSubject());
//			questionDto.setAnswers(question.getAnswerList());
//			questionDto.setCreateDate(question.getCreateDate());
//			
//			questionDtos.add(questionDto);
//		}		
		
		return questionList;
	}
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> optQuestion = questionRepository.findById(id);
		
//		QuestionDto questionDto = new QuestionDto();
		if(optQuestion.isPresent()) {
			
			Question question = optQuestion.get();
//			Question question = optQuestion.get();
//			questionDto.setId(question.getId());
//			questionDto.setContent(question.getContent());
//			questionDto.setSubject(question.getSubject());
//			questionDto.setAnswers(question.getAnswerList());
//			questionDto.setCreateDate(question.getCreateDate());
			return question;
		} else {
			throw new DataNotFoundException("해당 질문이 없습니당.");
		}
		
	}
	
//	public void questionCreate(String subject, String content) { //
//		
//		
//		Question question = new Question();
//		
//		question.setSubject(subject);
//		question.setContent(content);
//		question.setCreateDate(LocalDateTime.now());//LocalDateTime.now() 암기 .상수를 넣어준다
//		
//	    questionRepository.save(question);
//	}
	
	public void questionCreate(String subject, String content, String username) {
		SiteMember siteMember = memberService.getMemberInfo(username);
		
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setWriter(siteMember);
		
		questionRepository.save(question);
	}
	
	
	public void modify(String subject, String content,Question question) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());//수정 시간을 지금 현재시간으로 셋팅
		
		questionRepository.save(question);//새로운 수정
	}
	
	public void delete(Integer id) {
		
		questionRepository.deleteById(id);
	}
	
	public void questionLike(Question question, SiteMember siteMember) {
		question.getLiker().add(siteMember);
		//현재 질문글이 가지고 있는 좋아요를 누른 회원의 집합을 가져온 후 그 집합에 새로운 좋아요 클릭 회원 객체를 추가한다
		questionRepository.save(question);
	}
}
	








