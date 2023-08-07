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

public class PurchaseTdd extends env_target {
    @Given("User is already login and on inventory list")
    public void userIsAlreadyLoginAndOnInventoryList() {
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
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@type=\"submit\"][@id=\"login-button\"]")).click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]"))
        );
    }
    @When("User click add to cart button to one of the items")
    public void userClickAddToCartButtonToOneOfTheItems() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")).click();
    }
    @And("User click cart icon on top-right")
    public void userClickCartIconOnTopRight() {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }
    @And("User check the items they want to buy")
    public void userCheckTheItemsTheyWantToBuy() {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]"))
        );
    }
    @And("User click checkout button")
    public void userClickCheckoutButton() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    }
    @And("^User fill (.*), (.*), and (.*)$")
    public void userFillFirstNameLastNameAndZipCode(String firstname, String lastname, String zipcode) {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys(firstname);
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys(lastname);
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys(zipcode);
    }
    @And("User click continue button")
    public void userClickContinueButton() {
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
    }
    @And("^User check their order (.*)$")
    public void userCheckTheirOrderResult(String result) {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        if (result.equals("Passed")){
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]"))
            );
        } else if (result.equals("Failed")){
            try {
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]"))
                );
                throw new RuntimeException("Skenario gagal karena ini negative case.");
            } finally {
            driver.quit();
        }
        }
    }
    @And("User click finish button")
    public void userClickFinishButton() {
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
    }

    @Then("User get the order result")
    public void userGetTheMessageThatTheOrderWasSuccessful() {
        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout_complete_container\"]"))
        );
        driver.quit();
    }
}
