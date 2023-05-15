package MavenTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.abstractValues;

public class CheckOutPage extends abstractValues {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".action__submit")
	private WebElement submit;

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;

	private By results = By.cssSelector(".ta-results");

	public void SelectOption(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		webdriverwaitmethod(By.cssSelector(".ta-results"));
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);

	}
}
