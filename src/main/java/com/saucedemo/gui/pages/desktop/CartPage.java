package com.saucedemo.gui.pages.desktop;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucedemo.gui.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {
	
	@FindBy(className = "cart_list")
    private List<CartItem> items;
	
	@FindBy(xpath = "//span[@class='title']")
    private ExtendedWebElement pageTitle;
	
	@FindBy(id = "checkout")
    private ExtendedWebElement btnCheckout;
	
	public CartPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(pageTitle);
	}

	public List<CartItem> getItems() {
		return items;
	}
	
	public CheckoutInfoPage clickCheckout() {
		btnCheckout.click();
		return new CheckoutInfoPage(driver);
	}
	
//	@Override
//	public void validateCart() {
//		for(ProductItem item: items) {
//			String quantity = readItemQuantity();
//			String itemName = readItemName();
//			String price = readItemPrice();
//			SoftAssert softAssert = new SoftAssert();
//			softAssert.assertEquals(itemName, "Sauce Labs Backpack", "Invalid Item name!");
//			softAssert.assertEquals(quantity, "1", "Invalid Item quantity!");
//			softAssert.assertEquals(price, "$29.99", "Invalid Item price!");
//			softAssert.assertAll();
//			System.out.println("Item Details : " + quantity + " | " + itemName + " | " + price);
//		}
//	}

}
