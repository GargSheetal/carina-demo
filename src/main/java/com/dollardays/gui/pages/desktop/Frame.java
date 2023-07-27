package com.dollardays.gui.pages.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public class Frame extends AbstractPage {

	@FindBy(css = ".recaptcha-checkbox-border")
	private ExtendedWebElement checkNotARobot;
	
	public Frame(WebDriver driver) {
		super(driver);
	}

	public void click() {
		checkNotARobot.click();
	}
}

