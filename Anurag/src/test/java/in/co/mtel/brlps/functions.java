package in.co.mtel.brlps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class functions {
	static WebDriver driver;
	static String[][] testData;
	static int totalRows;
	
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
	public static void click(String xpath)
	{
		WebElement ClickableButton = new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		ClickableButton.click();
	}
	public static void dropDown(String dropdown,String selectvalue) throws InterruptedException 
	{
		System.out.println(dropdown);
		System.out.println(selectvalue);
		
		String dpd="\""+dropdown+"\"";
		WebElement dd=driver.findElement(By.xpath(dpd));
		Thread.sleep(1000);
		Select select=new Select(dd);
		select.selectByVisibleText(selectvalue);
	}
	public static void editCategory() throws InterruptedException
	{
		 Actions action = new Actions(driver);
		 
		WebElement costing_method = driver.findElement(By.xpath("//select[@name='property_cost_method']"));
		Select selectObject=new Select(costing_method);
		
		try {
			selectObject.selectByVisibleText("First In First Out (FIFO)");
		} catch (Exception e) {
			if(new WebDriverWait(driver, Duration.ofSeconds(60))
			        .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-content']"))).isDisplayed())
			{
				driver.findElement(By.xpath("//span[contains(text(),'Ok')]")).click();
			}
		}
				//code to edit the inventory valuation
		WebElement inventoryValuation = driver.findElement(By.xpath("//select[@name='property_valuation']"));
		Select selectIV=new Select(inventoryValuation);
		selectIV.selectByVisibleText("Automated");
		
		//change the stock valuation account
		driver.findElement(By.xpath("//div[@name='property_stock_valuation_account_id']//child::input")).clear();
		driver.findElement(By.xpath("//div[@name='property_stock_valuation_account_id']//child::input")).sendKeys("100310 Inventories");
		Thread.sleep(500);
		action.sendKeys(Keys.ENTER).perform();
		
		//change the stock Stock Journal
		driver.findElement(By.xpath("//div[@name='property_stock_journal']//child::input")).clear();
		driver.findElement(By.xpath("//div[@name='property_stock_journal']//child::input")).sendKeys("Inventory Valuation");
		Thread.sleep(500);
		action.sendKeys(Keys.ENTER).perform();
		
		//change the stock Stock input Account
		driver.findElement(By.xpath("//div[@name='property_stock_account_input_categ_id']//child::input")).clear();
		driver.findElement(By.xpath("//div[@name='property_stock_account_input_categ_id']//child::input")).sendKeys("212400 Stock Input Account");
		Thread.sleep(500);
		action.sendKeys(Keys.ENTER).perform();
		
		//change the stock Stock Output Account
		driver.findElement(By.xpath("//div[@name='property_stock_account_output_categ_id']//child::input")).clear();
		driver.findElement(By.xpath("//div[@name='property_stock_account_output_categ_id']//child::input")).sendKeys("212500 Stock Output Account");
		Thread.sleep(500);
		action.sendKeys(Keys.ENTER).perform();
		
		driver.findElement(By.xpath("//span[contains(text(),'Save')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Product Categories')]")).click();
		
		//below line is to wait till create button display
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Create')]")));
	}
}






