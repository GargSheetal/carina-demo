package com.chase.gui.pages.desktop;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class LoginPageBase extends AbstractPage {

	public LoginPageBase(WebDriver driver) {
		super(driver);
	}

	public abstract Frame getIframe();
}
