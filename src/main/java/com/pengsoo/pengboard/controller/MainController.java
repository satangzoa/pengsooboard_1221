package com.pengsoo.pengboard.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.pengsoo.pengboard.dto.AnswerForm;
import com.pengsoo.pengboard.dto.MemberForm;
import com.pengsoo.pengboard.dto.QuestionDto;
import com.pengsoo.pengboard.dto.QuestionForm;
import com.pengsoo.pengboard.entity.Answer;
import com.pengsoo.pengboard.entity.Question;
import com.pengsoo.pengboard.entity.SiteMember;
import com.pengsoo.pengboard.repository.AnswerRepository;
import com.pengsoo.pengboard.repository.QuestionRepository;
import com.pengsoo.pengboard.service.AnswerService;
import com.pengsoo.pengboard.service.MemberService;
import com.pengsoo.pengboard.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
//	private final QuestionRepository questionRepository;
//	private final AnswerRepository answerRepository;
//레퍼지토리 부르기	
//	private final QuestionRepository questionRepository;
//	private final AnswerRepository answerRepository;
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final MemberService memberService;
	
//	@Autowired
//	QuestionRepository questionRepository;
//	
//	@Autowired
//	AnswerRepository answerRepository;
//
//	 @RequestMapping("index")
//	 @ResponseBody//간단한 텍스트가 바로 뜨게 해준다
//	 public String index() {
//		 return "반갑습니다.";
//	 }
//	
	@RequestMapping("/")//루트가 걸리면 무조건 처음으로 가게한다
	public String home() {  // 첫화면 리스트로 보내기
		return "redirect:list";
	}
//	
//	@RequestMapping("/question_List") // 리스트로 보내기
//	public String questionList() {
//		return "question_list";
//	}
	
	 @RequestMapping("index")
	 public String index() {
		 return "redirect:list";
	 }
	 
		@RequestMapping(value = "/list")	
		public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {// 리스트에 보일 값들 view로 보내기 defaultValue = "0"은 첫페이지부터 시작하게한다
			
//			List<Question> questionList = questionRepository.findAll();
			
//			List<QuestionDto> questionList = questionService.getQuestionList();
			
			Page<Question> paging = questionService.getList(page);
			
			model.addAttribute("pageCount",  paging.getTotalElements());//전체 게시물 개수
			model.addAttribute("paging", paging);
			
			return "question_list";
		}
		
	
	@RequestMapping("/questionView/{id}")  //리스트 뷰페이지/id 값 가져오기. 따로 id값을 작성해줘야 인식한다/타임리프 방법이다
	public String questionView(Model model,@PathVariable("id") Integer id,AnswerForm answerForm ) { // {id}가 Integer id로 들어온다
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_View";
	}
	
//	
//	@RequestMapping("/answerCreate/{id}")
//	public String answerCreate(@PathVariable("id") Integer id, @RequestParam String content) {//글번호.리퀘스트파람 가져온다
//			
//		answerService.answerCreate(id,content);
//		
//		return String.format("redirect:/questionView/%s", id);//파라미터값 넘겨준댜
//	}
	
//	@PostMapping("/answerCreate/{id}")
//	public String answerCreate(Model model,@PathVariable("id") Integer id,@Valid AnswerForm answerForm, BindingResult bindingResult) {//글번호.리퀘스트파람 가져온다
//			
//		 QuestionDto questionDto = questionService.getQuestion(id); //질문원글을 dto에 담아온다
//		
//		if(bindingResult.hasErrors()) {
//			model.addAttribute("question", questionDto);//에러에 있는 내용만 보여준다.
//			 return "question_view";
//		 }
//		
//		answerService.answerCreate(id,answerForm.getContent());
//		
//		return String.format("redirect:/questionView/%s", id);//파라미터값 넘겨준댜/요청이라서 questionView쓴다
//	}
	@PreAuthorize("isAuthenticated")
	@PostMapping("/answerCreate/{id}")//{id}는 질문게시판 원글의 번호 
	public String answerCreate(Model model,@PathVariable("id") Integer id,
			@Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {//글번호.리퀘스트파람 가져온다
			
		 Question question = questionService.getQuestion(id); //질문원글을 dto에 담아온다
		
		 
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);//에러에 있는 내용만 보여준다.
			 return "question_view";
		 }
		
		answerService.answerCreate(id,answerForm.getContent(), principal.getName());// principal.getName() 현재 로그인 중인 아이디
		
		return String.format("redirect:/questionView/%s", id);//파라미터값 넘겨준댜/요청이라서 questionView쓴다
	}
	 @PreAuthorize("isAuthenticated")
	 @GetMapping("/questionCreate")
	 public String questionCreate(QuestionForm questionForm) {
		 
		 
		 return "question_form";
	 }
	
//	 @RequestMapping("/questionCreateOk")
//	 public String questionCreateOk( @RequestParam String subject, @RequestParam String content) {//
//		 questionService.questionCreate(subject, content);
//		 
//		 
//		 return "redirect:list";
//	 }
	 
	 @PreAuthorize("isAuthenticated")
	 @PostMapping("/questionCreate")//post로 넘길때 postmapping 사용
	 public String questionCreateOk(@Valid QuestionForm questionForm, BindingResult bindingResult,  Principal principal) {//
		 
		 if(bindingResult.hasErrors()) {
			 return "question_form";
		 }
		 
		 questionService.questionCreate(questionForm.getSubject(), questionForm.getContent(),principal.getName());//subject, content값이 questionForm에서 넘어온 값이랑 일치해야한다.
	
		 return "redirect:list";
	 }
	
