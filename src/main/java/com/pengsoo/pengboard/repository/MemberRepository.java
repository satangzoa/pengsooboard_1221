package com.pengsoo.pengboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pengsoo.pengboard.entity.SiteMember;

public interface MemberRepository  extends JpaRepository<SiteMember, Integer>{

	public  Optional<SiteMember> findByUsername(String username);
}
