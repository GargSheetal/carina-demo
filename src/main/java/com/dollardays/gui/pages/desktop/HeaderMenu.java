package com.dollardays.gui.pages.desktop;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dollardays.gui.pages.common.HeaderMenuBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HeaderMenuBase.class)
public class HeaderMenu extends HeaderMenuBase{

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@FindBy(css = "a[role='button'] i[class='fa']")
	private ExtendedWebElement dropdownSignIn;
	
	@FindBy(xpath = "//a[text()=' Sign In']")
	private ExtendedWebElement linkSignIn;
	
	@FindBy(xpath = "//a[text()='Create Account']")
	private ExtendedWebElement linkCreateAccount;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private ExtendedWebElement linkSignOut;
	
	@FindBy(id = "sm_menu_ham")
	private ExtendedWebElement burgerMenu;
	
	@FindBy(xpath = "//li[@class='hasChild']//a[text()='Backpacks']")
	private ExtendedWebElement linkBackpacks;
	
	@FindBy(xpath = "//a[text()='15\" or Smaller Backpacks']")
	private ExtendedWebElement smallBackpacks;
	
	@FindBy(xpath = "//div[@class='box-product']")
	private List<ExtendedWebElement> listBackpacks;
	
	@FindBy(xpath = "//input[contains(@value,'Add to Cart')]")
//	@FindBy(css = "input[class='btn btn-quick-view jqatc fsig']")
	private ExtendedWebElement btnAddToCart;

	@FindBy(css = ".cart.dropdown.divcart")
	private ExtendedWebElement linkCart;
	
	@FindBy(id = "ctl00_ddiPageHeader_liViewCartDesktop")
	private ExtendedWebElement cartCount;
	
	@FindBy(xpath = "//span[@class='remove-item']")
	private ExtendedWebElement btnRemoveItem;
	
	
	public HeaderMenu(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	public void clickDropDownSignIn() {
		dropdownSignIn.assertElementPresent();
		dropdownSignIn.click();
	}
	
	public LoginPage clickSignInLink() {
		linkSignIn.assertElementPresent();
		linkSignIn.click();
		return new LoginPage(getDriver());
	}
	
	public CreateAccountPage clickCreateAccount() {
		linkCreateAccount.assertElementPresent();
		linkCreateAccount.click();
		return new CreateAccountPage(getDriver());
	}

	public void clickSignOut() {
		linkSignOut.assertElementPresent();
		linkSignOut.click();
	}
	
	public void clickBurgerMenu() {
		burgerMenu.assertElementPresent();
		burgerMenu.click();
	}
	
	public void clickBackpacks() {
		linkBackpacks.assertElementPresent();
		linkBackpacks.click();
	}
	
	public SmallBackpacksPage clickSmallBackpacks() {
		smallBackpacks.assertElementPresent();
		smallBackpacks.click();
		return new SmallBackpacksPage(getDriver());
	}
	
	public void selectBackpackFromList(String product) {
		for(ExtendedWebElement backpack: listBackpacks) {
			LOGGER.info("Selecting product : " + product);
			String currentProduct = backpack.getText();
			if(currentProduct.contains(product)) {
				btnAddToCart.clickByJs();
			}
		}
	}
	
	public CheckoutPage clickCartLink() {
		linkCart.assertElementPresent();
		linkCart.click();
		return new CheckoutPage(getDriver());
	}
	
	public String getCartCount() {
		cartCount.assertElementPresent();
		return cartCount.getText();
	}
	
	public void hoverCartLink() {
		linkCart.assertElementPresent();
		linkCart.hover();
	}
	
	public void removeItemFromCart() {
		assertElementPresent(btnRemoveItem);
		btnRemoveItem.clickByJs();
	}
}
