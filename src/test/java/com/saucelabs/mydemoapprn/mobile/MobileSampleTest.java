package com.saucelabs.mydemoapprn.mobile;

import org.testng.annotations.Test;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.CartItemBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.CartPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.DataProviderClass;
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
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		//Asserting home page is opened
		homePage.assertPageOpened();
		MenuPageBase menu = homePage.clickBurgerMenu();
		LoginPageBase loginPage = menu.clickLoginButton();
		Assert.assertEquals(loginPage.getPageTitle(), "Login", "Invalid Page Title!");
		homePage = loginPage.performLogin();
		homePage.assertPageOpened();
		
		// perform logout
		menu = homePage.clickBurgerMenu();
		menu.clickLogout();
		menu.clickLogoutOnFrame();
		String expLogoutMsg = "You are successfully logged out.";
		String actLogoutMsg = menu.getLogoutMsg();
		Assert.assertEquals(actLogoutMsg, expLogoutMsg, "Invalid Logout message!");
		loginPage = menu.clickOKBtn();
		loginPage.assertPageOpened();
	}
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviderClass.class)
	@MethodOwner(owner = "sheetal")
	public void testLoginDataDriven(String username, String password, Boolean expectedResult) {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		//Asserting home page is opened
		homePage.assertPageOpened();
		MenuPageBase menu = homePage.clickBurgerMenu();
		LoginPageBase loginPage = menu.clickLoginButton();
		loginPage.assertPageOpened();
		Assert.assertEquals(loginPage.getPageTitle(), "Login", "Invalid Page Title!");
		loginPage.loginDataDriven(username, password);
		SoftAssert softAssert = new SoftAssert();
		
		if(expectedResult.equals(true)) {
			Boolean actualResult = homePage.getPageTitle().equals("Products");
			if(actualResult.equals(true)) {
				softAssert.assertTrue(true, "Login Successful with Valid Credentials!");
				LOGGER.info("[VALID-PASS] Navigated to Home Page. Login Successfull with Valid Credentials : " + username);
			} 
			else {
				softAssert.assertTrue(false, "Login Unsuccessful with Valid Credentials!");
				LOGGER.info("[VALID-FAIL] Login Unsuccessful with Valid Credentials : " + username );
			}
		}
		else{
			Boolean actualResult = loginPage.getUserLockedOutMsg().equalsIgnoreCase("Sorry, this user has been locked out.");
			if(actualResult.equals(true)) {
				softAssert.assertTrue(true, "Login failed with Invalid Credentials!");
				LOGGER.info("[INVALID-PASS] Login Failed for Invalid Credentials : " + username);
			} 
			else {
				softAssert.assertTrue(false, "Login did not fail with Invalid Credentials!");
				LOGGER.info("[INVALID-FAIL] Login did not fail with Invalid Credentials : " + username);
			} 
		}
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testProductDetails() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		// Asserting home page is opened
		homePage.assertPageOpened();
		String productNameHomePage = homePage.readSauceLabsBackpackName();
		String productPriceHomePage = homePage.readSauceLabsBackpackPrice();
		ProductPageBase productPage = homePage.selectProduct("Sauce Labs Backpack");
		productPage.assertPageOpened();
		// Verify item specifications
		String productNameProductPage = productPage.readProductName();
		String productPriceProductPage = productPage.readProductPrice();
		final String expectedDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productNameHomePage, productNameProductPage, "Product Name mismatch!");
		softAssert.assertEquals(productPriceHomePage, productPriceProductPage, "Product Price mismatch!");
		productPage.swipeToProductDescription();
		softAssert.assertEquals(productPage.readProductDescription(), expectedDesc, "Invalid Item description!");
		softAssert.assertAll();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testValidateCart() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.assertPageOpened();
		ProductPageBase productPage = homePage.selectProduct("Sauce Labs Backpack");
		productPage.assertPageOpened();
		productPage.clickAddToCart();
		String cartSize1 = homePage.getCartSize();
		LOGGER.info("Cart size : " + cartSize1);
		Assert.assertEquals(cartSize1, "1", "Invalid Cart Size!");
		homePage.goBack();
		productPage = homePage.selectProduct("Sauce Labs Bike Light");
		productPage.clickAddToCart();
		// asserting number of products in the cart
		String cartSize2 = homePage.getCartSize();
		LOGGER.info("Cart size : " + cartSize2);
		Assert.assertEquals(cartSize2, "2", "Invalid Cart Size!");
		CartPageBase cartPage = homePage.clickCartIcon();
		String totalItems = cartPage.getTotalItems();
		String totalPrice = cartPage.getTotalPrice();
		double calculatedTotalPrice = cartPage.calculateTotalPrice();
		LOGGER.info("Total number of items : " + totalItems + " | Total price : " + totalPrice + " | Total calculated price : $" + calculatedTotalPrice);
		// Validating cart
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(totalItems, "2 items", "Invalid item count");
		softAssert.assertEquals(totalPrice, ("$" + calculatedTotalPrice), "Total price Mismatch!");
		softAssert.assertAll();
		// Define the expected item names in a Set
        Set<String> expectedItemNames = new HashSet<>(Arrays.asList(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light"
        ));
		for(CartItemBase cartItem: cartPage.getCartItems()) {
			String itemName = cartItem.readItemName();
			LOGGER.info("Item Name: " + itemName);
            Assert.assertTrue(expectedItemNames.contains(itemName), "Invalid Item Name: " + itemName);
		}
	}
}
