package com.miscellaneous.desktop;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = TextCompareBase.class)
public class TextCompare extends TextCompareBase{
	
	@FindBy(id = "inputText1")
	private ExtendedWebElement textArea1;
	
	@FindBy(id = "inputText2")
	private ExtendedWebElement textArea2;
	
	public TextCompare(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(textArea1);
	}

	public void sendText(String text) {
		textArea1.type(text);
	}
	
	public void performCopyPasteAction() throws AWTException {
		Actions action = new Actions(getDriver());
		// command+a
		action.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).perform();
		// command+c
		action.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();
		// click tab
		action.sendKeys(Keys.TAB).perform();
		// command+v
		action.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();
	}
	
	public ExtendedWebElement getTextArea1() {
		return textArea1;
	}
	
	public ExtendedWebElement getTextArea2() {
		return textArea2;
	}
}
