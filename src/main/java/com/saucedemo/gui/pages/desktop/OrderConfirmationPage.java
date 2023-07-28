package com.saucedemo.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class OrderConfirmationPage extends AbstractUIObject{

	@FindBy(id = "back-to-products")
	private ExtendedWebElement btnBackHome;
	
	@FindBy(xpath = "//h2[text()='Thank you for your order!']")
	private ExtendedWebElement txtOrderConfirmation;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public HomePage clickBackHome() {
		btnBackHome.assertElementPresent();
		btnBackHome.click();
		return new HomePage(getDriver());
	}
	
	public String verifyOrderConfirmationText() {
		assertElementPresent(txtOrderConfirmation);
		String confirmationText = txtOrderConfirmation.getText();
		return confirmationText;
	}
}
