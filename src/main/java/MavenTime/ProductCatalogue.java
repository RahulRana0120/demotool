package MavenTime;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.abstractValues;

public class ProductCatalogue extends abstractValues {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	By cartGrid=By.xpath("//button[@routerlink='/dashboard/cart']");

	public List<WebElement> getProductList() {
		webdriverwaitmethod(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		webdriverwaitmethod(toastmessage);
		waitForElementToDisappear(spinner);
	}
	public Cart CTO()
	{
		Cart CT = new Cart(driver);
		return CT;
	}

}
