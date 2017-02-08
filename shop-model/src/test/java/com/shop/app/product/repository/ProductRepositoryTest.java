package com.shop.app.product.repository;

import static com.shop.app.commontests.product.ProductsForTest.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shop.app.commontests.utils.DBCommandTransactionalExecutor;
import com.shop.app.product.model.Product;

public class ProductRepositoryTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	private ProductRepository productRepository;
	private DBCommandTransactionalExecutor dBCommandTransactionalExecutor;

	@Before
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("libraryPU");
		em = emf.createEntityManager();

		productRepository = new ProductRepository();
		productRepository.em = em;

		dBCommandTransactionalExecutor = new DBCommandTransactionalExecutor(em);
	}

	@After
	public void closeEntityManager() {
		em.close();
		emf.close();
	}

	@Test
	public void testAddAndRetrieveProduct() {
		final Long productId = dBCommandTransactionalExecutor.executeCommand(() -> {
			return productRepository.add(cake()).getId();
		});

		assertThat(productId, is(notNullValue()));

		final Product product = productRepository.findById(productId);
		assertThat(product, is(notNullValue()));
		assertThat(product.getName(), is(equalTo(cake().getName())));
	}

	@Test
	public void testFindProductByIdNotFound() {
		final Product product = productRepository.findById(999L);
		assertThat(product, is(nullValue()));
	}

	@Test
	public void testFindProductByIdWithNullId() {
		final Product product = productRepository.findById(null);
		assertThat(product, is(nullValue()));
	}

	@Test
	public void testFindAllProducts() {
		dBCommandTransactionalExecutor.executeCommand(() -> {
			allProducts().forEach(productRepository::add);
			return null;
		});

		final List<Product> products = productRepository.findAll("name");
		assertThat(products.size(), is(equalTo(3)));
		assertThat(products.get(0).getName(), is(equalTo(cake().getName())));
		assertThat(products.get(1).getName(), is(equalTo(chocolate().getName())));
		assertThat(products.get(2).getName(), is(equalTo(iceCream().getName())));
	}

	@Test
	public void testDeleteProduct() {
		final Long id = dBCommandTransactionalExecutor.executeCommand(() -> {
			return productRepository.add(cake()).getId();
		});
		final List<Product> products = productRepository.findAll("name");
		assertThat(products.size(), is(equalTo(1)));
		final Integer deletedRows = dBCommandTransactionalExecutor.executeCommand(() -> {
			return productRepository.remove(productWithId(cake(), id));
		});
		final List<Product> products1 = productRepository.findAll("name");
		assertThat(products1.size(), is(equalTo(0)));
		assertThat(deletedRows, is(equalTo(1)));
	}

}
