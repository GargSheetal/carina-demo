package com.dollardays.gui.pages.desktop;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.dollardays.gui.pages.common.HomePageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;

public class CreateAccountPage extends AbstractUIObject {

	@FindBy(id = "ctl00_cphContent_txtFName")
	private ExtendedWebElement txtFirstname;
	
	@FindBy(id = "ctl00_cphContent_txtLName")
	private ExtendedWebElement txtLastname;
	
	@FindBy(id = "ctl00_cphContent_txtClientEmail")
	private ExtendedWebElement txtEmail;
	
	@FindBy(name = "ctl00$cphContent$txtPhoneMain")
	private ExtendedWebElement txtPhone;
	
	@FindBy(name = "ctl00$cphContent$txtPassword")
	private ExtendedWebElement txtPassword;
	
	@FindBy(name = "ctl00$cphContent$txtPasswordConfirm")
	private ExtendedWebElement txtConfirmPassword;
	
	@FindBy(xpath = "//iframe[@title='reCAPTCHA']")
	private ExtendedWebElement iframeCaptcha;
	
	@FindBy(name = "ctl00$cphContent$btnRegister")
	private ExtendedWebElement btnCreateAccount;
	
	public CreateAccountPage(WebDriver driver) {
		super(driver);
	}
	
	public void setFirstName() {
		assertElementPresent(txtFirstname);
		String firstname = RandomStringUtils.randomAlphabetic(6);
		txtFirstname.type(firstname);
	}
	
	public void setLastName() {
		assertElementPresent(txtLastname);
		String lastname = RandomStringUtils.randomAlphabetic(6);
		txtLastname.type(lastname);
	}
	
	public void setEmailAdd() {
		assertElementPresent(txtEmail);
		String email = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
		txtEmail.type(email);
	}
	
	public void setPhone() {
		assertElementPresent(txtPhone);
		String phone = RandomStringUtils.randomNumeric(10);
		txtPhone.type(phone);
	}
	
	public String setPassword() {
		assertElementPresent(txtPassword);
		String password = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomAlphanumeric(3);;
		txtPassword.type(password);
		return password;
	}
	
	public void switchToIframe() {
		Frame frame = new Frame(getDriver());
		getDriver().switchTo().frame(iframeCaptcha.getElement());
		frame.click();
		getDriver().switchTo().defaultContent();
	}
	
	public void setConfirmPassword(String confirmPass) {
		assertElementPresent(txtConfirmPassword);
		txtConfirmPassword.type(confirmPass);
	}
	
	public HomePageBase clickCreateAccount() {
		assertElementPresent(btnCreateAccount);
		btnCreateAccount.click();
		return new HomePage(getDriver());
	}

}
