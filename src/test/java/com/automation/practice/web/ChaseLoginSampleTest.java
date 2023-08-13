package com.automation.practice.web;

import org.testng.annotations.Test;

import com.chase.gui.pages.desktop.Frame;
import com.chase.gui.pages.desktop.LoginPageBase;
import com.zebrunner.carina.core.IAbstractTest;

public class ChaseLoginSampleTest implements IAbstractTest {

	@Test()
	public void testLogin() throws InterruptedException {
		LoginPageBase loginPage = initPage(getDriver(), LoginPageBase.class);
		loginPage.open();
		Frame frame = loginPage.getIframe();
		frame.performLogin();
	}
	
}
