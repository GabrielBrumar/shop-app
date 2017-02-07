package com.shop.app.category.services;

import java.util.List;

import javax.ejb.Local;

import com.shop.app.category.exception.CategoryExistentException;
import com.shop.app.category.exception.CategoryNotFoundException;
import com.shop.app.category.model.Category;
import com.shop.app.common.exception.FieldNotValidException;

@Local
public interface CategoryServices {

	Category add(Category category) throws FieldNotValidException, CategoryExistentException;

	void update(Category category) throws FieldNotValidException, CategoryNotFoundException;

	Category findById(Long id) throws CategoryNotFoundException;

	List<Category> findAll();

}