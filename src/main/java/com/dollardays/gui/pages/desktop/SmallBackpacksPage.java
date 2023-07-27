package com.dollardays.gui.pages.desktop;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class SmallBackpacksPage extends AbstractUIObject {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@FindBy(xpath = "//div[@class='box-product']")
	private List<ExtendedWebElement> listBackpacks;
	
//	@FindBy(xpath = "//input[contains(@value,'Add to Cart')]")
	@FindBy(css = "input[class='btn btn-quick-view jqatc fsig']")
	private ExtendedWebElement btnAddToCart;
	
	public SmallBackpacksPage(WebDriver driver) {
		super(driver);
	}

	public void selectBackpackFromList(String product) {
		for(ExtendedWebElement backpack: listBackpacks) {
			LOGGER.info("Selecting product : " + product);
			String currentProduct = backpack.getText();
			if(currentProduct.contains(product)) {
				btnAddToCart.clickByJs();
			}
		}
	}
}
