package in.co.mtel.brlps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class functions {
	static WebDriver driver;
	
	
	public static void Browser(String browser,String url) throws IOException {

		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();// This is for latest version
			// WebDriverManager.chromedriver().version("VERSION_NO").setup(); THIS IS FOR
			// SPECIFIC VERSION OF THE CHROME

			ChromeOptions ChromeOption = new ChromeOptions();
			ChromeOption.setCapability(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, true);
			ChromeOption.addArguments("--disable-infobars");
			ChromeOption.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
			// ChromeOption.setPageLoadStrategy(PageLoadStrategy.NONE);
			driver = new ChromeDriver(ChromeOption);
			// driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else {
			System.out.println("The wrong browser has been selected");
		}
		driver.manage().window().maximize();
		driver.get(url);
		
	}
	public static void login(String username,String password)
	{
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
		
	}
}
