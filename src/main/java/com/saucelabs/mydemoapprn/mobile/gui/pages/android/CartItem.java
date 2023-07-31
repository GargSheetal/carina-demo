package com.saucelabs.mydemoapprn.mobile.gui.pages.android;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.CartItemBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartItemBase.class)
public class CartItem extends CartItemBase implements IMobileUtils{

	@ExtendedFindBy(accessibilityId = "product label")
	private ExtendedWebElement itemName;
	
	public CartItem(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	public String readItemName() {
		return itemName.getText();
	}

}
