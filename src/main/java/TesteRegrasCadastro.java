import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteRegrasCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
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
	
	@Parameters
	public static Collection<Object[]>getCollection(){
		return Arrays.asList(new Object[][] {
			{"","","", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Wagner","","", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Wagner","Castro","", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Wagner","Castro","Masculino", Arrays.asList("Carne","Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Wagner","Castro","Masculino", Arrays.asList("Carne"), new String[]{"Karate","O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Vagetariano")) page.setComidaVegetariano();
		page.setEsporte("esportes");
		page.cadastrar();
		//Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	}
	
}
