package com.couple.persistence;

import java.util.List;

import com.couple.model.Question;

public interface QuestionDao {
	public List<Question> getAllQuestions();
}
