package POM_Maven.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MavenTime.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage LP;

	public WebDriver InitializeDriver() throws IOException {

		// Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//POM_Maven//resourses//GlobalData.properties");
		prop.load(fis);
		String BrowserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		if (BrowserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if (BrowserName.contains("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/rahul.rana//GeckoDriver//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().setSize(new Dimension(1440, 900));
		return driver;
	}

	public List<HashMap<String, String>> jsontoHash(String FilePath) throws IOException {
		// Read JSON to String
		String jsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);
		// src\test\java\testData\testdata.json
		// String to Hash using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot TS = (TakesScreenshot) driver;
		File source = TS.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reportss//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reportss//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchAppliction() throws IOException, InterruptedException {
		driver = InitializeDriver();
		LP = new LandingPage(driver);
		LP.invokeURL();
		return LP;
	}

	@AfterMethod(alwaysRun = true)
	public void CloseBrowser() {
		driver.close();
	}

}