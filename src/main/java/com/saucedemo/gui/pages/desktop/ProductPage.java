package com.saucedemo.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucedemo.gui.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase{

	@FindBy(css = ".inventory_details_name.large_size")
    private ExtendedWebElement itemName;
	
	@FindBy(css = ".inventory_details_desc.large_size")
    private ExtendedWebElement itemDescription;
	
	@FindBy(css = ".inventory_details_price")
    private ExtendedWebElement itemPrice;
	
	@FindBy(xpath = "//button[text()='Add to cart']")
    private ExtendedWebElement btnAddToCart;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(itemName);
	}

	@Override
	public String readItemName() {
		itemName.assertElementPresent();
		return itemName.getText();
	}
	
	@Override
	public String readItemDescription() {
		assertElementPresent(itemDescription);
		return itemDescription.getText();
	}

	@Override
	public String readItemPrice() {
		assertElementPresent(itemPrice);
		return itemPrice.getText();
	}

	@Override
	public void clickAddToCart() {
		assertElementPresent(btnAddToCart);
		btnAddToCart.click();
	}


	

}
