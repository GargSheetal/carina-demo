package com.saucedemo.carina.web;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.saucedemo.gui.pages.common.LoginPageBase;
import com.saucedemo.gui.pages.common.ProductPageBase;
import com.saucedemo.gui.pages.common.HomePageBase;
import com.saucedemo.gui.pages.desktop.CartPage;
import com.saucedemo.gui.pages.desktop.CheckoutInfoPage;
import com.saucedemo.gui.pages.desktop.CheckoutOverviewPage;
import com.saucedemo.gui.pages.desktop.OrderConfirmationPage;
import com.saucedemo.gui.pages.desktop.HomePage;
import com.saucedemo.gui.pages.desktop.CartItem;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

public class SauceDemoSampleTest implements IAbstractTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testLoginAndLogout() {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
		// Asserting home page is opened
		loginPage.assertPageOpened();
		HomePageBase homePage = loginPage.performLogin();
		// Asserting products page is opened
		homePage.assertPageOpened();
		
		loginPage = homePage.getHeaderMenu().clickLogout();
		//  Asserting home page after logout
		loginPage.assertPageOpened();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testProductCardDetails() {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
		// Asserting home page is opened
		loginPage.assertPageOpened();
		HomePageBase homePage = loginPage.performLogin();
		// Asserting products page is opened
		homePage.assertPageOpened();
		
		ProductPageBase productPage = homePage.selectProduct("Sauce Labs Backpack");
		// Verify item specifications
		final String expectedDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productPage.readItemName(), "Sauce Labs Backpack", "Invalid Item name!");
		softAssert.assertEquals(productPage.readItemDescription(), expectedDesc, "Invalid Item description!");
		softAssert.assertEquals(productPage.readItemPrice(), "$29.99", "Invalid Item price!");
		softAssert.assertAll();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testValidateCart() {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
		// Asserting home page is opened
		loginPage.assertPageOpened();
		HomePageBase homePage = loginPage.performLogin();
		// Asserting products page is opened
		homePage.assertPageOpened();
		homePage.addProductToCart("Sauce Labs Backpack");
		String cartSize = homePage.getHeaderMenu().readCartSize();
		Assert.assertEquals(cartSize, "1");
		CartPage cartPage = homePage.getHeaderMenu().selectCart();
		
		// Asserting cart page is opened
		cartPage.assertPageOpened();
		for(CartItem item:cartPage.getItems()) {
			String itemName = item.readItemName();
			String quantity = item.readItemQuantity();
			String price = item.readItemPrice();
			LOGGER.info("Item Details : " + quantity + " | " + itemName + " | " + price);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(!itemName.isEmpty(), "Invalid Item name!");
			softAssert.assertTrue(!quantity.isEmpty(), "Invalid Item quantity!");
			softAssert.assertTrue(!price.isEmpty(), "Invalid Item price!");
			softAssert.assertAll();
		}
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void verifyProductSorting() {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
		// Asserting home page is opened
		loginPage.assertPageOpened();
		HomePageBase homePage = loginPage.performLogin();
		// Asserting products page is opened
		homePage.assertPageOpened();
		String order = homePage.selectSortOrder("Price (low to high)");
		Assert.assertTrue(order.equalsIgnoreCase("Price (low to high)"), "Invalid Sort Order!");
		
		List<ExtendedWebElement> productsLink = homePage.getProductsLink();
		for(int i = 0; i<productsLink.size(); i++) {
			String product = productsLink.get(i).getText();
			LOGGER.info("(" + (i + 1) + ") " + product);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(productsLink.get(0).getText(), "Sauce Labs Onesie", "Invalid product!");
			softAssert.assertEquals(productsLink.get(1).getText(), "Sauce Labs Bike Light", "Invalid product!");
			softAssert.assertEquals(productsLink.get(2).getText(), "Sauce Labs Bolt T-Shirt", "Invalid product!");
			softAssert.assertEquals(productsLink.get(3).getText(), "Test.allTheThings() T-Shirt (Red)", "Invalid product!");
			softAssert.assertEquals(productsLink.get(4).getText(), "Sauce Labs Backpack", "Invalid product!");
			softAssert.assertEquals(productsLink.get(5).getText(), "Sauce Labs Fleece Jacket", "Invalid product!");
			softAssert.assertAll();
		}
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void validateOpeningOfAllPages() {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
		// Asserting home page is opened
		loginPage.assertPageOpened();
		HomePageBase homePage = loginPage.performLogin();
		// Asserting products page is opened
		homePage.assertPageOpened();
		ProductPageBase productPage = homePage.selectProduct("Sauce Labs Bolt T-Shirt");
		// Asserting ItemInfo page is opened
		productPage.assertPageOpened();
		productPage.clickAddToCart();
		CartPage cartPage =  homePage.getHeaderMenu().selectCart();
		cartPage.assertPageOpened();
		CheckoutInfoPage checkoutInfo = cartPage.clickCheckout();
		checkoutInfo.setFirstName("John");
		checkoutInfo.setLastName("Doe");
		checkoutInfo.setZipCode("123456");
		CheckoutOverviewPage checkoutOverview = checkoutInfo.clickContinue();
		OrderConfirmationPage orderConfirmation = checkoutOverview.clickFinish();
		String confirmationText = orderConfirmation.verifyOrderConfirmationText();
		// Asserting order confirmation text
		Assert.assertEquals(confirmationText, "Thank you for your order!", "Invalid Confirmation text!");
		homePage = orderConfirmation.clickBackHome();
		homePage.assertPageOpened();
	}
}
