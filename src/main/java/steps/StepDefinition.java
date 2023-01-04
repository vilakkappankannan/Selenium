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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.get("https://phptravels.org/clientarea.php");

    }

    @When("enter the username and password")
    public void enterTheUsernameAndPassword() throws Throwable {

        driver.findElement(By.id("inputEmail")).sendKeys("");
        driver.findElement(By.id("inputPassword")).sendKeys("");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("Primary_Navbar_Service")).click();

    }

    @Then("login successful")
    public void loginSuccessful() {
    }
}
