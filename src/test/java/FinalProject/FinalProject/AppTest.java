package FinalProject.FinalProject;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	String url;
	String FirstName = "elyan";
	String LastName = "husam";
	String Email= FirstName+LastName+"@gmail.com";
Random rand = new Random();
JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@Test(priority = 1)
	public void GetUrl() {
		
		 url ="https://magento.softwaretestingboard.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(url);
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
	}
	
    @Test(priority = 3)
    public void sign_in() throws InterruptedException{
    	driver.findElement(By.linkText("Sign In")).click();
    	driver.findElement(By.id("email")).sendKeys(Email);
    	driver.findElement(By.id("pass")).sendKeys("123rsH*&");
    	driver.findElement(By.id("send2")).click();
    }
    
    
    
    @Test(priority = 4)
    public void productDetails() {
    	driver.get("https://magento.softwaretestingboard.com/men/bottoms-men/pants-men.html");
    driver.findElement(By.linkText("Aether Gym Pant")).click();
  System.out.println(driver.findElement(By.cssSelector(".data.item.content")).getText());  
        }
    
    @Test(priority = 5)
    public void addTwoRandomPantsToCart() throws InterruptedException {
        js.executeScript("window.scrollTo(0, 500)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Random rand = new Random();

        // 1. احصل على روابط المنتجات
        List<WebElement> productLinks = driver.findElements(By.cssSelector(".product-item a.product-item-link"));

        if (productLinks.size() < 2) {
            System.out.println("Not enough products to add two.");
            return;
        }

        // 2. اختر رابطين عشوائيين بدون تكرار
        Set<String> selectedHrefs = new HashSet<>();
        while (selectedHrefs.size() < 2) {
            String href = productLinks.get(rand.nextInt(productLinks.size())).getAttribute("href");
            selectedHrefs.add(href);
        }

        // 3. أضف كل منتج إلى السلة
        for (String href : selectedHrefs) {
            driver.get(href); // انتقل لصفحة المنتج
            Thread.sleep(2000); // انتظر التحميل

            // اختر حجم
            List<WebElement> sizes = driver.findElements(By.cssSelector(".swatch-attribute.size .swatch-option"));
            sizes.removeIf(option -> !option.isEnabled());

            if (!sizes.isEmpty()) {
                WebElement size = sizes.get(rand.nextInt(sizes.size()));
                size.click();
                System.out.println("Selected size: " + size.getAttribute("option-label"));
            }

            // اختر لون
            List<WebElement> colors = driver.findElements(By.cssSelector(".swatch-attribute.color .swatch-option"));
            colors.removeIf(option -> !option.isEnabled());

            if (!colors.isEmpty()) {
                WebElement color = colors.get(rand.nextInt(colors.size()));
                color.click();
                System.out.println("Selected color: " + color.getAttribute("option-label"));
            }

            // أضف للسلة
            Thread.sleep(1000);
            WebElement addToCartBtn = driver.findElement(By.id("product-addtocart-button"));
            if (addToCartBtn.isEnabled()) {
                addToCartBtn.click();
                System.out.println("Product added to cart.");
            } else {
                System.out.println("Add to Cart button is disabled.");
            }

            Thread.sleep(3000); // انتظر بعد الإضافة
        }

        System.out.println("✅ تمت إضافة منتجين مختلفين إلى السلة بنجاح.");
    }


    
    @Test(priority =6)
    public void ViewingandModifyingtheCart() throws InterruptedException{
    	Thread.sleep(2000);    
        js.executeScript("window.scrollTo(500 , 0)");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".action.showcart")).click();
   driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']")).click();
   Thread.sleep(2000);
   WebElement qtyInput = driver.findElement(By.xpath("(//td[contains(@class, 'col qty')]//input)[1]"));
qtyInput.clear();
qtyInput.sendKeys("2");
Thread.sleep(2000);
 
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
}


@Test(priority = 8)
public void PrivacyPolicyAccessibility() {
	driver.get(url);
	js.executeScript("window.scrollTo(0,2700)");
WebElement footer =	driver.findElement(By.cssSelector(".footer.content"));
	footer.findElement(By.linkText("Privacy and Cookie Policy")).click();
	
}
@Test(priority = 9)
public void ContactUsPage() throws InterruptedException, IOException {
     js.executeScript("window.scrollTo(0, 4500)");
    Thread.sleep(2000);
    driver.findElement(By.linkText("Contact Us")).click();
    TakesScreenshot scrShot = ((TakesScreenshot) driver);
    File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
    String projectPath = System.getProperty("user.dir");
    File destFile = new File(projectPath + "/src/test/java/screenshot/1.Png");
    FileHandler.copy(srcFile, destFile);

    System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
}

@Test(priority = 10)
public void LogOut() {
	driver.findElement(By.xpath("//button[@tabindex='-1']")).click();
	driver.findElement(By.linkText("Sign Out")).click();
	
} 
}