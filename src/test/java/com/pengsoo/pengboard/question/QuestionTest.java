package com.pengsoo.pengboard.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.pengsoo.pengboard.entity.Question;
import com.pengsoo.pengboard.repository.QuestionRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuestionTest  {

	@Autowired
	private QuestionRepository questionRepository;
	
//	@Test
//	@DisplayName("질문 저장 테스트")
//	public void testQuestion1() {
//		Question q1 = new Question();//빈 Question 객체 q1
//		q1.setSubject("안녕하십니꽈.");
//		q1.setContent("반갑습니돠");
//		q1.setCreateDate(LocalDateTime.now());//현재시간  저장
//		//속성값이 초기화된 Question 객체 q1가 만들어짐
//		questionRepository.save(q1); // 첫번쨰 질문 생성 저장
//		
//		
//		Question q2 = new Question();//빈 Question 객체 q1
//		q2.setSubject("질문있으십니다.");
//		q2.setContent("스프링 질문이댜");
//		q2.setCreateDate(LocalDateTime.now());//현재시간  저장
//		//속성값이 초기화된 Question 객체 q2가 만들어짐
//		questionRepository.save(q2); // 첫번쨰 질문 생성 저장
//	}
	
	@Test
	@DisplayName("조회 테스트")
	public void testQuestion2() {
		List<Question> qAll= questionRepository.findAll(); // 질문 게시판의 모든 글 가져오기
		assertEquals(2, qAll.size());//전체 글의 개수를 가져와 기대하고 있는 글의 개수인 2와 비교 확인
		
		 Question q1 = qAll.get(0);//첫번 째 글 가져오기 .get(0)은 인덱스 0
		 assertEquals("안녕하십니꽈.", q1.getSubject()); // 첫번 째 글의 글 제목
		 
		 Optional<Question> q2 = questionRepository.findById(2); // 아이디가 2인 글 가져오기 // 값이 없을 수도( null) 있으므로 Optional
		 //Optional<Question> q3 = questionRepository.findById(3); // 아이디가 3인 글 가져오기 // 값이 없을 수도 있으므로 Optional
		 
		 if(q2.isPresent()) {
			 Question qq = q2.get();//2번 레코드 글
			 assertEquals("질문있으십니다.", qq.getSubject());
			 
		 }
		 
		// if(q3.isEmpty()) {
		//	 System.out.println("q3 내용없음");
			 
		// }
	}

	@Test
	@DisplayName("조회 테스트2")
	public void testQuestion3() {
		List<Question> questionList = questionRepository.findBySubject("질문있으십니다.");
		
		 assertEquals("스프링 질문이댜",questionList.get(0).getContent() );
		
	}
	
	@Test
	@DisplayName("조회 테스트3")
	public void testQuestion4() {
		List<Question> questionList = questionRepository.findBySubjectLike("%질문%");
		
		 assertEquals("스프링 질문이댜",questionList.get(0).getContent() );
		
	}

	@Test
	@DisplayName("수정테스트")
	public void testQuestion5() {
		
		Optional<Question> q1 =questionRepository.findById(1);//id가 1번 글 가져오기
		 if(q1.isPresent()) {
			 Question qq = q1.get();
			 qq.setSubject("감사합니당");
			 questionRepository.save(qq);
		 }
	}
	
	@Test
	@DisplayName("삭제테스트")
	public void testQuestion6() {
		
		Optional<Question> q1 =questionRepository.findById(1);//id가 1번 글 가져오기
		 if(q1.isPresent()) {
			 Question qq = q1.get();
			 questionRepository.delete(qq);
		 }
		
		
	}
}