//	 @RequestMapping("/questionView/{id}")
//		public String question_modify(@PathVariable("id") Integer id, Model model) {  
//		 
//		 QuestionDto question = questionService.getQuestion(id);
//		 model.addAttribute("question", question);
//		 
//		 return "question_modify";
//		}
	 
	 
	 @RequestMapping("join")
	 public String join(MemberForm memberForm) {//
		 
	
		 return "join_form";
	 }
	 
	 @PostMapping(value = "/joinOk")
		public String joinOk(@Valid MemberForm memberForm, BindingResult bindingResult) {
			
			if(bindingResult.hasErrors()) {
				return "join_form";
			}
			
			
			try {
				memberService.memberCreate(memberForm.getUsername(), memberForm.getPassword(), memberForm.getEmail());
			}catch(Exception e){
				e.printStackTrace();
				bindingResult.reject("joinFail", "이미 등록된 아이디입니당.");
				return "join_form";
			}
			return "redirect:list";
		}
		
	 @RequestMapping("/login")
	 public String login() {
		 return "login_form";
	 }
	 
	 @PreAuthorize("isAuthenticated")
	 @GetMapping("/modify/{id}")
	 public String modify(@PathVariable("id") Integer id, QuestionForm questionForm,Principal principal) {//Principal 현재 로그인 되어있는 아이디를 가져온다.
		 
		 Question question =  questionService.getQuestion(id);
		 
		 if(!question.getWriter().getUsername().equals(principal.getName())) {//위에 question 에 username이 저장되어있어서 하나씩 찾아가는 자리를 입력하면된다.
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없음!!");
		 }
		 
		 questionForm.setSubject(question.getSubject());// 벨리데이션이 되어있어서 question.getSubject() 해야한다.
		 questionForm.setContent(question.getContent());
		 
		 
		 return "question_form";
	 }
	 
	 @PreAuthorize("isAuthenticated")
	 @PostMapping("/modify/{id}")
	 public String questionModify(@PathVariable("id") Integer id,@Valid QuestionForm questionForm, BindingResult bindingResult,  Principal principal) {
		 

		 if(bindingResult.hasErrors()) {
			 return "question_form";
		 }
		 
		 Question question =  questionService.getQuestion(id);
	 
		 questionService.modify(questionForm.getSubject(), questionForm.getContent(),question);
		 
		 return String.format("redirect:/questionView/%s", id);
	 }
	 
	 
	 @PreAuthorize("isAuthenticated")
	 @GetMapping("/delete/{id}")
	 public String delete(@PathVariable("id") Integer id, QuestionForm questionForm, Principal principal) {
		 
		 Question question =  questionService.getQuestion(id);
		 
		 if(!question.getWriter().getUsername().equals(principal.getName())) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없음!!");
		 }
		 
		questionService.delete(id);
		 return "redirect:/index";
	 }
	 
	 

		@PreAuthorize("isAuthenticated")
		@GetMapping(value = "/answerModify/{id}")
		public String answerModify(@PathVariable("id") Integer id, AnswerForm answerForm, Principal principal) {
			
			Answer answer = answerService.getAnswer(id);
			
			if(!answer.getWriter().getUsername().equals(principal.getName()) ) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");			
			}
			
		
			answerForm.setContent(answer.getContent());
			
			return "answer_form";
		}
	 
	
	 @PreAuthorize("isAuthenticated")
		@PostMapping(value = "/answerModify/{id}")
		public String answerModify(@PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
			
			if(bindingResult.hasErrors()) {
				return "answer_form";
			}
			
			Answer answer = answerService.getAnswer(id);
			
			if(!answer.getWriter().getUsername().equals(principal.getName()) ) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");			
			}
			
			answerService.answerModify(answerForm.getContent(), answer);
			
			return String.format("redirect:/questionView/%s", answer.getQuestion().getId());
		}
	 
	 @PreAuthorize("isAuthenticated")
	 @GetMapping("/answerDelete/{id}")
	 public String answerDelete(@PathVariable("id") Integer id, Principal principal) {
		 
		 Answer answer = answerService.getAnswer(id);
		 if(!answer.getWriter().getUsername().equals(principal.getName())) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없음!!");
		 }
		 
		 answerService.answerDelete(id);
		 return String.format("redirect:/questionView/%s", answer.getQuestion().getId());
	 }
	 
	 @PreAuthorize("isAuthenticated")
	 @GetMapping("/questionLike/{id}")//하이퍼링크 요청이라서사용
	 public String questionLike(@PathVariable("id") Integer id, Principal principal) {
		 
		 Question question =  questionService.getQuestion(id);
		 
		 SiteMember siteMember = memberService.getMemberInfo(principal.getName());
		
		 questionService.questionLike(question, siteMember);
		 
		 return String.format("redirect:/questionView/%s", id);
	 }
	 
	 
	 @PreAuthorize("isAuthenticated")
	 @GetMapping("/answerLike/{id}")//하이퍼링크 요청이라서사용
	 public String answerLike(@PathVariable("id") Integer id, Principal principal) {
		 
		 Answer answer = answerService.getAnswer(id);
		 
		 SiteMember siteMember = memberService.getMemberInfo(principal.getName());
		
		 answerService.answerLike(answer, siteMember);
		 
		 return String.format("redirect:/questionView/%s", answer.getQuestion().getId());
	 }
	 
	}









