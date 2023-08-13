package com.demo.guru99.gui.pages.desktop;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.demo.guru99.gui.pages.common.LoginPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

	@FindBy(name = "uid")
	private ExtendedWebElement txtUserId;
	
	@FindBy(name = "password")
	private ExtendedWebElement txtPassword;
	
	@FindBy(name = "btnLogin")
	private ExtendedWebElement btnLogin;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	
	public void setUserId(String userId) {
		txtUserId.type(userId);
	}
	
	public void setPassword(String password) {
		txtPassword.type(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void performLogin(String username, String password) {
		txtUserId.assertElementPresent();
		txtUserId.type(username);
		txtPassword.assertElementPresent();
		txtPassword.type(password);
		System.out.println("performLogin 5");
		try {
			btnLogin.click();
            // Handle the alert if it's present
            Alert alert = driver.switchTo().alert();
            String actMessage = alert.getText();
            System.out.println("Alert Text: " + actMessage);
            alert.accept(); // or alert.dismiss() based on your needs
            driver.switchTo().defaultContent();
			String expMessage = "User or Password is not valid";
			Assert.assertEquals(actMessage, expMessage, "Invalid Error Message");
        } catch (NoAlertPresentException e) {
            // No alert found, proceed with other steps
        	System.out.println("NoAlertPresentException > " + e.getMessage());
        }
		
		
//		Alert alert = getDriver().switchTo().alert();
//		String actMessage = alert.getText();
//		System.out.println("performLogin 6 > " + actMessage);
	}
	
}
