package FinalProject.FinalProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest extends TestData {

    @BeforeTest
    public void mySetup() {
        Setup();
    }

    @Test(priority = 1)
    public void GetUrl() {
        driver.get(url);
       String ActualURL = driver.getCurrentUrl();
        Assert.assertEquals(ActualURL, ExpectedURL);
    }

    @Test(priority = 2)
    public void userRegistrationProsses() throws InterruptedException {
        driver.findElement(By.partialLinkText("Create")).click();
        driver.findElement(By.id("firstname")).sendKeys(FirstName);
        driver.findElement(By.id("lastname")).sendKeys(LastName);
        driver.findElement(By.id("email_address")).sendKeys(Email);
        driver.findElement(By.id("password")).sendKeys("123rsH*&");
        Thread.sleep(2000);
        driver.findElement(By.id("password-confirmation")).sendKeys("123rsH*&");
        driver.findElement(By.cssSelector(".action.submit.primary")).click(); 
      String ActualRegisterMsg = driver.findElement(By.cssSelector(".message-success.success.message")).getText();
        Assert.assertEquals(ActualRegisterMsg , ExpectedRegisterMsg);
        
        driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']")).click();
        driver.findElement(By.xpath("//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")).click();
    }

    @Test(priority = 3)
    public void sign_in() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.id("email")).sendKeys(Email);
        driver.findElement(By.id("pass")).sendKeys("123rsH*&");
        driver.findElement(By.id("send2")).click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement welcomeUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("span.logged-in")
        ));
        
        String welcomeText = welcomeUser.getText();
        boolean ActualwelcomeText = welcomeText.startsWith("Welcome,");
        Assert.assertEquals(ActualwelcomeText, true);
    }
    @Test(priority = 4)
    public void productDetails() {
        driver.get(url + "men/bottoms-men/pants-men.html");
        driver.findElement(By.linkText("Aether Gym Pant")).click();
        WebElement productTitle = driver.findElement(By.cssSelector("span.base"));
        String ActualProductTitle = productTitle.getText();
        Assert.assertEquals(ActualProductTitle , ExpectedProductTitle);
    }

    @Test(priority = 5)
    public void AddingProductstoCart() throws InterruptedException {
        js.executeScript("window.scrollTo(0, 500)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> productLinks = driver.findElements(By.cssSelector(".product-item a.product-item-link"));
        if (productLinks.size() < 2) return;

        Set<String> selectedHrefs = new HashSet<>();
        while (selectedHrefs.size() < 2) {
            selectedHrefs.add(productLinks.get(rand.nextInt(productLinks.size())).getAttribute("href"));
        }

        for (int i = 0; i < selectedHrefs.size(); i++) {
            String href = (String) selectedHrefs.toArray()[i];
            driver.get(href);
            Thread.sleep(2000);

            List<WebElement> sizes = driver.findElements(By.cssSelector(".swatch-attribute.size .swatch-option"));
            sizes.removeIf(option -> !option.isEnabled());
            if (!sizes.isEmpty()) sizes.get(rand.nextInt(sizes.size())).click();

            List<WebElement> colors = driver.findElements(By.cssSelector(".swatch-attribute.color .swatch-option"));
            colors.removeIf(option -> !option.isEnabled());
            if (!colors.isEmpty()) colors.get(rand.nextInt(colors.size())).click();

            WebElement addToCartBtn = driver.findElement(By.id("product-addtocart-button"));
            if (addToCartBtn.isEnabled()) addToCartBtn.click();
            Thread.sleep(3000);
        }

        WebElement cartIcon = driver.findElement(By.cssSelector(".action.showcart"));
        boolean ActualCartIcon = cartIcon.isDisplayed();
        Assert.assertEquals(ActualCartIcon , true);
    }

    @Test(priority =6)
    public void ViewingandModifyingtheCart() throws InterruptedException{
    	Thread.sleep(2000);    
        js.executeScript("window.scrollTo(0 , 500)");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".action.showcart")).click();
   driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']")).click();
   Thread.sleep(2000);
   WebElement qtyInput = driver.findElement(By.xpath("(//td[contains(@class, 'col qty')]//input)[1]"));
qtyInput.clear();
qtyInput.sendKeys("2");
Thread.sleep(2000);

String actualQty = qtyInput.getAttribute("value");
Assert.assertEquals(actualQty, ExpectedQty);


 
driver.findElement(By.xpath("//button[@title='Update Shopping Cart']")).click();
Thread.sleep(2000);
js.executeScript("window.scrollTo(0,500)");
 List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".action.action-delete"));
   deleteButtons.get(1).click();    
  
    }
    
   
    
    @Test(priority = 7)
    public void ProceedingtoCheckout () throws InterruptedException {
        driver.findElement(By.cssSelector(".action.showcart")).click();
        driver.findElement(By.id("top-cart-btn-checkout")).click();
    
    String ActualPage = driver.getTitle();    
    Assert.assertEquals(ActualPage, ExpectedPage);
    
    }
    
    
    @Test(priority = 8)
    public void PrivacyPolicyAccessibility() {
        driver.get(url);
        js.executeScript("window.scrollTo(0,2700)");
        WebElement footer = driver.findElement(By.cssSelector(".footer.content"));
        footer.findElement(By.linkText("Privacy and Cookie Policy")).click();
        boolean ActualPrivacyPolicyPage = driver.getCurrentUrl().toLowerCase().contains("privacy");
        Assert.assertEquals(ActualPrivacyPolicyPage , true);
    }

    @Test(priority = 9)
    public void ContactUsPage() throws InterruptedException, IOException {
        js.executeScript("window.scrollTo(0, 4500)");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Contact Us")).click();
        
        System.out.println("Whoops, our bad...\r\n+ The page you requested was not found, and we have a fine guess why.");
        
     String ActualTitle = driver.getTitle();
     Assert.assertEquals(ActualTitle,ExpectedTitle);
       
     
     TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir");
        File destFile = new File(projectPath + "/src/test/java/screenshot/1.Png");
        FileHandler.copy(srcFile, destFile);
    }

    @Test(priority = 10)
    public void LogOut() throws InterruptedException {
        driver.findElement(By.xpath("//button[@tabindex='-1']")).click();
        driver.findElement(By.linkText("Sign Out")).click();
  Thread.sleep(3000);
        WebElement signIn = driver.findElement(By.xpath("//span[@class='base']"));
     
        String ActualsignInPage = signIn.getText();        
       Assert.assertEquals(ActualsignInPage , ExpectedsignInPage);
    }
    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    	
    }
}