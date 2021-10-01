package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.openqa.*;

public class Waits {

	public static void waitforelement(WebDriver driver, By val, int time) {
		WebDriverWait wb=new WebDriverWait(driver, time);
		wb.until(ExpectedConditions.elementToBeClickable(val));
	}
}
