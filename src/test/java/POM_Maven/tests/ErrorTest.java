package POM_Maven.tests;

import org.testng.annotations.Test;

import MavenTime.Cart;
import MavenTime.ProductCatalogue;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import POM_Maven.TestComponents.BaseTest;
import POM_Maven.TestComponents.Retry;

public class ErrorTest extends BaseTest {
	@Test(retryAnalyzer=Retry.class)
	public void ErrorVerify() throws InterruptedException {

		LP.logintoApp("rrana@learn.com", "Sam@122345");
		AssertJUnit.assertEquals(LP.errorLogin(), "Incorrect email o password.");
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue PC = LP.logintoApp("rrana@learn.com","Sam@12345");
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(productName);
		Cart CP = PC.ClickOnCartButton();
		Boolean match = CP.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}
}
