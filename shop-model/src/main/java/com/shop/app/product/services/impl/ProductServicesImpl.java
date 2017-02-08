package com.shop.app.product.services.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.shop.app.common.exception.FieldNotValidException;
import com.shop.app.product.model.Product;
import com.shop.app.product.repository.ProductRepository;
import com.shop.app.product.services.ProductServices;

@Stateless
public class ProductServicesImpl implements ProductServices {
	@Inject
	Validator validator;

	@Inject
	ProductRepository productRepository;

	@Override
	public Product add(final Product product) throws FieldNotValidException {
		validateProduct(product);

		return productRepository.add(product);
	}

	@Override
	public int deleteProduct(final Product product) {
		validateProduct(product);
		return productRepository.remove(product);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll("name");

	}

	private void validateProduct(final Product product) {
		final Set<ConstraintViolation<Product>> errors = validator.validate(product);
		final Iterator<ConstraintViolation<Product>> itErrors = errors.iterator();
		if (itErrors.hasNext()) {
			final ConstraintViolation<Product> violation = itErrors.next();
			throw new FieldNotValidException(violation.getPropertyPath().toString(), violation.getMessage());
		}
	}
}
