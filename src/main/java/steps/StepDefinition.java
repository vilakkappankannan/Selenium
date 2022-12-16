package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StepDefinition {

    public WebDriver driver;
    @Given("login to mail")
    public void loginToMail() {
        System.setProperty("webdriver.chrome.driver", "/Volumes/Google Chrome/Google Chrome.app");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.get("https://dpsopsportaltsys2.ihsdev.discoverfinancial.com/dcisc-sso-web/login");
    }

    @When("enter the username and password")
    public void enterTheUsernameAndPassword() {

    }

    @Then("login successful")
    public void loginSuccessful() {
    }
}
