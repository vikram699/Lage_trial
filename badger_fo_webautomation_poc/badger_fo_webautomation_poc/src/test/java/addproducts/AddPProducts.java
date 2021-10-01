package addproducts;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import config.Constants;
import config.DataUtils;
import pageObject.AddProductsPage;
import pageObject.FODashBoardPage;
import pageObject.Productspage;
import pageObject.SalesPage;
import utility.DriverUtiltiy;
import utility.ExcelReader;

public class AddPProducts extends BaseClass {
	DriverUtiltiy driverutil;
	WebDriver driver;
	AddProductsPage addprod;
	Productspage prod;

	public AddPProducts() {
		driverutil = new DriverUtiltiy();
		addprod = new AddProductsPage();
		prod = new Productspage();
	}

	@BeforeTest()
	public void setup() throws IOException {
		driver = driverutil.getdriver("chrome");

	}

	@Test()
	public void addProducts() throws Exception {
		Thread.sleep(4000);
		addprod.enterEmail(driver,"vickyreddy0519@gmail.com");
		addprod.entepass(driver,"vicky123");
		addprod.clicksubmit(driver);
		prod.click_t_shirts(driver);
		prod.click_Size(driver);
		prod.click_Compositions(driver);
		prod.click_Style(driver);
		prod.click_Properties(driver);
		prod.click_Availability(driver);
		prod.click_Manufacturer(driver);
		prod.click_AddToCart(driver);
	}

	@AfterTest()
	public void tearDown() throws IOException {
		driver.quit();

	}

}
