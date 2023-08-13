package com.bestbuy.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.bestbuy.gui.pages.desktop.TopMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class HomePageBase extends AbstractPage {

	public HomePageBase(WebDriver driver) {
		super(driver);
	}

	public abstract TopMenu getTopMenu();
	
	public abstract LoginPageBase clickSignIn();
	
	public abstract String getUserDisplayName();
	
	public abstract void isSignoutPresent();
}
