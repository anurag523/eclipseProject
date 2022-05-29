package in.co.mtel.brlps;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class cpmsCategoryMapping extends functions {
	//static WebDriver driver;
	static WebDriverWait wait;
	static String dirPath=System.getProperty("user.dir");//This will give the path upto the main foler that is in this case Anurag 
	static Scanner sc=new Scanner(System.in);
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		FileReader fr=new FileReader(dirPath+"\\src\\test\\resources\\configfiles\\config.properties");
		Properties prop=new Properties();
		prop.load(fr);
		
		FileReader fr1=new FileReader(dirPath+"\\src\\test\\resources\\configfiles\\locators.properties");
		Properties prop1=new Properties();
		prop1.load(fr1);
		
		System.out.println(dirPath);
		Browser(prop.getProperty("browser"), prop.getProperty("cpmsurl"));
        login(prop.getProperty("cpmsUsername"),prop.getProperty("cpmsPassword"));
        
        //command to read the excel file for category
        ReadXLSData red=new ReadXLSData();
		click(prop1.getProperty("menuButton"));//click menu button
		click(prop1.getProperty("inventoryButton"));//click inventory button or apps
		click(prop1.getProperty("ConfigurationButton"));//click menu configuration
		click(prop1.getProperty("ProductCateroySubMenu"));//click submenu product category
		
		for(int i=0;i<25;i++) 
		{
			red.getData(prop.getProperty("Company"));//display company name
			System.out.println("Enter the serial number of Grameen Bazaar to select: ");
			int GrameenBazaarIndex=sc.nextInt();
			String GrameenBazaarName=testData[GrameenBazaarIndex][1];
			System.out.println("The Selected Grameen Bazaar is "+GrameenBazaarName);
			click(prop1.getProperty("CompanySelectionDropdown"));//click dropdown for selection of company.
			String GBClickable="//span[contains(text(),'"+GrameenBazaarName+"')]";
			try {
				driver.findElement(By.xpath(GBClickable)).click();
			} catch (Exception e) {
				System.out.println("The given grameen bazaar is not found. Please select Grameen Bazaar");
				i--;
				continue;
			}
			
			red.getData(prop.getProperty("Category"));//display category name
			System.out.println("Enter the serial number of Category from where to start: ");
			int CategoryIndex=sc.nextInt();
			//now to click the product category
			for(int j=CategoryIndex;j<totalRows;j++) 
			{
				System.out.println("Total Rows:"+totalRows);
				String Categoryxpath="//td[text()='"+testData[j][1]+"']";
				System.out.println(Categoryxpath);
				click(Categoryxpath);
				click(prop1.getProperty("CategoryEditButton"));
				Thread.sleep(2000);
				editCategory();

			} 
			
		}
    }
}
