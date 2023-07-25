package com.saucedemo.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class HomePageBase extends AbstractPage {

	public HomePageBase(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
	}
	
	public abstract ProductsPageBase performLogin(String username, String password);
	
	public void open() {
		super.open();
	}
	
}
