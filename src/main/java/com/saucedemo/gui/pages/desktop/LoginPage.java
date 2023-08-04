package com.saucedemo.gui.pages.desktop;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.gui.pages.common.LoginPageBase;
import com.saucedemo.gui.pages.common.HomePageBase;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	 public LoginPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(txtPassword);
	}

	@FindBy(id = "user-name")
	private ExtendedWebElement txtUserName;
	
	@FindBy(id = "password")
	private ExtendedWebElement txtPassword;
	
	@FindBy(id = "login-button")
	private ExtendedWebElement btnLogin;
	
	@Override
	public HomePageBase performLogin() {
		String username = Configuration.getRequired("username_saucedemo");
		String password = Configuration.getRequired("password_saucedemo");
		LOGGER.info("Entering UserName : " + username);
		assertElementPresent(txtUserName);
		txtUserName.type(username);
		LOGGER.info("Entering Password : " + password);
		assertElementPresent(txtPassword);
		txtPassword.type(password);
		btnLogin.click();
		return initPage(getDriver(), HomePageBase.class);
	}
	
}
