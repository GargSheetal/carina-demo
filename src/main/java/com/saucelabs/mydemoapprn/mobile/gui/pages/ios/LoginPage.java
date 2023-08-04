package com.saucelabs.mydemoapprn.mobile.gui.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.HomePageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.factory.DeviceType.Type;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.decorator.annotations.ClassChain;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = Type.IOS_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase implements IMobileUtils{

	@ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == \"Login\"`]")
	private ExtendedWebElement title;
	
	@ExtendedFindBy(accessibilityId  = "Username input field")
	private ExtendedWebElement txtUsername;
	
	@ExtendedFindBy(accessibilityId = "Password input field")
	private ExtendedWebElement txtPassword;
	
	@ExtendedFindBy(accessibilityId = "Login button")
	private ExtendedWebElement btnLogin;
	
	@ExtendedFindBy(accessibilityId = "Sorry, this user has been locked out.")
	private ExtendedWebElement userLockedOutMsg;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(txtUsername);
	}

	public String getUserLockedOutMsg() {
		return userLockedOutMsg.getText();
	}
	
	public String getPageTitle() {
		return title.getText();
	}
	
	@Override
	public HomePageBase performLogin() {
		String username = Configuration.getRequired("capabilities.username");
		String password = Configuration.getRequired("capabilities.password");
		assertElementPresent(txtUsername);
		txtUsername.type(username);
		assertElementPresent(txtPassword);
		txtPassword.type(password);
		assertElementPresent(btnLogin);
		btnLogin.click();
		return new HomePage(getDriver());
	}
	
	public HomePageBase loginDataDriven(String username, String password) {
		assertElementPresent(txtUsername);
		txtUsername.type(username);
		assertElementPresent(txtPassword);
		txtPassword.type(password);
		assertElementPresent(btnLogin);
		btnLogin.click();
		return new HomePage(getDriver());
	}

}
