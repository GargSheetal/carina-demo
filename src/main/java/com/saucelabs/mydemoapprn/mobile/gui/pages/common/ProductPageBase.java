package com.saucelabs.mydemoapprn.mobile.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class ProductPageBase extends AbstractPage {

	public ProductPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract String readProductName();
	
	public abstract String readProductDescription();
	
	public abstract String readProductPrice();
	
	public abstract void clickAddToCart();
	
	public abstract void swipeToProductDescription();
}
