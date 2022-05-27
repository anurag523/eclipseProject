package in.co.mtel.brlps;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class cpmsCategoryMapping extends functions {
	//static WebDriver driver;
	static WebDriverWait wait;
	static String dirPath=System.getProperty("user.dir");//This will give the path upto the main foler that is in this case Anurag 
	
	public static void main(String[] args) throws IOException {
		
		FileReader fr=new FileReader(dirPath+"\\src\\test\\resources\\configfiles\\config.properties");
		Properties prop=new Properties();
		prop.load(fr);
		
		System.out.println(dirPath);
		Browser(prop.getProperty("browser"), prop.getProperty("cpmsurl"));
        login(prop.getProperty("cpmsUsername"),prop.getProperty("cpmsPassword"));
    }
}
