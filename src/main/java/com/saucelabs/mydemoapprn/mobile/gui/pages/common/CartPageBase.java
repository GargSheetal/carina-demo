package com.saucelabs.mydemoapprn.mobile.gui.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class CartPageBase extends AbstractPage {

	public CartPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract String getTotalItems();
	
	public abstract String getTotalPrice();
	
	public abstract double calculateTotalPrice();
	
	public abstract LoginPageBase clickProceedToCheckout();
	
	public abstract List<? extends CartItemBase> getCartItems();
	
}
