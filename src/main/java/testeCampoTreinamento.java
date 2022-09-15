import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class testeCampoTreinamento {
	
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
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "teste de escrita");
		Assert.assertEquals("teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
		
	}
	
	@Test 
	public void DeveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));	
	}
	
	@Test 
	public void DeveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.verificarRadio("elementosForm:sexo:0"));
		
	}
	
	@Test 
	public void DeveInteragirComCheckBox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.verificarRadio("elementosForm:comidaFavorita:2"));
		
	}
	
	@Test
	public void DeveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.obterValorCampo("elementosForm:escolaridade"));
	}
	
	@Test
	public void DeveVerificarValoresComo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(3);
		//combo.selectByValue("superior");
		List<WebElement> options=combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou=false;;
		for(WebElement option: options){
			if(option.getText().equals("Mestrado")) {
				encontrou=true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void DeveVerificarValoresComoMult() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		
		List<WebElement> allSelectedOptions=combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions=combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("elementosForm:buttonSimple");
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
			
	}
	
	@Test
	//@Ignore
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		//Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		//Assert.fail();
	}
	
	@Test
	//@Ignore
	public void deveBuscarTextosNaPagina() {
		//System.out.println(driver.findElement(By.tagName("body")).getText());
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
		
	}
	
	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
	
}


