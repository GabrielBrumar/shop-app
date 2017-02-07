package com.shop.app.managed.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.shop.app.category.model.Category;
import com.shop.app.category.services.CategoryServices;

@ManagedBean
public class ProductBeans {
	private String name;
	@Inject
	CategoryServices categoryServices;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void saveProduct() {
		/*
		 * Session session = HibernateUtil.getSessionFactory().openSession();
		 * session.beginTransaction();
		 * Product product = new Product();
		 * product.setName(name);
		 * product.setPrice(2.0f);
		 * session.save(product);
		 * session.getTransaction().commit();
		 * session.close();
		 */
		final Category findById = categoryServices.findById(1L);
		System.out.println(findById);
	}
}
