package com.saucelabs.mydemoapprn.mobile.gui.pages.android;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saucelabs.mydemoapprn.mobile.gui.pages.common.CartPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.HomePageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.MenuPageBase;
import com.saucelabs.mydemoapprn.mobile.gui.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.locator.ExtendedFindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase implements IMobileUtils{

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@FindBy(css = ".android.widget.ImageView")
	private ExtendedWebElement burgerMenu;
	
	@FindBy(className = "android.widget.TextView")
	private ExtendedWebElement title;
	
	@ExtendedFindBy(accessibilityId = "cart badge")
	private ExtendedWebElement cartIcon;
	
	@FindBy(xpath = "//android.widget.TextView[@index='1']")
	private ExtendedWebElement cartSize;
	
	@FindBy(xpath = "//android.widget.TextView[@content-desc='store item text']")
	private List<ExtendedWebElement> productsLink;
	
	@FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"product screen\"]/android.view.ViewGroup")
	private ExtendedWebElement contentScreen;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Sauce Labs Backpack']")
	private ExtendedWebElement sauceLabsbackpackName;
	
	@FindBy(xpath = "(//android.widget.TextView[@content-desc=\"store item price\"])[1]")
	private ExtendedWebElement sauceLabsbackpackPrice;
	
	public HomePage(WebDriver driver) {
		super(driver);
		setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
		setUiLoadedMarker(title);
	}
	
	public String getPageTitle() {
		return title.getText();
	}

	@Override
	public MenuPageBase clickBurgerMenu() {
		burgerMenu.click();
		return new MenuPage(getDriver());
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

	public CartPageBase clickCartIcon() {
		assertElementPresent(cartIcon);
		cartIcon.click();
		return initPage(getDriver(), CartPageBase.class);
	}

	public String getCartSize() {
		assertElementPresent(cartSize);
		return cartSize.getText();
	}

	@Override
	public HomePageBase goBack() {		
		// Get the device screen size
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		// Calculate the start and end points for the swipe right action
		int startX = (int) (width * 0.5);
		int endX = (int) (width * 1);
		int startY = height / 2;
		swipe(endX, startY, startX, startY, 1000);
		return initPage(getDriver(), HomePageBase.class);
	}
	
	public String readSauceLabsBackpackName() {
		return sauceLabsbackpackName.getText();
	}
	
	public String readSauceLabsBackpackPrice() {
		return sauceLabsbackpackPrice.getText();
	}
}
