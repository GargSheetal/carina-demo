package com.saucelabs.mydemoapprn.mobile.gui.pages.common;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class CartItemBase extends AbstractUIObject {

	public CartItemBase(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	public abstract String readItemName();
}
