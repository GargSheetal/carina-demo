package com.saucelabs.mydemoapprn.mobile;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.saucelabs.mydemoapprn.mobile.gui.pages.android.CartItem;
import com.saucelabs.mydemoapprn.mobile.gui.pages.android.HomePage;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.CartPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.HomePageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.LoginPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.MenuPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.ProductPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.utils.mobile.IMobileUtils;

public class MobileSampleTest implements IAbstractTest, IMobileUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testLoginAndLogout() {
		HomePageBase homePage = new HomePage(getDriver());
		homePage.assertPageOpened();
		MenuPageBase menu = homePage.clickBurgerMenu();
		LoginPageBase loginPage = menu.clickLoginButton();
		String expLoginMsg = "Select a username and password from the list below, or click on the usernames to automatically populate the username and password.";
		String actLoginMsg = loginPage.getLoginMessage();
		Assert.assertEquals(actLoginMsg, expLoginMsg, "Invalid Login Page message!");
		homePage = loginPage.performLogin();
		homePage.assertPageOpened();
		
		// perform logout
		menu = homePage.clickBurgerMenu();
		menu.clickLogout();
		String expLogoutMsg = "Are you sure you sure you want to logout?";
		String actLogoutMsg = menu.getLogoutMsg();
		Assert.assertEquals(actLogoutMsg, expLogoutMsg, "Invalid Logout message!");
		menu.clickLogoutOnFrame();
		loginPage = menu.clickOKBtn();
		loginPage.assertPageOpened();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testProductDetails() {
		HomePageBase homePage = new HomePage(getDriver());
		homePage.assertPageOpened();
		ProductPageBase productPage = homePage.selectProduct("Sauce Labs Backpack");
		productPage.assertPageOpened();
		// Verify item specifications
		final String expectedDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productPage.readProductName(), "Sauce Labs Backpack", "Invalid Item name!");
		softAssert.assertEquals(productPage.readProductPrice(), "$29.99", "Invalid Item price!");
		productPage.swipeToProductDescription();
		softAssert.assertEquals(productPage.readProductDescription(), expectedDesc, "Invalid Item description!");
		softAssert.assertAll();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testValidateCart() {
		HomePageBase homePage = new HomePage(getDriver());
		homePage.assertPageOpened();
		ProductPageBase productPage = homePage.selectProduct("Sauce Labs Backpack");
		productPage.assertPageOpened();
		productPage.clickAddToCart();
		getDriver().navigate().back();
		productPage = homePage.selectProduct("Sauce Labs Bike Light");
		productPage.clickAddToCart();
		// asserting number of products in the cart
		String cartSize = homePage.getCartSize();
		LOGGER.info("Cart size : " + cartSize);
		Assert.assertEquals(cartSize, "2", "Invalid Cart Size!");
		CartPageBase cartPage = homePage.clickCartIcon();
		String totalItems = cartPage.getTotalItems();
		String totalPrice = cartPage.getTotalPrice();
		double calculatedTotalPrice = cartPage.calculateTotalPrice();
		LOGGER.info("Total number of items : " + totalItems + " | Total price : " + totalPrice + " | Total calculated price : $" + calculatedTotalPrice);
		// Validating cart
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(totalItems, "2");
		softAssert.assertEquals(totalPrice, ("$" + calculatedTotalPrice));
		
		// Define the expected item names in a Set
        Set<String> expectedItemNames = new HashSet<>(Arrays.asList(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light"
        ));
		for(CartItem cartItem: cartPage.getCartItems()) {
			String itemName = cartItem.readItemName();
			LOGGER.info("Item Name: " + itemName);
            Assert.assertTrue(expectedItemNames.contains(itemName), "Invalid Item Name: " + itemName);
		}
	}

}
