package com.saucelabs.mydemoapprn.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class MenuPageBase extends AbstractPage {

	public MenuPageBase(WebDriver driver) {
		super(driver);
	}
	
	public abstract LoginPageBase clickLoginButton();
	
	public abstract String getLogoutMsg();
	
	public abstract void clickLogout();
	
	public abstract void clickLogoutOnFrame();
	
	public abstract LoginPageBase clickOKBtn();
}
