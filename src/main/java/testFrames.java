import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testFrames {
	
	//private WebDriver driver;
	private DSL dsl;
	
	@Before
	@Ignore
	public void inicializar() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void deveInteragirComframesEscondidos() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		
		driver.switchTo().frame("frame2");
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, argument[0])",frame.getLocation().y);
		driver.findElement(By.id("frameButton")).click();
		Alert alert=driver.switchTo().alert();
		String msg= alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
	}

	@Test
	public void DeveInteragirComFrames() {
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	driver.manage().window().setSize(new Dimension(1200,765));
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	driver.switchTo().frame("frame1");
	driver.findElement(By.id("frameButton")).click();
	Alert alert=driver.switchTo().alert();
	String msg= alert.getText();
	Assert.assertEquals("Frame OK!", msg);
	alert.accept();
	
	driver.switchTo().defaultContent();
	driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void DeveInteragirComjanelas() {
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	driver.manage().window().setSize(new Dimension(1200,765));
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	driver.findElement(By.id("buttonPopUpEasy")).click();
	driver.switchTo().window("Popup");
	driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
	driver.switchTo().window("");
	driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	
	
	}
	
	@Test
	public void DeveInteragirComjanelasSemTítulo() {
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	driver.manage().window().setSize(new Dimension(1200,765));
	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
	driver.findElement(By.id("buttonPopUpEasy")).click();
	System.out.println(driver.getWindowHandle());
	System.out.println(driver.getWindowHandles());
	driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
	driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
	driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
	driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	
	}
}
