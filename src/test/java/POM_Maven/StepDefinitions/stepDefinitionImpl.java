package POM_Maven.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import MavenTime.Cart;
import MavenTime.CheckOutPage;
import MavenTime.ConfirmationPage;
import MavenTime.LandingPage;
import MavenTime.ProductCatalogue;
import POM_Maven.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {
	public LandingPage LP;
	public ProductCatalogue PC;
	public ConfirmationPage CP;
	public CheckOutPage COP;

	@Given("user landed on ECommerce Page")
	public void user_landed_on_ECommerce_Page() throws IOException, InterruptedException {
		LP = LaunchAppliction();
	}

	@Given("^Logged in with username (.+) and password(.+)$")
	public void Logged_in_username_and_password(String username, String password) {
		PC = LP.logintoApp(username, password);
	}

	@When("^User add a productName(.+) to Cart$")
	public void User_adds_a_product(String productName) throws InterruptedException {
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(productName);
	}

	@When("^Checks out (.+) and submits the order$")
	public void Checks_out_and_submits_the_order(String productName) {
		Cart CT = new Cart(driver);
		Boolean match = CT.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		COP = CT.goToCheckout();
		COP.SelectOption("India");
		CP = COP.submitOrder();
	}

	// THANKYOU FOR THE ORDER."
	@Then("{string} message is displayed on the confirmation page")
	public void message_is_displayed_on_the_confirmation_page(String string) {
		String confirmMessage = CP.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
}

