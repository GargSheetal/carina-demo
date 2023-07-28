package com.saucedemo.gui.pages.common;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.saucedemo.gui.pages.desktop.CartPage;
import com.saucedemo.gui.pages.desktop.LoginPage;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class HeaderMenuBase extends AbstractUIObject {

	public HeaderMenuBase(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}
	
	public HeaderMenuBase(WebDriver driver) {
		super(driver);
	}

	public abstract LoginPage clickLogout();
	 
	public abstract CartPage selectCart();
	
	public abstract String readCartSize();
}
