package com.dollardays.gui.pages.desktop;

import java.lang.invoke.MethodHandles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dollardays.gui.pages.common.HomePageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class LoginPage extends AbstractUIObject {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@FindBy(name = "username")
	private ExtendedWebElement txtUsername;
	
	@FindBy(name = "password")
	private ExtendedWebElement txtPassword;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	private ExtendedWebElement btnSignIn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void setUsername(String username) {
		assertElementPresent(txtUsername);
		LOGGER.info("Entered username : " + username);
		txtUsername.type(username);
	}
	
	public void setPassword(String password) {
		assertElementPresent(txtPassword);
		LOGGER.info("Entered password : " + password);
		txtPassword.type(password);
	}
	
	public HomePageBase clickSignIn() {
		assertElementPresent(btnSignIn);
		btnSignIn.click();
		return new HomePage(getDriver());
	}
}
