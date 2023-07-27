package com.dollardays.gui.pages.common;

import org.openqa.selenium.WebDriver;

import com.dollardays.gui.pages.desktop.HeaderMenu;
import com.dollardays.gui.pages.desktop.SmallBackpacksPage;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class HomePageBase extends AbstractPage{

	public HomePageBase(WebDriver driver) {
		super(driver);
	}

	public void open() {
		super.open();
	}
	
	public abstract HeaderMenu getHeaderMenu();

}
