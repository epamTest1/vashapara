package com.couple.persistence;

import java.util.List;

import com.couple.model.Category;

public interface CategoryDao {
	public List<Category> getCategories();
}
