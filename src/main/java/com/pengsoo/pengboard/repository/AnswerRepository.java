package com.pengsoo.pengboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pengsoo.pengboard.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
