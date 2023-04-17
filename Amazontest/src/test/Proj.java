package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class Proj {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		String ParentWin = driver.getWindowHandle();
		WebElement SearchInput = driver.findElement(By.id("twotabsearchtextbox"));
		SearchInput.sendKeys("Samsung");
		WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
		SearchButton.click();
		
		List<WebElement> ProductDesc = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		System.out.println("Total num of links are " + ProductDesc.size());
		List<WebElement> ProductPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		for(int index=0;index<ProductDesc.size();index++) {
			System.out.println(ProductDesc.get(index).getText() + "Price : Rupees ");
			System.out.println(ProductPrice.get(index).getText());
		}
		
		ProductDesc.get(0).click();
		String toValidate = ProductDesc.get(0).getText();
		System.out.println(toValidate);
		Set<String> allWinMan = driver.getWindowHandles();
		System.out.println("Before clicking tab button the win is " + ParentWin);
		
		for(String win:allWinMan) {
			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}
		WebElement HeadingOnNewTab = driver.findElement(By.xpath("//div[@id='title_feature_div']//span"));
		String headerString = HeadingOnNewTab.getText();
		System.out.println(headerString);
		
		if(headerString.equals(toValidate)) {
			System.out.println("TC Passed");
		}else {
			System.out.println("TC Failed");
		}
	}

}

