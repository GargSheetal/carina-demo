package com.chase.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class Frame extends AbstractPage {

	@FindBy(name = "userId")
	private ExtendedWebElement username;
	
	@FindBy(name = "password")
	private ExtendedWebElement password;
	
	@FindBy(id = "signin-button")
	private ExtendedWebElement btnSignin;
	
	public Frame(WebDriver driver) {
		super(driver);
	}
	
	public void performLogin() {
		username.assertElementPresent();
		username.type("test1234");
		password.assertElementPresent();
		password.type("password");
	}

}
