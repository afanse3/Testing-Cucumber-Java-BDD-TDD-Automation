package StepDefTdd;
import config.env_target;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTdd extends env_target {
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        //Set driver path
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        //Maximize the window driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Set URL
        driver.get(baseUrl);
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type=\"submit\"][@id=\"login-button\"]"))
        );
    }

    @When("^User input (.*) and (.*)$")
    public void userFillUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @And("User click login button")
    public void userClickLoginButton() {
        driver.findElement(By.xpath("//*[@type=\"submit\"][@id=\"login-button\"]")).click();
    }

    @Then("^User get verify login (.*)$")
    public void userVerifyLoginResultTdd(String result) {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        if (result == "Passed"){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]")
                    ));
        } else if (result == "Failed"){
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"))
            );
        }
        driver.quit();
    }
}
