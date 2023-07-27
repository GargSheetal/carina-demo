package com.saucedemo.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucedemo.gui.pages.desktop.CheckoutInfoPage;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class CartPageBase extends AbstractPage {

	public CartPageBase(WebDriver driver) {
		super(driver);
	}
	
	public abstract CheckoutInfoPage clickCheckout();

}
