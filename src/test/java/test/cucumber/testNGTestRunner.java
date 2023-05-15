package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "POM_Maven.stepDefinitons", monochrome = true, plugin = {
		"html:target/cucumber.html" })
public class testNGTestRunner extends AbstractTestNGCucumberTests{

}
