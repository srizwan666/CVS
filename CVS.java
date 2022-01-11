package cvs;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CVS {
	
	static WebDriver driver;
	
	@BeforeMethod
	public void openBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rizwan\\git\\practice\\rizwan\\src\\main\\java\\rizwan\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}

	@Test
	public void serachBox() {
		
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("CVS");
		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		List<WebElement> searchResults= driver.findElements(By.xpath("//ul[@role='listbox']/li/descendant::div[@class='pcTkSc']"));
		
		
		System.out.println(searchResults.size());

		for(int i = 0; i < searchResults.size(); i++) {
			String searchIteams = searchResults.get(i).getText();
			if(searchIteams.contains("CVS")) {
				searchResults.get(i).click();
				break;
			}}
	
		driver.findElement(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']")).click();
		driver.findElement(By.xpath("//body/app-root[1]/app-homepage[1]/main[1]/cvs-actionbar[1]/section[1]/div[1]/div[1]/ul[1]/li[4]/a[1]")).click();
		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/main[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/nav[1]/div[1]/ul[1]/li[4]")).click();
		
	}
	
	@Test
	public void linksCount() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int linksCount = links.size();

		System.out.println("Total number of links on this page is " + linksCount);

		for(int i = 1; i < linksCount; i++) {
			WebElement storage = links.get(i);
			String linksAttribute = storage.getAttribute("href");
			System.out.println(linksAttribute);
			verifyLinkActive(linksAttribute);
		}
	}

	
	
	
	public static void verifyLinkActive(String linksAtt) {
		try {
			URL linksAttribute = new URL(linksAtt);
			HttpURLConnection httpURLConnect = (HttpURLConnection)linksAttribute.openConnection();
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.connect();

			if(httpURLConnect.getResponseCode() == 200) {
				System.out.println(linksAtt + " - " + httpURLConnect.getResponseMessage());
			}

			if(httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linksAtt + " - " + httpURLConnect.getResponseCode() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
			}
		} 
		catch(Exception e) {

		}

	}
	
	
	@AfterMethod
	public void browserClose() {
		driver.close();
	}










}
	
	
		
		
		
	
	
	











