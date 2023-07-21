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
		getSingleProductMethod.callAPIExpectSuccess();
		getSingleProductMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
		getSingleProductMethod.validateResponseAgainstSchema("api/products/_get/rs_singleproduct.schema");
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testCreateProduct() {
		PostProductMethod postProductMethod = new PostProductMethod();
		postProductMethod.setProperties("api/products/product.properties");
		postProductMethod.callAPIExpectSuccess();
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

