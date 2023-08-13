package com.automation.practice.web;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.miscellaneous.desktop.JavascriptAlertsBase;
import com.zebrunner.carina.core.IAbstractTest;

public class JsPromptAlertTest implements IAbstractTest {
	
	@Test
	public void testJsPrompt() throws InterruptedException {
		JavascriptAlertsBase alertBase = initPage(getDriver(), JavascriptAlertsBase.class);
		alertBase.open();
		alertBase.clickJsPrompt();
		Thread.sleep(3000);
		String actText = "Performing JsPrompt Test";
		Alert alert = getDriver().switchTo().alert();
		Thread.sleep(3000);
		alert.sendKeys(actText);
		alert.accept();
		String expText = alertBase.getResult();
		Assert.assertTrue(expText.contains(actText), "Input Text Mismatch");
		
	}

}
