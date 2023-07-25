package com.saucedemo.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class ItemInfoPageBase extends AbstractPage {

	protected ItemInfoPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract String readItemName();
	
	public abstract String readItemDescription();
	
	public abstract String readItemPrice();
	
	public abstract void clickAddToCart();
	
}
