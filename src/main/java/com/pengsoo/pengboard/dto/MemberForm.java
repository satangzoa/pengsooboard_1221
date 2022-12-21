package com.pengsoo.pengboard.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberForm {

	@NotEmpty(message = "아이디(은)는 필수입력사항입니다!")
	@Size(min = 4, message = "아이디는 4자 이상이랑께")//최소값 최대값을 넣을 수 있다 200바이트를 넘기면 에러
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수입력사항입니다!")
	@Size(min = 4, message = "비밀번호는 4자 이상 써야하지롱")//20byte가 안되면 에러
	private String password;
	
	@NotEmpty(message = "이메일도 적어주시오!")
	private String email;
}
