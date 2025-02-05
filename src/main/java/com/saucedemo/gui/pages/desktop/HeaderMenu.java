package com.saucedemo.gui.pages.desktop;

import java.lang.invoke.MethodHandles;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.gui.pages.common.LoginPageBase;
import com.saucedemo.gui.pages.common.CartPageBase;
import com.saucedemo.gui.pages.common.HeaderMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HeaderMenuBase.class)
public class HeaderMenu extends HeaderMenuBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	public HeaderMenu(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	@FindBy(id = "react-burger-menu-btn")
	private ExtendedWebElement btnBurgerMenu;
	
	@FindBy(xpath = ".//a[@id='logout_sidebar_link']")
	private ExtendedWebElement linkLogout;
	
	@FindBy(className = "shopping_cart_link")
	private ExtendedWebElement linkCart;
	
	@FindBy(className = "shopping_cart_badge")
	private ExtendedWebElement cartSize;
	
	@Override
	public LoginPage clickLogout() {
		assertElementPresent(btnBurgerMenu);
		btnBurgerMenu.click();
		assertElementPresent(linkLogout);
		linkLogout.hover();
		LOGGER.info("Clicking Logout link");
		linkLogout.click();
		return new LoginPage(getDriver());
	}

	@Override
	public CartPage selectCart() {
		linkCart.assertElementPresent();
		linkCart.click();
		return new CartPage(getDriver());
	}
	
	@Override
	public String readCartSize() {
		assertElementPresent(cartSize);
		return cartSize.getText();
	}
}
