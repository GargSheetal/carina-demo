package com.saucelabs.mydemoapprn.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class LoginPageBase extends AbstractPage {

	public LoginPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract String getPageTitle();
	
	public abstract String getUserLockedOutMsg();
	
	public abstract HomePageBase performLogin();
	
	public abstract HomePageBase loginDataDriven(String username, String password);
}
