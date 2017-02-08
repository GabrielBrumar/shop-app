package com.shop.app.managed.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.shop.app.product.model.Product;
import com.shop.app.product.services.ProductServices;

@ManagedBean
public class ShopListBeans {

	@Inject
	ProductServices productServices;
	private String name;
	private String description;

	public String getDescription() {
		return description;
	}

	private float price;
	private List<Product> products = new ArrayList<>();
	private Product product;

	// JavaServerFaces related variables
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;

	public String addNew() {
		product = new Product("", "", 0f);
		form.setRendered(true);
		addCommand.setRendered(false);
		return null;
	}

	public String save() {
		final Product prod = new Product(name, description, price);
		productServices.add(prod);
		products.add(prod);
		return null;
	}

	public String cancel() {
		product = null;
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public String delete() {
		productServices.deleteProduct(product);
		products.remove(product);
		return null;
	}

	public void displayTable(final ActionEvent event) {
		if (event.getComponent().getId().equalsIgnoreCase("hide")) {
			tableForm.setRendered(false);
		} else {
			tableForm.setRendered(true);
		}
	}

	public List<Product> getRepoProducts() {
		final List<Product> allProducts = productServices.findAll();
		if (allProducts != null) {
			products = allProducts;
		}
		return products;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(final UIForm form) {
		this.form = form;
	}

	public UICommand getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(final UICommand addCommand) {
		this.addCommand = addCommand;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(final UIForm tableForm) {
		this.tableForm = tableForm;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(final List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(final Product product) {
		this.product = product;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(final float price) {
		this.price = price;
	}
}