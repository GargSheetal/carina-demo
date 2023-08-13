package com.bestbuy.gui.pages.common;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public abstract class TopMenuBase extends AbstractUIObject {

	public TopMenuBase(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	public abstract List<ExtendedWebElement> getDepartmentList();
	
	public abstract void clickMenu();
}
