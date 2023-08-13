package com.bestbuy.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.bestbuy.gui.pages.common.HomePageBase;
import com.bestbuy.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

	
	@FindBy(css = ".v-p-right-xxs.line-clamp")
	private ExtendedWebElement account;
	
	@FindBy(xpath = "//a[text()='Sign In']")
	private ExtendedWebElement linkSignIn;
	
	@FindBy(id = "logout-button-bby")
	private ExtendedWebElement btnSignout;
	
	@FindBy(css = ".top-nav")
	private TopMenu topMenu;
	
	public HomePage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(account);
	}
	
	public TopMenu getTopMenu() {
		return topMenu;
	}

	@Override
	public LoginPageBase clickSignIn() {
		account.assertElementPresent();
		account.click();
		linkSignIn.assertElementPresent();
		linkSignIn.click();
		return initPage(getDriver(), LoginPageBase.class);
	}
	
	@Override
	public String getUserDisplayName() {
		account.assertElementPresent();
		return account.getText();
	}
	
	@Override
	public void isSignoutPresent() {
		account.click();
		btnSignout.assertElementPresent();
	}
	

}
