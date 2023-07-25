package com.saucedemo.gui.pages.desktop;

import java.lang.invoke.MethodHandles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.gui.pages.common.HomePageBase;
import com.saucedemo.gui.pages.common.ProductsPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	 public HomePage(WebDriver driver) {
		super(driver);
		setUiLoadedMarker(txtPassword);
	}

	@FindBy(id = "user-name")
	private ExtendedWebElement txtUserName;
	
	@FindBy(id = "password")
	private ExtendedWebElement txtPassword;
	
	@FindBy(id = "login-button")
	private ExtendedWebElement btnLogin;
	
	@Override
	public ProductsPageBase performLogin(String username, String password) {
		LOGGER.info("Entering UserName : " + username);
		txtUserName.type(username);
		LOGGER.info("Entering Password : " + password);
		txtPassword.type(password);
		btnLogin.click();
		return initPage(getDriver(), ProductsPageBase.class);
	}
	
}
