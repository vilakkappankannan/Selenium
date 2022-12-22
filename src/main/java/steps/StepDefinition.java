package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StepDefinition {

    public WebDriver driver;
    @Given("login to mail")
    public void loginToMail() {
//        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.get("");
    }

    @When("enter the username and password")
    public void enterTheUsernameAndPassword() throws Throwable {

        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("submit")).click();


    }

    @Then("login successful")
    public void loginSuccessful() {
    }
}
