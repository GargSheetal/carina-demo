package com.miscellaneous.desktop;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;

public abstract class TextCompareBase extends AbstractPage {

	public TextCompareBase(WebDriver driver) {
		super(driver);
	}
	
	public abstract void sendText(String text);

	public abstract void performCopyPasteAction() throws AWTException;
	
	public abstract ExtendedWebElement getTextArea1();
	
	public abstract ExtendedWebElement getTextArea2();
}
