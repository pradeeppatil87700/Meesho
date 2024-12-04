package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class demo {

    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Asus\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Open the Meesho website
        driver.get("https://www.meesho.com/");
        
        
         
//        private By HoverElement = By.cssSelector("div span[class='sc-eDvSVe kYLKqU']");
//        private By loginButton = By.id("loginbutton");
//  .       private By supplierText = By.xpath("div[class='MuiBox-root css-mqxh8y'] h1");


        // Maximize the browser window for better visibility
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector(".SupplierBanner__StyledButton-sc-2qsypi-2.jmVeNL")).click();        
        
        Set<String> Windows = driver.getWindowHandles();
        Iterator<String>it = Windows.iterator(); 
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);
        
          driver.findElement(By.id("loginbutton")).click();
        
         Thread.sleep(4000); 
       WebElement supplierText =  driver.findElement(By.cssSelector("div[class='MuiBox-root css-mqxh8y'] h1"));
         String getsupplierText = supplierText.getText();
         
         System.out.println(getsupplierText);
         
        String actualText = getsupplierText;
        String expectedText = "Login to your supplier panel";
         Assert.assertEquals(actualText, expectedText);
         
         System.out.println("Pass Login Button");
        
         driver.navigate().back();
         
         driver.findElement(By.cssSelector(".primary-button.startselling.w-button[data-position='header']")).click();
         Thread.sleep(4000); 
         
         
         WebElement sellerText =  driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-h1.css-hzpjot"));
           String getsellerText = sellerText.getText();
           
           String actualsellerText = getsellerText;
           String expectedsellerText = "Welcome to Meesho";
            Assert.assertEquals(actualsellerText, expectedsellerText);
            
            System.out.println(getsellerText);
            System.out.println("Pass Supplier button");
            
            driver.navigate().back();
            
           driver.close();
           
           driver.switchTo().window(parentId); 
           
          // driver.navigate().back();
           
           
         
         
         
         

        
        
        
        
        
        

        
        
        
    
    }
}
