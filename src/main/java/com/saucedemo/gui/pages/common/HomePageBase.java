package com.saucedemo.gui.pages.common;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.saucedemo.gui.pages.desktop.HeaderMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class HomePageBase  extends AbstractPage{

	public HomePageBase(WebDriver driver) {
		super(driver);
	}

	public abstract HeaderMenu getHeaderMenu();
	
	public abstract ProductPageBase selectProduct(String product);
	
	public abstract void addProductToCart(String product);
	
	public abstract String selectSortOrder(String text);
	
	public abstract List<ExtendedWebElement> getProductsLink();

	
}
