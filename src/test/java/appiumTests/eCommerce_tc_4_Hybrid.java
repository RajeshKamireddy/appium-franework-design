package appiumTests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import rajesh.pageObjects.android.CartPage;
import rajesh.pageObjects.android.FormPage;
import rajesh.pageObjects.android.ProductCatalogue;

public class eCommerce_tc_4_Hybrid extends BaseTest {

	@Test
	public void FillForm() throws InterruptedException {
		FormPage formPage = new FormPage(driver);
		formPage.setNameField("Rajesh");
		formPage.setGender("female");
		formPage.setCountrySelection("Argentina");
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();

		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(
				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();

	}

}
