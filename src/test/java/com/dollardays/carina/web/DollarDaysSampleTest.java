package com.dollardays.carina.web;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dollardays.gui.pages.common.HomePageBase;
import com.dollardays.gui.pages.desktop.CheckoutPage;
import com.dollardays.gui.pages.desktop.CreateAccountPage;
import com.dollardays.gui.pages.desktop.HeaderMenu;
import com.dollardays.gui.pages.desktop.LoginPage;
import com.dollardays.gui.pages.desktop.SmallBackpacksPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

public class DollarDaysSampleTest implements IAbstractTest {
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testLoginAndLogout() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		homePage.assertPageOpened();
		HeaderMenu headerMenu = homePage.getHeaderMenu();
		headerMenu.clickDropDownSignIn();
		LoginPage loginPage = headerMenu.clickSignInLink();
		loginPage.setUsername("kanhakuapoojan@gmail.com");
		loginPage.setPassword("laddoosingh");
		homePage = loginPage.clickSignIn();
		homePage.assertPageOpened();
		headerMenu.clickDropDownSignIn();
		headerMenu.clickSignOut();
	}

	@Test()
	@MethodOwner(owner = "sheetal")
	public void testCreateAccount() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		homePage.assertPageOpened();
		HeaderMenu headerMenu = homePage.getHeaderMenu();
		headerMenu.clickDropDownSignIn();
		CreateAccountPage createAccount = headerMenu.clickCreateAccount();
		createAccount.setFirstName();
		createAccount.setLastName();
		createAccount.setEmailAdd();
		createAccount.setPhone();
		String password = createAccount.setPassword();
		createAccount.setConfirmPassword(password);
		// Switching to iframe and clicking captcha
		createAccount.switchToIframe();
		homePage = createAccount.clickCreateAccount();
		String expMsg = "Successfully Registered!\n"
						+ "Redirecting now...";
		String actMsg = getDriver().switchTo().activeElement().getText();
		Assert.assertTrue(actMsg.contains(expMsg), "Invalid Success Message. Create Account Failed..");
		homePage.assertPageOpened();
	}
	
	@Test()
	@MethodOwner(owner = "sheetal")
	public void testValidateCart() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		homePage.assertPageOpened();
		HeaderMenu headerMenu = homePage.getHeaderMenu();
		headerMenu.clickDropDownSignIn();
		LoginPage loginPage = headerMenu.clickSignInLink();
		loginPage.setUsername("kanhakuapoojan@gmail.com");
		loginPage.setPassword("laddoosingh");
		homePage = loginPage.clickSignIn();
		homePage.assertPageOpened();
		try {
			headerMenu.clickBurgerMenu();
			headerMenu.clickBackpacks();
			SmallBackpacksPage smallBackpacks = headerMenu.clickSmallBackpacks();
			smallBackpacks.selectBackpackFromList("15\" Two-Tone Backpacks - 5 Colors");
			String cartCount = headerMenu.getCartCount();
			// Asserting Cart count
			assertEquals(cartCount, "1", "Invalid Cart Count!");
			CheckoutPage checkoutPage = headerMenu.clickCartLink();
			String itemName = checkoutPage.getItemName();
			String price = checkoutPage.getPrice();
			String subtotal = checkoutPage.getSubtotal();
			String pricePerUnit = checkoutPage.getPricePerUnit();
			String unitsPerCase = checkoutPage.getUnitsPerCase();
			String quantity = checkoutPage.getQuantity();
			// Asserting product details in cart
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(itemName, "15\" Two-Tone Backpacks - 5 Colors", "Invalid Item Name!");
			softAssert.assertEquals(price, "$110.64", "Invalid Item Price!");
			softAssert.assertEquals(subtotal, "$110.64", "Invalid Item Subtotal!");
			softAssert.assertEquals(pricePerUnit, "$4.61", "Invalid Price per unit!");
			softAssert.assertEquals(unitsPerCase, "24", "Invalid Units per case!");
			softAssert.assertEquals(quantity, "1", "Invalid Quantity!");
		} finally {
			headerMenu.clickDropDownSignIn();
			headerMenu.clickSignOut();
		}
		
	}
	
}
