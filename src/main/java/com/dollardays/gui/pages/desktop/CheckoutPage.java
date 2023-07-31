package com.dollardays.gui.pages.desktop;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class CheckoutPage extends AbstractUIObject {

	@FindBy(css = "div[class='price_text'] h3")
    private ExtendedWebElement price;
	
	@FindBy(xpath = "//div[@class='order_histry']//li[1]//span[1]")
    private ExtendedWebElement subtotal;
	
	@FindBy(xpath = "//div[@class='sku_titel']//h4//a")
    private ExtendedWebElement itemName;
	
	@FindBy(xpath = "//span[contains(text(),'Price per Unit: ')]")
    private ExtendedWebElement pricePerUnit;
	
	@FindBy(xpath = "//p[@class='piece-qty']")
    private ExtendedWebElement unitsPerCase;
	
	@FindBy(xpath = "//input[@class='form-quantity']")
    private ExtendedWebElement quantity;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	public String getPrice() {
		return price.getText();
	}

	public String getSubtotal() {
		return subtotal.getText();
	}

	public String getItemName() {
		return itemName.getText();
	}

	public String getPricePerUnit() {
		return pricePerUnit.getText();
	}

	public String getUnitsPerCase() {
		return unitsPerCase.getText();
	}

	public String getQuantity() {
		return quantity.getText();
	}

	
	

}
