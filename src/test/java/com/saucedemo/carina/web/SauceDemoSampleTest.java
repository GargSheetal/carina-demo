package com.saucedemo.carina.web;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.saucedemo.gui.pages.common.HomePageBase;
import com.saucedemo.gui.pages.common.ItemInfoPageBase;
import com.saucedemo.gui.pages.common.ProductsPageBase;
import com.saucedemo.gui.pages.desktop.CartPage;
import com.saucedemo.gui.pages.desktop.CheckoutInfoPage;
import com.saucedemo.gui.pages.desktop.CheckoutOverviewPage;
import com.saucedemo.gui.pages.desktop.OrderConfirmationPage;
import com.saucedemo.gui.pages.desktop.ProductsPage;
import com.saucedemo.gui.pages.desktop.CartItem;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;

public class SauceDemoSampleTest implements IAbstractTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testLoginAndLogout() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		// Asserting home page is opened
		homePage.assertPageOpened();
		ProductsPageBase productsPage = homePage.performLogin("standard_user", "secret_sauce");
		// Asserting products page is opened
		productsPage.assertPageOpened();
		
		homePage = productsPage.getHeaderMenu().clickLogout();
		//  Asserting home page after logout
		homePage.assertPageOpened();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testProductCardDetails() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		// Asserting home page is opened
		homePage.assertPageOpened();
		ProductsPageBase productsPage = homePage.performLogin("standard_user", "secret_sauce");
		// Asserting products page is opened
		productsPage.assertPageOpened();
		
		ItemInfoPageBase itemInfoPage = productsPage.selectProduct("Sauce Labs Backpack");
		// Verify item specifications
		final String expectedDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(itemInfoPage.readItemName(), "Sauce Labs Backpack", "Invalid Item name!");
		softAssert.assertEquals(itemInfoPage.readItemDescription(), expectedDesc, "Invalid Item description!");
		softAssert.assertEquals(itemInfoPage.readItemPrice(), "$29.99", "Invalid Item price!");
		softAssert.assertAll();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testValidateCart() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		// Asserting home page is opened
		homePage.assertPageOpened();
		ProductsPageBase productsPage = homePage.performLogin("standard_user", "secret_sauce");
		// Asserting products page is opened
		productsPage.assertPageOpened();
		productsPage.addProductToCart("Sauce Labs Backpack");
		String cartSize = productsPage.getHeaderMenu().readCartSize();
		Assert.assertEquals(cartSize, "1");
		CartPage cartPage = productsPage.getHeaderMenu().selectCart();
		
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
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		// Asserting home page is opened
		homePage.assertPageOpened();
		ProductsPageBase productsPage = homePage.performLogin("standard_user", "secret_sauce");
		// Asserting products page is opened
		productsPage.assertPageOpened();
		productsPage.selectSortOrder("Price (low to high)");
		productsPage.verifyProductList();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void validateOpeningOfAllPages() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		// Asserting home page is opened
		homePage.assertPageOpened();
		ProductsPageBase productsPage = homePage.performLogin("standard_user", "secret_sauce");
		// Asserting products page is opened
		productsPage.assertPageOpened();
		ItemInfoPageBase itemInfoPage = productsPage.selectProduct("Sauce Labs Bolt T-Shirt");
		// Asserting ItemInfo page is opened
		itemInfoPage.assertPageOpened();
		itemInfoPage.clickAddToCart();
		CartPage cartPage =  productsPage.getHeaderMenu().selectCart();
		cartPage.assertPageOpened();
		CheckoutInfoPage checkoutInfo = cartPage.clickCheckout();
		checkoutInfo.setFirstName("John");
		checkoutInfo.setLastName("Doe");
		checkoutInfo.setZipCode("123456");
		CheckoutOverviewPage checkoutOverview = checkoutInfo.clickContinue();
		OrderConfirmationPage orderConfirmation = checkoutOverview.clickFinish();
		productsPage = orderConfirmation.clickBackHome();
	}
}
