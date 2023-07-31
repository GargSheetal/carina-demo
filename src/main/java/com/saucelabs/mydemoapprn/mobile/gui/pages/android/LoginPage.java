package com.saucelabs.mydemoapprn.mobile.gui.pages.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.HomePageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils{

	@ExtendedFindBy(accessibilityId  = "Username input field")
	private ExtendedWebElement txtUsername;
	
	@ExtendedFindBy(accessibilityId = "Password input field")
	private ExtendedWebElement txtPassword;
	
	@ExtendedFindBy(accessibilityId = "Login button")
	private ExtendedWebElement btnLogin;
	
	@FindBy(xpath = "//android.widget.TextView[@index='1']")
	private ExtendedWebElement loginMessage;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(txtUsername);
	}

	public String getLoginMessage() {
		return loginMessage.getText();
	}
	
	@Override
	public HomePageBase performLogin() {
//		Properties prop = new Properties();
//		try {
//			FileInputStream inputStream = new FileInputStream(new File("src/main/resources/_config.properties"));
//			prop.load(inputStream);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String username = prop.getProperty("username");
//		String password = prop.getProperty("password");
		
		String username = "bob@example.com";
		String password = "10203040";
		assertElementPresent(txtUsername);
		txtUsername.type(username);
		assertElementPresent(txtPassword);
		txtPassword.type(password);
		assertElementPresent(btnLogin);
		btnLogin.click();
		return new HomePage(getDriver());
	}

}
