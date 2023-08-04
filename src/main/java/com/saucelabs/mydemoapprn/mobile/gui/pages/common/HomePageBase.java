package com.saucelabs.mydemoapprn.mobile.gui.pages.common;


import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class HomePageBase extends AbstractPage {
	
	public HomePageBase(WebDriver driver) {
		super(driver);
	}
	
	public abstract String getPageTitle();
	
	public abstract MenuPageBase clickBurgerMenu();
	
	public abstract CartPageBase clickCartIcon();
	
	public abstract String getCartSize();
	
	public abstract ProductPageBase selectProduct(String product);
	
	public abstract HomePageBase goBack();
}
