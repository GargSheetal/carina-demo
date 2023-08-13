package com.bestbuy.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.bestbuy.gui.pages.common.HomePageBase;
import com.bestbuy.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

	@FindBy(id = "fld-e")
	ExtendedWebElement txtEmail;
	
	@FindBy(id = "fld-p1")
	ExtendedWebElement txtPassword;
	
	@FindBy(xpath = "//button[text()='Sign In']")
	ExtendedWebElement btnSignIn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(txtEmail);
	}
	
	@Override
	public HomePageBase performLogin() {
		String email = Configuration.getRequired("username");
		String pass = R.CONFIG.get("password");
		String password = Configuration.getRequired("password");
		
		txtEmail.assertElementPresent();
		txtEmail.type(email);
		txtPassword.assertElementPresent();
		txtPassword.type(pass);
		btnSignIn.assertElementPresent();
		btnSignIn.click();
		return initPage(getDriver(), HomePageBase.class);
	}
	

}
