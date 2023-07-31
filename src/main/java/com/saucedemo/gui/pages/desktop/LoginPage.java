package com.saucedemo.gui.pages.desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucedemo.gui.pages.common.LoginPageBase;
import com.saucedemo.gui.pages.common.HomePageBase;
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
		Properties prop = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(new File("src/main/resources/_config.properties"));
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String uname = prop.getProperty("username_saucedemo");
		String pwd = prop.getProperty("password_saucedemo");
		LOGGER.info("Entering UserName : " + uname);
		assertElementPresent(txtUserName);
		txtUserName.type(uname);
		LOGGER.info("Entering Password : " + pwd);
		assertElementPresent(txtPassword);
		txtPassword.type(pwd);
		btnLogin.click();
		return initPage(getDriver(), HomePageBase.class);
	}
	
}
