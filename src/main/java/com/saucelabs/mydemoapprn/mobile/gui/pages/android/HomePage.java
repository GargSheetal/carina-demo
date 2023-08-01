package com.saucelabs.mydemoapprn.mobile.gui.pages.android;

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
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase implements IMobileUtils{

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@FindBy(css = ".android.widget.ImageView")
	private ExtendedWebElement burgerMenu;
	
	@FindBy(className = "android.widget.TextView")
	private ExtendedWebElement title;
	
	@ExtendedFindBy(accessibilityId = "cart badge")
	private ExtendedWebElement cartIcon;
	
	@FindBy(xpath = "//android.widget.TextView[@index='0']")
	private ExtendedWebElement cartSize;
	
	@FindBy(xpath = "//android.widget.TextView[@content-desc='store item text']")
	private List<ExtendedWebElement> productsLink;
	
	
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
		burgerMenu.click();
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
		assertElementPresent(cartIcon);
		cartIcon.click();
		return initPage(getDriver(), CartPageBase.class);
	}

	public String getCartSize() {
		assertElementPresent(cartSize);
		return cartSize.getText();
	}
}
