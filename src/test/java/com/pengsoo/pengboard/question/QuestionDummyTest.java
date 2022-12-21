package com.pengsoo.pengboard.question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.pengsoo.pengboard.service.*;

@SpringBootTest
public class QuestionDummyTest {

	@Autowired
	private QuestionService questionService;
	@Test
	public void testDummyDate() {
		for(int i=0;i<150;i++)  {//더미 150개 삽입
			String subject = String.format("테스트 데이터 입니다:%d", i);
			String content = "테스트 데이터 내용입니다. 내용은 없습니다.";
			questionService.questionCreate(subject, content, "tiger");
		}
	}
}
