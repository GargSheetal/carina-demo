package com.chase.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

	@FindBy(id = "routablecpologonbox")
	private ExtendedWebElement iframe;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public Frame getIframe() {
		Frame frame = new Frame(getDriver());
		getDriver().switchTo().frame(iframe.getElement());
		return frame;
	}
	
}
