package com.saucedemo.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.saucedemo.gui.pages.desktop.HeaderMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class ProductsPageBase  extends AbstractPage{

	public ProductsPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract HeaderMenu getHeaderMenu();
	
	public abstract ItemInfoPageBase selectProduct(String product);
	
//	public abstract void verifyAllLinks();
	
	public abstract void addProductToCart(String product);
	
	public abstract void selectSortOrder(String text);
	
	public abstract int getProductsLinkSize();
	
	public abstract void verifyProductList();
	
}
