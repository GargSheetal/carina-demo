package com.saucelabs.mydemoapprn.mobile.gui.pages.ios;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.CartPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.HomePageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.MenuPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.decorator.annotations.ClassChain;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase implements IMobileUtils{

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@ExtendedFindBy(accessibilityId  = "tab bar option menu")
	private ExtendedWebElement menu;
	
	@ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == \"Products\"`]")
	private ExtendedWebElement title;
	
	@ExtendedFindBy(accessibilityId = "tab bar option cart")
	private ExtendedWebElement cart;
	
	@ExtendedFindBy(accessibilityId = "navigation back button")
	private ExtendedWebElement btnBack;
	
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name=\"store item text\"]")
	private List<ExtendedWebElement> productsLink;
	
	@ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == \"Sauce Labs Backpack\"`]")
	private ExtendedWebElement sauceLabsBackpackName;
	
	@ExtendedFindBy(iosClassChain = "**/XCUIElementTypeStaticText[`label == \"$29.99\"`]")
	private ExtendedWebElement sauceLabsBackpackPrice;
	
	public HomePage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(title);
	}
	
	public String getPageTitle() {
		return title.getText();
	}

	@Override
	public MenuPageBase clickBurgerMenu() {
		menu.click();
		return new MenuPage(getDriver());
	}

	@Override
	public ProductPageBase selectProduct(String product) {
		for(ExtendedWebElement link: productsLink) {
			LOGGER.info("Selecting product : " + product);
			String currentProduct = link.getText();
			if(product.equalsIgnoreCase(currentProduct)) {
				link.click();
				return initPage(getDriver(), ProductPageBase.class);
			}
		}
		throw new RuntimeException("Unable to open product : " + product);
	}

	public CartPageBase clickCartIcon() {
		assertElementPresent(cart);
		cart.click();
		return initPage(getDriver(), CartPageBase.class);
	}

	public String getCartSize() {
		return cart.getAttribute("label");
	}
	
	public HomePageBase goBack() {
		btnBack.click();
		return initPage(getDriver(), HomePageBase.class);
	}

	@Override
	public String readSauceLabsBackpackName() {
		return sauceLabsBackpackName.getText();
	}

	@Override
	public String readSauceLabsBackpackPrice() {
		return sauceLabsBackpackPrice.getText();
	}
}
