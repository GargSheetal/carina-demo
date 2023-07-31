package com.saucedemo.gui.pages.desktop;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.saucedemo.gui.pages.common.ProductPageBase;
import com.saucedemo.gui.pages.common.HomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public HomePage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(productsTitle);
	}
	
	@FindBy(css = ".primary_header")
	private HeaderMenu headerMenu;
	
	@FindBy(css = ".title")
	private ExtendedWebElement productsTitle;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	private List<ExtendedWebElement> productsLink;
	
	@FindBy(xpath = "//a")
	private List<ExtendedWebElement> allLinks;
	
	@FindBy(xpath = "//button[text()='Add to cart']")
	private ExtendedWebElement btnAddToCart;
	
	@FindBy(css = ".product_sort_container")
	private ExtendedWebElement sortOrder;
	
	@Override
	public HeaderMenu getHeaderMenu() {
		return headerMenu;
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
	
	@Override
	public void addProductToCart(String product) {
		for(ExtendedWebElement link: productsLink) {
			LOGGER.info("Selecting product : " + product);
			String currentProduct = link.getText();
			if(product.equalsIgnoreCase(currentProduct)) {
				btnAddToCart.click();
			}
		}
	}
	
	@Override
	public String selectSortOrder(String text) {
		LOGGER.info("Sorting Order before : " + sortOrder.getSelectedValue());
		sortOrder.select(text);
		String order = sortOrder.getSelectedValue();
		LOGGER.info("Sorting Order after : " + order);
		return order;
	}
	
	@Override
	public List<ExtendedWebElement> getProductsLink() {
		return productsLink;
	}
	
//	@Override
//	public void verifyProductList() {
//		for(int i = 0; i<productsLink.size(); i++) {
//			String product = productsLink.get(i).getText();
//			LOGGER.info("(" + (i + 1) + ") " + product);
//			SoftAssert softAssert = new SoftAssert();
//			softAssert.assertEquals(productsLink.get(0).getText(), "Sauce Labs Onesie", "Invalid product!");
//			softAssert.assertEquals(productsLink.get(1).getText(), "Sauce Labs Bike Light", "Invalid product!");
//			softAssert.assertEquals(productsLink.get(2).getText(), "Sauce Labs Bolt T-Shirt", "Invalid product!");
//			softAssert.assertEquals(productsLink.get(3).getText(), "Test.allTheThings() T-Shirt (Red)", "Invalid product!");
//			softAssert.assertEquals(productsLink.get(4).getText(), "Sauce Labs Backpack", "Invalid product!");
//			softAssert.assertEquals(productsLink.get(5).getText(), "Sauce Labs Fleece Jacket", "Invalid product!");
//		}
//	}
}
