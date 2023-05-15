package MavenTime;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractComponents.abstractValues;

public class Orders extends abstractValues {
	WebDriver driver;
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> OrderColumn;

	public Orders(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrderDisplayed(String productName) {
		Boolean match = OrderColumn.stream().anyMatch(ci -> ci.getText().equalsIgnoreCase(productName));
		return match;
	}

}
