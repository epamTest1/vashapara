package com.couple.persistence.stubs;

import java.util.Arrays;
import java.util.List;

import com.couple.model.Category;
import com.couple.model.Question;
import com.couple.persistence.CategoryDao;

//@org.springframework.stereotype.Repository
public class CategoryInMemoryDao implements CategoryDao {
	private final List<Category> categories;
	
	
	public CategoryInMemoryDao() {
		Category home = new Category("Дом");
		home.addQuestion(new Question("Убираю в квартире"));
		home.addQuestion(new Question("Мою посуду"));
		home.addQuestion(new Question("Выношу мусор"));
		home.addQuestion(new Question("Мою окна"));
		
		Category finances = new Category("Финансы");
		finances.addQuestion(new Question("бла-бла-бла"));
		
		categories = Arrays.asList(home, finances);
	}
	
	@Override
	public List<Category> getCategories() {
		return categories;
	}

}
