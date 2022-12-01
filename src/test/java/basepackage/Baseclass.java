package basepackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.timeutils;

public class Baseclass {

	public static Properties prop= new Properties();
	public static WebDriver driver;
	
	public Baseclass() {
		try {
			FileInputStream file=new FileInputStream("C:\\Users\\kmbyk\\eclipse-workspace\\amazon\\src\\test\\java\\environmentvariables\\config.properties");
			prop.load(file);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException a) {
			a.printStackTrace();
		}
	}
	public static void initiation(){
	String browsername = prop.getProperty("browser");
	if (browsername.equals("chrome")) {//if (browsername.equals("chrome") 
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver=new ChromeDriver();
	}
	else if (browsername.equals("Firefox")) {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver=new FirefoxDriver();
	}
	else {
		System.out.println("choose browser as either chrome or firefox");
	}

		driver.get(prop.getProperty("url"));
	
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(timeutils.pageloadtime, TimeUnit.SECONDS);
	}	
	 public static void screenshots(String screenshotname) {
		 File scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try { 
		 FileUtils.copyFile(scrFile, new File("C:\\Users\\kmbyk\\eclipse-workspace\\amazon\\src\\test\\java\\screenshots\\Screenshots " +screenshotname+ ".jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	 }
}
