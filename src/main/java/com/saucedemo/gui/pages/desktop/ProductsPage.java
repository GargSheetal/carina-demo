package com.saucedemo.gui.pages.desktop;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import com.saucedemo.gui.pages.common.ItemInfoPageBase;
import com.saucedemo.gui.pages.common.ProductsPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = ProductsPageBase.class)
public class ProductsPage extends ProductsPageBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public ProductsPage(WebDriver driver) {
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
	public ItemInfoPageBase selectProduct(String product) {
		for(ExtendedWebElement link: productsLink) {
			LOGGER.info("Selecting product : " + product);
			String currentProduct = link.getText();
			if(product.equalsIgnoreCase(currentProduct)) {
				link.click();
				return initPage(getDriver(), ItemInfoPageBase.class);
			}
		}
		throw new RuntimeException("Unable to open product : " + product);
	}

//	@Override
//	public void verifyAllLinks() {
//		System.out.println("allLinks: " + allLinks.size());
//		for(ExtendedWebElement link: allLinks) {
//		//	LOGGER.info(link.getText());
//			String linkText = link.getAttribute("href");
//			System.out.println(linkText);
//			checkLink(linkText);
//		}
//	}
//	
//	public void checkLink(String linkHref) {
//		try {
//			URL url = new URL(linkHref);
//			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//			int responseCode = connection.getResponseCode();
//			if(responseCode == HttpURLConnection.HTTP_OK) {
//				LOGGER.info(linkHref + " is Working");
//			} else {
//				LOGGER.info(linkHref + " is not Working (HTTP status code : " + responseCode + ")");
//			}
//		} catch(IOException e) {
//			System.out.println(linkHref + " - Not Working (IOException)");
//		}
//	}
	
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
	public void selectSortOrder(String text) {
		System.out.println(sortOrder.getSelectedValue());
		sortOrder.select(text);
		System.out.println(sortOrder.getSelectedValue());
	}
	
	@Override
	public void verifyProductList() {
		for(int i = 0; i<productsLink.size(); i++) {
			String product = productsLink.get(i).getText();
			LOGGER.info(productsLink.get(i) + " Product : " + product);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(productsLink.get(0).getText(), "Sauce Labs Onesie", "Invalid product!");
			softAssert.assertEquals(productsLink.get(1).getText(), "Sauce Labs Bike Light", "Invalid product!");
			softAssert.assertEquals(productsLink.get(2).getText(), "Sauce Labs Bolt T-Shirt", "Invalid product!");
			softAssert.assertEquals(productsLink.get(3).getText(), "Test.allTheThings() T-Shirt (Red)", "Invalid product!");
			softAssert.assertEquals(productsLink.get(4).getText(), "Sauce Labs Backpack", "Invalid product!");
			softAssert.assertEquals(productsLink.get(5).getText(), "Sauce Labs Fleece Jacket", "Invalid product!");
		}
	}
}
