package pageObject;

import org.openqa.selenium.*;

public class AddProductsPage {
	By email1 = By.xpath("//*[@id='email']");
	By pass = By.xpath("//*[@type='password']");
	By submit = By.xpath("//*[@id='SubmitLogin']");

	public void enterEmail(WebDriver driver, String emailVal) {
		driver.findElement(email1).sendKeys(emailVal);

	}

	public void entepass(WebDriver driver, String password) {
		driver.findElement(pass).sendKeys(password);
		driver.findElement(pass).click();
	}

	public void clicksubmit(WebDriver driver) {
		driver.findElement(submit).click();
	}
}
