import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testeCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\broco\\Desktop\\DriversGoogleGecko\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
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
	public void DeveInteragirComAlertSimples() {
		page.setNome("Wagner");
		page.setSobrenome("Castro");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		
		Assert.assertEquals( "Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals( "Wagner", page.obterResultadoNome());
		Assert.assertEquals("Castro", page.obterResultadoSobrenome());
		//Assert.assertTrue( dsl.obterTexto("descSobrenome").endsWith("Castro"));
		Assert.assertEquals("Masculino", page.obterSexoCadastrado());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastrada());
		Assert.assertEquals("Natacao", page.obterEsporteCadastrado());
	
	}
}
