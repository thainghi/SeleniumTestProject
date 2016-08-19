import java.util.List;

import javax.swing.text.Element;

import org.openqa.selenium.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

import com.thoughtworks.selenium.Selenium.*;


public class MyClass {
	public enum BROWSER {
        FIREFOX,
        CHROME
    }
	
	public WebDriver openBrowser(BROWSER browserType){
		WebDriver browser=null;
		switch(browserType){
		case FIREFOX:
			browser = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "E:\\My Data\\Research\\Selenium\\chromedriver.exe");
			browser = new ChromeDriver();			
		}
		return browser;		
		
	}
	
	public void checkGmail(WebDriver browser, String username, String password) throws InterruptedException{
		browser.findElement(By.id("gmail-sign-in")).click();
		//Send user/pass
		browser.findElement(By.xpath("//*[@name='Email']")).sendKeys(username);
		browser.findElement(By.xpath("//*[@name='signIn']")).click();
		Thread.sleep(1000);
		browser.findElement(By.name("Passwd")).sendKeys(password);
		browser.findElement(By.id("signIn")).click();
		//Check contains
		WebDriverWait wait = new WebDriverWait(browser, 20);
		wait.until(ExpectedConditions.titleContains("Inbox"));
		
		java.util.List<WebElement> links = browser.findElements(By.tagName("xY a4W"));
		System.out.println(links.toString());
		//Logout
		browser.findElement(By.xpath("//*[@class='gb_b gb_8a gb_R']")).click();
		browser.findElement(By.id("gb_71")).click();
		wait.until(ExpectedConditions.titleIs("Gmail"));
		
		
		
		
	
		
		
		
	}
	public void wait(WebDriver browser, int timeout, String expectedText){
		WebDriverWait wait = new WebDriverWait(browser, timeout);
		wait.until(ExpectedConditions.titleContains(expectedText));
		
	}
	
	public void openNGA(WebDriver browser, String ngaUrl) throws InterruptedException{
		String username = "nghiJuly11@hpe.com";
		//Open NGA
		browser.get(ngaUrl);
		wait(browser, 20, "SAAS Mock");
		//Login NGA
		browser.findElement(By.id("mock-portal-authenticate")).click();
		WebElement table_element = browser.findElement(By.id("recentlyused"));
		List<WebElement>  tr_collection = table_element.findElements(By.tagName("tr"));
		System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
        for(WebElement trElement : tr_collection)
        {
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            for(WebElement tdElement : td_collection){
            	if(tdElement.getText().equals(username)){
                	tdElement.click(); 
                	break;
                	}            	
            }
            
            
          
        }
        browser.findElement(By.id("mock-portal-login")).click();
        browser.findElement(By.xpath("//*[@id=\"mock-portal-Horizon\"]/descendant::span[text()='MQM']")).click();
        String currentURL = browser.getCurrentUrl();
        Thread.sleep(3000);
        System.out.println(currentURL.replace("localhost.emea.hpqcorp.net", "16.153.233.164"));
        browser.get(currentURL.replace("localhost.emea.hpqcorp.net", "16.153.233.164"));
        
	}
	


}
