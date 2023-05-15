package MavenTime;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.abstractValues;

public class LandingPage extends abstractValues {
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory
	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement userPassword;
	@FindBy(id = "login")
	WebElement Submit;
	@FindBy(css = "[class*='flyInOut']")
	WebElement InvalidLogin;

	public void invokeURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogue logintoApp(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		Submit.click();
		ProductCatalogue PC=new ProductCatalogue(driver);
		return PC;
	}
	public String errorLogin()
	{
		webdriverwaitForElement(InvalidLogin);
		String message =InvalidLogin.getText();
		return message;
		
	}
}
