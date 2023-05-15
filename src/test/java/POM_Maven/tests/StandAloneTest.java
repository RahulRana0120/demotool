package POM_Maven.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.annotations.Test;

import MavenTime.Cart;
import MavenTime.CheckOutPage;
import MavenTime.ConfirmationPage;
import MavenTime.Orders;
import MavenTime.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import POM_Maven.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	String product = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitLogin(HashMap<String, String> input) throws InterruptedException, IOException {
		ProductCatalogue PC = LP.logintoApp(input.get("email"), input.get("password"));
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(input.get("productName"));
		PC.ClickOnCartButton();
		Cart CT = new Cart(driver);
		Boolean match = CT.VerifyProductDisplay((input.get("productName")));
		Assert.assertTrue(match);
		CheckOutPage COP = CT.goToCheckout();
		COP.SelectOption("India");
		ConfirmationPage CP= COP.submitOrder();
		String confirmMessage = CP.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	// New test for order tab validation
	@Test(dependsOnMethods = { "submitLogin" })
	public void VerifyOrders() {
		ProductCatalogue PC = LP.logintoApp("rrana@learn.com", "Sam@12345");
		Orders ODC = PC.goToOrdersPage();
		Assert.assertTrue(ODC.VerifyOrderDisplayed(product));
	}
	public String takeScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot TS=(TakesScreenshot)driver;
		File source=TS.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = jsontoHash(System.getProperty("user.dir") + "//src//test//java//testData//testdata.json");
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "rrana@learn.com");
//	map.put("password", "Sam@12345");
//	map.put("productName", "ADIDAS ORIGINAL");
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "rrana1@learn.com");
//	map1.put("password", "Sam@12345");
//	map1.put("productName", "ZARA COAT 3");
	return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
