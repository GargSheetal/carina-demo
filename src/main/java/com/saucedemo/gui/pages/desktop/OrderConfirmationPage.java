package com.saucedemo.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class OrderConfirmationPage extends AbstractUIObject{

	@FindBy(id = "back-to-products")
	private ExtendedWebElement btnBackToHome;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public ProductsPage clickBackHome() {
		btnBackToHome.assertElementPresent();
		btnBackToHome.click();
		return new ProductsPage(getDriver());
	}
}
