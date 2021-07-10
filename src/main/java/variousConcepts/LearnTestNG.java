package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;
	String browser;
	String url;

	@BeforeClass
	public void readConfig() {

		Properties prop = new Properties();

		try {
			// InputStream //BufferedReader //FileReader //Scanner
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser);
			url = prop.getProperty("url");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	// Element Liberary
	By USERNAME_ELEMENT = By.xpath("//input[@id='username']");
	By PASSWORD_ELEMENT = By.xpath("//input[@id='password']");
	By SIGNIN_ELEMENT = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By DASHBOARD_FIELD_LOCATOR = By.xpath("//h2[contains(text(), ' Dashboard ')]");
	By CUSOMER_ELEMENT = By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]");
	By ADD_CUSOMER_ELEMENT = By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a");
	By FULL_NAME_ELEMENT = By.xpath("//*[@id=\"account\"]");
	By COMPANY_DROPDOWN_ELEMENT = By.xpath("//select[@id='cid']");
	By EMAIL_ELEMENT = By.xpath("//*[@id=\"email\"]");
	By COUNTRY_DROPDOWN_ELEMENT = By.xpath("//*[@id=\"country\"]");

	// Login Data
	String userName = "demo@techfios.com";
	String password = "abc123";

	// Test data or Mock data
	String fullName = "Test Feb";
	String companyName = "Google";
	String eamil = "techfios@gmail.com";
	String phone = "21435523";
	String countryName = "Albania";
	

	// @Test(priority = 2)
	public void loginTest() {

		driver.findElement(USERNAME_ELEMENT).sendKeys(userName);
		driver.findElement(PASSWORD_ELEMENT).sendKeys(password);
		driver.findElement(SIGNIN_ELEMENT).click();

		Assert.assertEquals(driver.findElement(DASHBOARD_FIELD_LOCATOR).getText(), "Dashboard",
				"Dashboard page not found!!");
	}

	// @Test(priority = 1)
	public void negetiveLoginTest() {

		driver.findElement(USERNAME_ELEMENT).sendKeys(userName);
		driver.findElement(PASSWORD_ELEMENT).sendKeys(password);
		driver.findElement(SIGNIN_ELEMENT).click();

	}

	@Test
	public void addCustomer() {

		driver.findElement(USERNAME_ELEMENT).sendKeys(userName);
		driver.findElement(PASSWORD_ELEMENT).sendKeys(password);
		driver.findElement(SIGNIN_ELEMENT).click();

		Assert.assertEquals(driver.findElement(DASHBOARD_FIELD_LOCATOR).getText(), "Dashboard",
				"Dashboard page not found!!");

		driver.findElement(CUSOMER_ELEMENT).click();
		driver.findElement(ADD_CUSOMER_ELEMENT).click();
		
		
		
		
		
		driver.findElement(FULL_NAME_ELEMENT).sendKeys(fullName + randomGenerator(999));
		
		driver.findElement(EMAIL_ELEMENT).sendKeys(randomGenerator(999) + eamil);
		
		selectFromDrowdown(driver.findElement(COMPANY_DROPDOWN_ELEMENT), companyName);
		
		
		
		
		selectFromDrowdown(driver.findElement(COUNTRY_DROPDOWN_ELEMENT), countryName);

	}

	

	private int randomGenerator(int boundaryNumber) {
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(boundaryNumber);
		return randomNumber;		
	}

	private void selectFromDrowdown(WebElement element, String visibleTest) {
		
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleTest);
		
	}

	// @AfterMethod
	public void tearDown() {
		// closes the window
		driver.close();
		// kills the process we started
		driver.quit();
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("After Class");
	}

}
