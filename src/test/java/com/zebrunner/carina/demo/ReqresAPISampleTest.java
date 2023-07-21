package com.zebrunner.carina.demo;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import com.solvd.automation.carina.demo.reqresapi.GetAllUsersMethod;
import com.solvd.automation.carina.demo.reqresapi.GetUserNotFoundMethod;
import com.solvd.automation.carina.demo.reqresapi.PostNewUserMethod;
import com.solvd.automation.carina.demo.reqresapi.PutUserMethod;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class ReqresAPISampleTest implements IAbstractTest {
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void getAllUsers() {
		GetAllUsersMethod getAllUsersMethod = new GetAllUsersMethod();
		getAllUsersMethod.callAPIExpectSuccess();
		getAllUsersMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
		getAllUsersMethod.validateResponseAgainstSchema("api/reqres_users/_get/rs.schema");
	}

	@Test()
	@MethodOwner(owner = "sheetal")
	public void testCreateUser() {
		PostNewUserMethod postNewUserMethod = new PostNewUserMethod();
		postNewUserMethod.setProperties("api/reqres_users/reqres_user.properties");
		postNewUserMethod.callAPIExpectSuccess();
		postNewUserMethod.validateResponse();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testPutUser() {
		PutUserMethod putUserMethod = new PutUserMethod();
		putUserMethod.callAPIExpectSuccess();
		putUserMethod.validateResponse();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testUserNotFound() {
		GetUserNotFoundMethod getUserNotFoundMethod = new GetUserNotFoundMethod();
		getUserNotFoundMethod.callAPIExpectSuccess();
		getUserNotFoundMethod.validateResponse();
	}
}
