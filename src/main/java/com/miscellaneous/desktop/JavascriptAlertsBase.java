package com.miscellaneous.desktop;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class JavascriptAlertsBase extends AbstractPage {

	public JavascriptAlertsBase(WebDriver driver) {
		super(driver);
	}
	
	public abstract void clickJsPrompt();
	
	public abstract String getResult();

}
