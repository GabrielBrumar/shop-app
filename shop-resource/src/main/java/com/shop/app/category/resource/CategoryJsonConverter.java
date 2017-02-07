package com.shop.app.category.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shop.app.category.model.Category;
import com.shop.app.common.json.JsonReader;

@ApplicationScoped
public class CategoryJsonConverter {

	public Category convertFrom(final String json) {
		final JsonObject jsonObject = JsonReader.readAsJsonObject(json);

		final Category category = new Category();
		category.setName(JsonReader.getStringOrNull(jsonObject, "name"));

		return category;
	}

	public JsonElement convertToJsonElement(final Category category) {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", category.getId());
		jsonObject.addProperty("name", category.getName());
		return jsonObject;
	}

	public JsonElement convertToJsonElement(final List<Category> categories) {
		final JsonArray jsonArray = new JsonArray();

		for (final Category category : categories) {
			jsonArray.add(convertToJsonElement(category));
		}

		return jsonArray;
	}

}