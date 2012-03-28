package com.couple.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couple.model.Category;
import com.couple.persistence.CategoryDao;
import com.couple.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	
	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Override
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

}
