package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtiltiy {

	public WebDriver getdriver(String browserName) {
		WebDriver driver = null;
		if (browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

			driver.get(
					"http://automationpractice.com/index.php?controller=authentication&multi-shipping=0&display_guest_checkout=0&back=http%3A%2F%2Fautomationpractice.com%2Findex.php%3Fcontroller%3Dorder%26step%3D1%26multi-shipping%3D0");
		}
		return driver;
	}

}
