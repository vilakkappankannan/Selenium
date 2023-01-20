//package steps;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.Iterator;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//public class StepDefinition {
//
//    public WebDriver driver;
//    @Given("login to mail")
//    public void loginToMail() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
//        driver.get("https://old.reddit.com/login");
//    }
//
//    @When("enter the username and password")
//    public void enterTheUsernameAndPassword() throws Throwable {
//
//        driver.findElement(By.id("user_login")).sendKeys("");
//        driver.findElement(By.id("passwd_login")).sendKeys("");
//        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[5]/button")).click();
//        driver.findElement(By.xpath("//*[@id=\"redesign-beta-optin-btn\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div/div[2]/div[2]/div[1]/div[1]/input")).click();
//
////        Select community = new Select(driver.findElement(By.xpath("//*[@id="AppRouter-main-content"]/div/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/i")));
////        community.selectByValue("u/vilakkappan");
//
//        Duration timeoutInSeconds = null;
//        WebDriverWait wait = new WebDriverWait(driver, null);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/i")));
//
////
//        driver.findElement(By.xpath("//*[@id=\"AppRouter-main-content\"]/div/div/div[2]/div[3]/div[1]/div[2]/div[3]/div[2]/div[1]/div/textarea")).sendKeys("Testing");
////        driver.findElement(By.name("button")).click();
//
//
//
//
//    }
//
//    @Then("login successful")
//    public void loginSuccessful() {
//    }
//}
