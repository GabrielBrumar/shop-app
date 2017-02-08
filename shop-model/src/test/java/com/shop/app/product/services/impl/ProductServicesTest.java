package com.shop.app.product.services.impl;

import static com.shop.app.commontests.product.ProductsForTest.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

import com.shop.app.common.exception.FieldNotValidException;
import com.shop.app.product.model.Product;
import com.shop.app.product.repository.ProductRepository;
import com.shop.app.product.services.ProductServices;

public class ProductServicesTest {
	private ProductServices productServices;
	private ProductRepository productRepository;
	private Validator validator;

	@Before
	public void initTestCase() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		productRepository = mock(ProductRepository.class);

		productServices = new ProductServicesImpl();
		((ProductServicesImpl) productServices).validator = validator;
		((ProductServicesImpl) productServices).productRepository = productRepository;
	}

	@Test
	public void testAddProductWithNullName() {
		final Product product = new Product(null, "great", 10.0f);
		addProductWithInvalidName(product);
	}

	@Test
	public void testAddProductWithShortName() {
		final Product product = new Product("c", "great", 10.0f);

		addProductWithInvalidName(product);
	}

	@Test
	public void testAddProductWithLongName() {
		final Product product = new Product("This is a long name that will cause an exception to be thrown", "great", 10.0f);
		addProductWithInvalidName(product);
	}

	private void addProductWithInvalidName(final Product product) {
		try {
			productServices.add(product);
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo("name")));
		}
	}

	@Test
	public void testFindAllNoProducts() {
		when(productRepository.findAll("name")).thenReturn(new ArrayList<>());

		final List<Product> products = productServices.findAll();
		assertThat(products.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void testFindAllProducts() {
		when(productRepository.findAll("name")).thenReturn(
				Arrays.asList(productWithId(cake(), 1L), productWithId(iceCream(), 2L)));

		final List<Product> products = productServices.findAll();
		assertThat(products.size(), is(equalTo(2)));
		assertThat(products.get(0).getName(), is(equalTo(cake().getName())));
		assertThat(products.get(1).getName(), is(equalTo(iceCream().getName())));
	}

	@Test
	public void testRemoveProduct() {
		when(productRepository.remove(productWithId(iceCream(), 2L))).thenReturn(1);

		final int productsDeleted = productServices.deleteProduct(productWithId(iceCream(), 2L));
		assertThat(productsDeleted, is(equalTo(1)));
	}

}
