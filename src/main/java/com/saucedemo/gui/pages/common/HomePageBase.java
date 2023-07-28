package com.saucedemo.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucedemo.gui.pages.desktop.HeaderMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class HomePageBase  extends AbstractPage{

	public HomePageBase(WebDriver driver) {
		super(driver);
	}

	public abstract HeaderMenu getHeaderMenu();
	
	public abstract ProductPageBase selectProduct(String product);
	
	public abstract void addProductToCart(String product);
	
	public abstract void selectSortOrder(String text);
	
	public abstract void verifyProductList();
	
}
