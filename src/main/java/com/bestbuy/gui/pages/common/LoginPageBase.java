package com.bestbuy.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class LoginPageBase  extends AbstractPage {

	public LoginPageBase(WebDriver driver) {
		super(driver);
	}
	
	public abstract HomePageBase performLogin();

}
