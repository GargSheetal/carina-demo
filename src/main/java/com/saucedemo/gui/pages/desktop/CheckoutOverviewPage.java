package com.saucedemo.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class CheckoutOverviewPage extends AbstractUIObject {

	@FindBy(id = "finish")
	private ExtendedWebElement btnFinish;
	
	public CheckoutOverviewPage(WebDriver driver) {
		super(driver);
	}

	public OrderConfirmationPage clickFinish() {
		btnFinish.assertElementPresent();
		btnFinish.click();
		return new OrderConfirmationPage(driver);
	}
}
