package com.shop.app.product.services;

import java.util.List;

import javax.ejb.Local;

import com.shop.app.common.exception.FieldNotValidException;
import com.shop.app.product.model.Product;

@Local
public interface ProductServices {
	Product add(Product product) throws FieldNotValidException;

	int deleteProduct(Product product);

	List<Product> findAll();

}
