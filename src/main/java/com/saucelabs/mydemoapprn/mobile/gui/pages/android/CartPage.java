package com.saucelabs.mydemoapprn.mobile.gui.pages.android;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.CartPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase implements IMobileUtils {

	@ExtendedFindBy(accessibilityId = "product price")
	private List<ExtendedWebElement> productPrices;
	
	@ExtendedFindBy(accessibilityId = "total number")
	private ExtendedWebElement totalItems;
	
	@ExtendedFindBy(accessibilityId = "total price")
	private ExtendedWebElement totalPrice;
	
	@ExtendedFindBy(accessibilityId = "Proceed To Checkout button")
	private ExtendedWebElement btnProceedToCheckout;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='product row']")
	private List<CartItem> cartItems;
	
	public CartPage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(totalItems);
	}
	
	@Override
	public String getTotalItems() {
		return totalItems.getText();
	}
	
	@Override
	public String getTotalPrice() {
		return totalPrice.getText();
	}

	@Override
	public double calculateTotalPrice() {
		double totalPrice = 0.0;
		for(ExtendedWebElement productPrice: productPrices) {
			String priceString = productPrice.getText().replace("$", "");
			double priceDouble = Double.parseDouble(priceString);
			totalPrice += priceDouble;
		}
		return totalPrice;
	}

	@Override
	public LoginPageBase clickProceedToCheckout() {
		assertElementPresent(btnProceedToCheckout);
		btnProceedToCheckout.click();
		return initPage(getDriver(), LoginPageBase.class);
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	
}
