package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Productspage {
	By Tshirts = By.xpath("//div/ul/li/a[@title='T-shirts']");
	By Size = By.xpath("//input[starts-with(@id,'layered_id_attribute_group') and @value='1_1']");
	By Compositions = By.xpath("//input[@id='layered_id_feature_5']");
	By Style = By.xpath("//input[@id='layered_id_feature_11']");
	By Properties = By.xpath("//input[@id='layered_id_feature_17']");
	By Availability = By.xpath("//input[@id='layered_quantity_1']");
	By Manufacturer = By.xpath("//input[@id='layered_manufacturer_1']");
	By AddToCart = By.xpath("//span[contains(text(),'Add to cart')]");

	public void click_t_shirts(WebDriver driver) {
		driver.findElement(Tshirts).click();
	}

	public void click_Size(WebDriver driver) {
		driver.findElement(Size).click();
	}

	public void click_Compositions(WebDriver driver) {
		driver.findElement(Compositions).click();
	}

	public void click_Style(WebDriver driver) {
		driver.findElement(Style).click();
	}

	public void click_Properties(WebDriver driver) {
		driver.findElement(Properties).click();
	}

	public void click_Availability(WebDriver driver) {
		driver.findElement(Availability).click();
	}

	public void click_Manufacturer(WebDriver driver) {
		driver.findElement(Manufacturer).click();
	}

	public void click_AddToCart(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")));
	}

}