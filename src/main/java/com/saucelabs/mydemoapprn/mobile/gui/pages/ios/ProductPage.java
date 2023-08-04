package com.saucelabs.mydemoapprn.mobile.gui.pages.ios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.decorator.annotations.ClassChain;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.IOS_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase implements IMobileUtils {
	
	@ExtendedFindBy(accessibilityId = "container header")
    private ExtendedWebElement productName;
	
	@ExtendedFindBy(accessibilityId = "product description")
    private ExtendedWebElement productDescription;
	
	@ExtendedFindBy(accessibilityId = "product price")
    private ExtendedWebElement productPrice;
	
	@ExtendedFindBy(accessibilityId = "Add To Cart button")
    private ExtendedWebElement btnAddToCart;
	
	@ExtendedFindBy(iosClassChain = "**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
    private ExtendedWebElement container;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(productName);
	}
	
	@Override
	public String readProductName() {
		productName.assertElementPresent();
		return productName.getText();
	}
	
	@Override
	public String readProductDescription() {
		assertElementPresent(productDescription);
		return productDescription.getText();
	}

	@Override
	public String readProductPrice() {
		assertElementPresent(productPrice);
		return productPrice.getText();
	}

	public void swipeToProductDescription() {
        swipe(productDescription, container, 10);
    }
	
	@Override
	public void clickAddToCart() {
		assertElementPresent(btnAddToCart);
		btnAddToCart.click();
	}
}
