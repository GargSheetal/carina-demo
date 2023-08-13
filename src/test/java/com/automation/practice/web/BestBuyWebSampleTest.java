package com.automation.practice.web;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bestbuy.gui.pages.common.HomePageBase;
import com.bestbuy.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

public class BestBuyWebSampleTest implements IAbstractTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Test
	public void testLogin() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		homePage.assertPageOpened();
		LoginPageBase loginPage = homePage.clickSignIn();
		loginPage.assertPageOpened();
		homePage = loginPage.performLogin();
		String actDisplayName = homePage.getUserDisplayName();
		String expDisplayName = "Hi, Test";
		Assert.assertEquals(actDisplayName, expDisplayName, "Display name mismatch !");
		homePage.isSignoutPresent();
	}

	@Test()
	public void testVerifyDepartmentList() {
		HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
		homePage.open();
		homePage.assertPageOpened();
		homePage.getTopMenu().clickMenu();
		Set<String> expDepartmentList = new HashSet<>(Arrays.asList(
				"Deals",
				"Support & Services",
				"Brands",
				"Appliances",
				"TV & Home Theater",
				"Computers & Tablets",
				"Cell Phones",
				"Audio",
				"Video Games",
				"Cameras, Camcorders & Drones",
				"Home, Furniture & Office",
				"Smart Home, Security & Wi-Fi",
				"Car Electronics & GPS",
				"Movies & Music",
				"Wearable Technology",
				"Health, Wellness & Fitness",
				"Outdoor Living",
				"Toys, Games & Collectibles",
				"Electric Transportation",
				"New & Featured"
				));
		List<ExtendedWebElement> departmentList = homePage.getTopMenu().getDepartmentList();
		int index = 1;
		for(ExtendedWebElement department: departmentList) {
			String departmentName = department.getText();
			LOGGER.info(index + ") Department Name : " + departmentName);
			index++;
			Assert.assertTrue(expDepartmentList.contains(departmentName), "Invalid Department name!" + departmentName);
			}
		}
	
}
