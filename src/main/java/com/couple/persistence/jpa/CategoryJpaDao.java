package com.couple.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.couple.model.Category;
import com.couple.persistence.CategoryDao;

@Repository
public class CategoryJpaDao implements CategoryDao {
	private static final String LOAD_WITH_QUESTIONS_QUERY = "Category.getAll";
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Category> getCategories() {
		return em.createNamedQuery(LOAD_WITH_QUESTIONS_QUERY, Category.class).getResultList();
	}
}
