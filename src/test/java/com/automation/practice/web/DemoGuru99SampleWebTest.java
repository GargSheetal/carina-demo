package com.automation.practice.web;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.guru99.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.CsvDataSourceParameters;

public class DemoGuru99SampleWebTest implements IAbstractTest, IAbstractDataProvider {
	
	@Test(dataProvider = "DataProvider")
	@CsvDataSourceParameters(path = "data_source/guruDemoLogin.csv", dsUid = "TUID", dsArgs = "username, password")
	public void testLoginDataDriven(List<Map<String, String>> args) throws InterruptedException {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
//		String username = args.get(0).get("username");
//		String password = args.get(1).get("password");
		String username = null;
		String password = null;
		for(Map<String, String> row: args) {
			username = row.get("username");
			password = row.get("password");
			loginPage.setUserId(username);
			loginPage.setPassword(password);
			loginPage.clickLogin();
		}
//		loginPage.performLogin(username, password);
//		if(expResult.equals(false)) {
//			String actMessage = getDriver().switchTo().alert().getText();
//			getDriver().switchTo().defaultContent();
//			String expMessage = "User or Password is not valid";
//			Assert.assertEquals(actMessage, expMessage, "Invalid Error Message");
//		}
		
//		if(expResult.equals(false)) {
//			Alert alert = getDriver().switchTo().alert();
//			Thread.sleep(2000);
//			String actMessage = alert.getText();
//			Thread.sleep(2000);
//			alert.accept();
//			getDriver().switchTo().defaultContent();
//			String expMessage = "User or Password is not valid";
//			Assert.assertEquals(actMessage, expMessage, "Invalid Error Message");
//		}
	}
	
	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	@DataProvider(name = "loginData")
	public static Object[][] loginData() {
		return new Object[][] {
			{"mngr521282", "mUtUsyt", true},
			{"mngr83640", "qAbUzyj", false}
		};
	}
	
	@Test()
	public void testNegativeLogin() throws InterruptedException {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
		loginPage.performLogin("mngr83640", "qAbUzyj");
//		System.out.println("Before alert");
//		Thread.sleep(3000);
//		boolean status = isAlertPresent();
//		System.out.println("Alert present is : " + status);
//		if(isAlertPresent() == true) {
//			Alert alert = getDriver().switchTo().alert();
//			Thread.sleep(2000);
//			String actMessage = alert.getText();
//			Thread.sleep(2000);
//			alert.accept();
//			getDriver().switchTo().defaultContent();
//			String expMessage = "User or Password is not valid";
//			Assert.assertEquals(actMessage, expMessage, "Invalid Error Message");
//		}
	}
}
