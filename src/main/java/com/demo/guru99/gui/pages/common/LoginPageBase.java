package com.demo.guru99.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class LoginPageBase extends AbstractPage {

	public LoginPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract void setUserId(String userId);
	
	public abstract void setPassword(String password);
	
	public abstract void clickLogin();
	
	public abstract void performLogin(String username, String password);
}
