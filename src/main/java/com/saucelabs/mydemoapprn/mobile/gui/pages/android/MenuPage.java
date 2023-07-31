package com.saucelabs.mydemoapprn.mobile.gui.pages.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.LoginPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.MenuPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MenuPageBase.class)
public class MenuPage extends MenuPageBase implements IMobileUtils {

	@FindBy(xpath = "//android.view.ViewGroup[@index='8']")
	private ExtendedWebElement btnLogin;
	
	@FindBy(xpath = "//android.view.ViewGroup[@index='9']")
	private ExtendedWebElement logout;
	
	@FindBy(id = "android:id/message")
	private ExtendedWebElement logoutMsg;
	
	@FindBy(id = "android:id/button1")
	private ExtendedWebElement btnLogout;
	
	@FindBy(xpath = "//android.widget.Button[@text='OK']")
	private ExtendedWebElement btnOK;
	
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
