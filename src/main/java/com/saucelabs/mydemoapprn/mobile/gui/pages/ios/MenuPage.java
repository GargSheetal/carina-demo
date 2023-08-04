package com.saucelabs.mydemoapprn.mobile.gui.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.LoginPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.MenuPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.annotations.ClassChain;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = MenuPageBase.class)
public class MenuPage extends MenuPageBase implements IMobileUtils {

	@ExtendedFindBy(accessibilityId = "menu item log in")
	private ExtendedWebElement btnLogin;
	
	@ExtendedFindBy(accessibilityId = "menu item log out")
	private ExtendedWebElement logout;
	
	@ExtendedFindBy(iosClassChain = "**/XCUIElementTypeButton[`label == \"Log Out\"`]")
	private ExtendedWebElement btnLogout;
	
	@ExtendedFindBy(accessibilityId = "OK")
	private ExtendedWebElement btnOK;
	
	@ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == \"You are successfully logged out.\"`]")
	private ExtendedWebElement logoutMsg;
	
	public MenuPage(WebDriver driver) {
		super(driver);
	}

	public LoginPageBase clickLoginButton() {
		assertElementPresent(btnLogin);
		btnLogin.click();
		return new LoginPage(getDriver());
	}
	
	public String getLogoutMsg() {
		return logoutMsg.getText();
	}
	
	public void clickLogout() {
		logout.click();
	}
	
	public void clickLogoutOnFrame() {
		assertElementPresent(btnLogout);
		btnLogout.click();
	}
	
	public LoginPageBase clickOKBtn() {
		assertElementPresent(btnOK);
		btnOK.click();
		return initPage(getDriver(), LoginPageBase.class);
	}
}
