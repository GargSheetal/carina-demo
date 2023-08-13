package com.bestbuy.gui.pages.desktop;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.bestbuy.gui.pages.common.TopMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = TopMenuBase.class)
public class TopMenu extends TopMenuBase {

	@FindBy(css = "button[aria-label='Menu']")
	private ExtendedWebElement burgerMenu;
	
	@FindBy(css = ".liDropdownList")
	private List<ExtendedWebElement> listDepartments;
	
//	@FindBy(css = "button[aria-label='Menu']")
//	ExtendedWebElement burgerMenu;
//	
//	@FindBy(css = "button[aria-label='Menu']")
//	ExtendedWebElement burgerMenu;
	
	public TopMenu(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	@Override
	public List<ExtendedWebElement> getDepartmentList() {
		return listDepartments;
	}
	
	@Override
	public void clickMenu() {
		burgerMenu.assertElementPresent();
		burgerMenu.click();
	}
	
}
