package com.automation.practice.web;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.miscellaneous.desktop.TextCompareBase;
import com.zebrunner.carina.core.IAbstractTest;

public class KeyboardActionsTest implements IAbstractTest {
	
	@Test
	public void testKeyboardAction() throws AWTException, InterruptedException {
		TextCompareBase textCompare = initPage(getDriver(), TextCompareBase.class);
		textCompare.open();
		textCompare.assertPageOpened();
		textCompare.sendText("Testing Keyboard Actions");
		textCompare.performCopyPasteAction();
		Thread.sleep(2000);
		String text1 = textCompare.getTextArea1().getText();
		String text2 = textCompare.getTextArea2().getText();
		System.out.println("Text1 : " + text1 + " | Text2 : " + text2);
		Assert.assertEquals(text1, text2, "Text Mismatch");
	}

}
