package com.couple.persistence.stubs;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.couple.model.Category;
import com.couple.model.Question;
import com.couple.persistence.CategoryDao;

@Repository
public class CategoryInMemoryDao implements CategoryDao {
	private final List<Category> categories;
	
	public CategoryInMemoryDao() {
		Category home = new Category(1, "Дом");
		home.addQuestion(new Question(1, "Убираю в квартире"));
		home.addQuestion(new Question(2, "Мою посуду"));
		home.addQuestion(new Question(3, "Выношу мусор"));
		home.addQuestion(new Question(4, "Мою окна"));
		
		Category finances = new Category(2, "Финансы");
		finances.addQuestion(new Question(5, "бла-бла-бла"));
		
		categories = Arrays.asList(home, finances);
	}
	
	@Override
	public List<Category> getCategories() {
		return categories;
	}

}
