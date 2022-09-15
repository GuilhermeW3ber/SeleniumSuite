import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	
	@After
	public void encerramento(){
		try{
		    driver.close();
		   }catch (Exception e){
		      System.out.println("Nothing to do with it");
		      }	
	}
	@Test
	public void testeAjax() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=66413");
		dsl= new DSL(driver);
		page=new CampoDeTreinamentoPage(driver);
		dsl.escreve("j_idt311:name","Teste");
		dsl.clicarBotao("j_idt311:j_idt315");
		WebDriverWait wait=new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt311:display")));
		wait.until(ExpectedConditions.textToBe(By.id("j_idt311:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt311:display"));
	}
}
