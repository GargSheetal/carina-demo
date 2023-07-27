package com.saucedemo.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class CheckoutInfoPage extends AbstractUIObject {

	@FindBy(xpath = "//input[@id='first-name']")
	private ExtendedWebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='last-name']")
	private ExtendedWebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='postal-code']")
	private ExtendedWebElement txtZipCode;
	
	@FindBy(xpath = "//input[@id='continue']")
	private ExtendedWebElement btnContinue;
	
	public CheckoutInfoPage(WebDriver driver) {
		super(driver);
	}
	
	public void setFirstName(String firstName) {
		assertElementPresent(txtFirstName);
		txtFirstName.type(firstName);
	}
	
	public void setLastName(String lastName) {
		assertElementPresent(txtLastName);
		txtLastName.type(lastName);
	}
	
	public void setZipCode(String zipcode) {
		assertElementPresent(txtZipCode);
		txtZipCode.type(zipcode);
	}
	
	public CheckoutOverviewPage clickContinue() {
		btnContinue.assertElementPresent();
		btnContinue.click();
		return new CheckoutOverviewPage(getDriver());
	}
}
