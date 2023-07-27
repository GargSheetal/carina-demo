package com.saucedemo.gui.pages.desktop;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucedemo.gui.pages.common.CartItemBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CartItemBase.class)
public class CartItem extends CartItemBase{

	@FindBy(css = ".cart_quantity")
    private ExtendedWebElement itemQuantity;
	
	@FindBy(css = ".inventory_item_name")
    private ExtendedWebElement itemName;
	
	@FindBy(css = ".inventory_item_price")
    private ExtendedWebElement itemPrice;
	
	public CartItem(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}
	
	@Override
	public String readItemQuantity() {
		assertElementPresent(itemQuantity);
		return itemQuantity.getText();
	}

	@Override
	public String readItemName() {
		assertElementPresent(itemName);
		return itemName.getText();
	}

	@Override
	public String readItemPrice() {
		assertElementPresent(itemPrice);
		return itemPrice.getText();
	}
}
