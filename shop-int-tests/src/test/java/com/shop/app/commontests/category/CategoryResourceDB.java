package com.shop.app.commontests.category;

import static com.shop.app.commontests.category.CategoryForTestsRepository.*;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shop.app.category.services.CategoryServices;

@Path("/DB/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResourceDB {

	@Inject
	private CategoryServices categoryServices;

	@POST
	public void addAll() {
		allCategories().forEach(categoryServices::add);
	}

}