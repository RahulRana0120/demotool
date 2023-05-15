package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MavenTime.Cart;
import MavenTime.Orders;

public class abstractValues {
	WebDriver driver;
	public abstractValues(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;
	By cartGrid=By.xpath("//button[@routerlink='/dashboard/cart']");
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	public Cart ClickOnCartButton()
	{
	cartButton.click();
	webdriverwaitmethod(cartGrid);
	Cart CT = new Cart(driver);
	return CT;
	}
	public void webdriverwaitmethod(By findBy)
	{
		WebDriverWait WT = new WebDriverWait(driver, Duration.ofSeconds(4));
		WT.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void webdriverwaitForElement(WebElement findBy)
	{
		WebDriverWait WT = new WebDriverWait(driver, Duration.ofSeconds(4));
		WT.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
		//WebDriverWait WT = new WebDriverWait(driver, Duration.ofSeconds(4));
		//WT.until(ExpectedConditions.invisibilityOf(ele));
	}
	public Orders goToOrdersPage()
	{
		orderHeader.click();
		Orders OD = new Orders(driver);
		return OD;
	}

}
