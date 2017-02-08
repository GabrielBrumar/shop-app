package com.shop.app.product.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.shop.app.product.model.Product;

@Stateless
public class ProductRepository {

	@PersistenceContext
	EntityManager em;

	public Product add(final Product product) {
		em.persist(product);
		return product;
	}

	public int remove(final Product product) {
		final Query query = em.createNativeQuery("DELETE FROM lib_product WHERE id = " + product.getId());
		final int updatedRows = query.executeUpdate();
		return updatedRows;
	}

	public Product findById(final Long id) {
		if (id == null) {
			return null;
		}
		return em.find(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll(final String orderField) {
		return em.createQuery("Select e From Product e Order by e." + orderField).getResultList();
	}

}
