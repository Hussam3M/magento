package FinalProject.FinalProject;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestData {
    WebDriver driver = new ChromeDriver();
    String url = "https://magento.softwaretestingboard.com/";
    Random rand = new Random();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String FirstName= "husam";
    String LastName = "Zyoud3";
   String Email = FirstName + LastName + "@gmail.com";
   
    String ExpectedURL = url ;
    String ExpectedRegisterMsg = "Thank you for registering with Main Website Store.";
    String ExpectedProductTitle = "Aether Gym Pant";
    String ExpectedQty = "2";
    String ExpectedPage = "Checkout";
    String ExpectedTitle = "contact us";
    String ExpectedsignInPage = "You are signed out";

public void Setup() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}