package RahulsWorld.MavenTime;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest_Dup {
	@Test
	public void E2ETest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("rrana@learn.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sam@12345");
		driver.findElement(By.id("login")).click();

		
		Thread.sleep(5000);
		List<WebElement> products = driver.findElements(By.xpath("//h5"));
		WebDriverWait WT = new WebDriverWait(driver, Duration.ofSeconds(4));
		// using java streams
		/*
		 * WebElement prods =
		 * products.stream().filter(prod->prod.findElement(By.cssSelector("b")).getText(
		 * ).equals("adidas original")).findFirst().orElse(null);
		 * prods.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
		 */
		// Using standard code
		for (int i = 0; i < products.size(); i++) {
			String productName = products.get(i).getText();
			if (productName.equalsIgnoreCase("adidas original")) {
				driver.findElements(By.xpath("//button[@class='btn w-10 rounded']")).get(i).click();
				WT.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
			}
		}
		WT.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		WT.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean BL = cartItems.stream().anyMatch(ci -> ci.getText().equalsIgnoreCase("adidas original"));
		AssertJUnit.assertTrue(BL);
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		WT.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
		//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		//List<WebElement> countries = driver.findElements(By.xpath("//i[@class='fa fa-search']"));
		//countries.stream().filter(county -> county.getText().equalsIgnoreCase("India")).forEach(test -> test.click());
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		driver.findElement(By.cssSelector(".action__submit")).click();
		driver.quit();
	}
}
