package com.dollardays.gui.pages.common;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class HeaderMenuBase extends AbstractUIObject {

	public HeaderMenuBase(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

}
