package com.saucelabs.mydemoapprn.mobile.gui.pages.common;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name = "loginData")
	public Object[][] provideLoginData() {
		return new Object[][] {
			{"bob@example.com", "10203040"},
			{"alice@example.com", "10203040"}
		};
	}

}
