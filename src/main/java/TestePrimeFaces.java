import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrimeFaces {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=3ab5b");
		driver.manage().window().setSize(new Dimension(1200,765));
		dsl = new DSL(driver);
		page=new CampoDeTreinamentoPage(driver);
	}
	
	@After
	public void encerramento(){
		try{
		    driver.close();
		   }catch (Exception e){
		      System.out.println("Nothing to do with it");
		      }	
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		dsl.clicarRadio(By.xpath("//input[@id='j_idt312:console:0']/../..//span"));
		Assert.assertTrue(dsl.verificarRadio("j_idt312:console:0"));
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.verificarRadio("j_idt312:console:1"));
		
	}
	
	@Test
	public void deveInteragirComSelectPrime() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=53d86");
		dsl.clicarRadio(By.xpath("//*[@id='_idt311:option_input']/../../span"));
		dsl.clicarRadio(By.xpath("//*[@id=\"j_idt311:option_1\"]"));
		
		//*[@id="j_idt311:option_1"]
	}
}
