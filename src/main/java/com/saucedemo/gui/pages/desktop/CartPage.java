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
	
	@Override
	public CheckoutInfoPage clickCheckout() {
		assertElementPresent(btnCheckout);
		btnCheckout.click();
		return new CheckoutInfoPage(getDriver());
	}
	

}
