import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Test {
	
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		  
		  MyClass myClass = new MyClass();
//		  WebDriver browser = myClass.openBrowser(MyClass.BROWSER.FIREFOX);
		  DesiredCapabilities capability = DesiredCapabilities.internetExplorer(); 
		  WebDriver browser = new RemoteWebDriver(new URL("http://16.153.233.151:4444/wd/hub"),capability);

		  String ngaUrl = "http://16.153.233.164:8080/portal";
		  String syncUrl = "http://16.153.233.164:8080/sync/ui/?p=ny1v8r705er64unn6znvmdl4j";
		  myClass.openNGA(browser, ngaUrl);
		  // Open SYNC 
		  browser.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		  browser.get(syncUrl);
		  browser.findElement(By.xpath("//div[@class='toolbar-item agmicon-view-list']")).click();
		  //Right click on button and choose action
		  WebElement addLink = browser.findElement(By.cssSelector("button.agmActionFocus"));
		  Actions action = new Actions(browser);
		  action.moveToElement(addLink);
		  action.contextClick(addLink);//sendKeys(Keys.ARROW_DOWN).build().perform();
		  browser.findElement(By.cssSelector("span.agmActionIcon")).click();
		  
		  }
}
