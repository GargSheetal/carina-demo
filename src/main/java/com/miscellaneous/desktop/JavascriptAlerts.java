package com.miscellaneous.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = JavascriptAlertsBase.class)
public class JavascriptAlerts extends JavascriptAlertsBase {

	@FindBy(xpath = "//button[text()='Click for JS Prompt']")
	private ExtendedWebElement jsPrompt;
	
	@FindBy(id = "result")
	private ExtendedWebElement result;
	
	public JavascriptAlerts(WebDriver driver) {
		super(driver);
	}
	
	public void clickJsPrompt() {
		jsPrompt.assertElementPresent();
		jsPrompt.click();
	}
	
	public String getResult() {
		return result.getText();
	}
	

}
