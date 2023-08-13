package com.zebrunner.carina.demo;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import com.solvd.automation.carina.demo.dummyjsonapi.DeleteProductMethod;
import com.solvd.automation.carina.demo.dummyjsonapi.GetAllProductsMethod;
import com.solvd.automation.carina.demo.dummyjsonapi.GetSingleProductMethod;
import com.solvd.automation.carina.demo.dummyjsonapi.PostProductMethod;
import com.solvd.automation.carina.demo.dummyjsonapi.PutProductMethod;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DummyJsonApiSampleTest implements IAbstractTest {
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testGetAllProducts() {
		GetAllProductsMethod getAllProductsMethod = new GetAllProductsMethod();
		getAllProductsMethod.callAPIExpectSuccess();
		getAllProductsMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
		getAllProductsMethod.validateResponseAgainstSchema("api/products/_get/rs.schema");
	}

	@Test()
	@MethodOwner(owner = "sheetal")
	public void testGetSingleProduct() {
		GetSingleProductMethod getSingleProductMethod = new GetSingleProductMethod();
		Response response = getSingleProductMethod.callAPIExpectSuccess();
		String responseBody = response.getBody().asString();
		JsonPath jsonPath = new JsonPath(responseBody);
		String title = jsonPath.get("title");
	//	String id = jsonPath.getString("id");
		int id = jsonPath.getInt("id");
		System.out.println("Properties of the product - Title : " + title + " | Id : " + id);
		getSingleProductMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
		getSingleProductMethod.validateResponseAgainstSchema("api/products/_get/rs_singleproduct.schema");
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testCreateProduct() {
		PostProductMethod postProductMethod = new PostProductMethod();
		postProductMethod.setProperties("api/products/product.properties");
		JsonPath rs = postProductMethod.callAPIExpectSuccess().jsonPath();
		System.out.println("Price : " + rs.getString("price"));
		postProductMethod.validateResponse();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testUpdateProduct() {
		PutProductMethod putProductMethod = new PutProductMethod();
		putProductMethod.callAPIExpectSuccess();
		putProductMethod.validateResponse();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testDeleteProduct() {
		DeleteProductMethod deleteProductMethod = new DeleteProductMethod();
		deleteProductMethod.callAPIExpectSuccess();
		deleteProductMethod.validateResponse();
	}
}

