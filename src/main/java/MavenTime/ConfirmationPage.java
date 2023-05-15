package MavenTime;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.abstractValues;

public class ConfirmationPage extends abstractValues {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	public String getConfirmationMessage() {
		CheckOutPage cp = new CheckOutPage(driver);
		return confirmationMessage.getText();
	}
}
