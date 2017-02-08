package com.shop.app.commontests.product;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;

import com.shop.app.product.model.Product;

@Ignore
public class ProductsForTest {
	public static Product cake() {
		return new Product("cake", "yummy", 3.0f);
	}

	public static Product iceCream() {
		return new Product("icecream", "yummy", 5.0f);
	}

	public static Product chocolate() {
		return new Product("chocolate", "yummy", 10.0f);
	}

	public static Product productWithId(final Product product, final Long id) {
		product.setId(id);
		return product;
	}

	public static List<Product> allProducts() {
		return Arrays.asList(cake(), iceCream(), chocolate());
	}

}
