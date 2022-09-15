import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteSincronismo {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	@Before
	public void inicializar() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl= new DSL(driver);
		page=new CampoDeTreinamentoPage(driver);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@After
	public void encerramento(){
		try{
			//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		    driver.close();
		   }catch (Exception e){
		      System.out.println("Nothing to do with it");
		      }	
	}
	
	@Test
	public void deveInteragirComRespostaDemoradaEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "Deu certo?");
		
	}
	
	@Test
	public void deveInteragirComRespostaDemoradaEsperaImplicita() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl= new DSL(driver);
		page=new CampoDeTreinamentoPage(driver);
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Deu certo?");
	}
		
	
}
