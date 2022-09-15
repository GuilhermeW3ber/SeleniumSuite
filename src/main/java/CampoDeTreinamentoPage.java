import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoDeTreinamentoPage {
	
	private DSL dsl; 
	
	public CampoDeTreinamentoPage(WebDriver driver) {
		dsl=new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);	
	}
	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);	
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");	
	}
	
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");	
	}
	
	public void setComidaCarne() {
		dsl.clicarRadio("elementosForm:comidaFavorita:0");	
	}
	public void setComidaFrango() {
		dsl.clicarRadio("elementosForm:comidaFavorita:1");	
	}
	
	public void setComidaPizza() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");	
	}
	
	public String setComidaVegetariano() {
		return dsl.obterTexto("elementosForm:comidaFavorita:3");
	}
	
	
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);	
	}
	
	public void setEsporte(String valor) {
		dsl.selecionarCombo("elementosForm:esportes", valor);	
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto(By.xpath(".//*[@id='resultado']/span"));
	}
	
	public String obterResultadoNome() {
		return dsl.obterTexto(By.xpath(".//*[@id='descNome']/span"));
	}
	
	public String obterResultadoSobrenome() {
		return dsl.obterTexto(By.xpath(".//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastrado() {
		return dsl.obterTexto(By.xpath(".//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastrada() {
		return dsl.obterTexto(By.xpath(".//*[@id='descComida']/span"));
	}
	
	public String obterEsporteCadastrado() {
		return dsl.obterTexto(By.xpath(".//*[@id='descEsportes']/span"));
	}
	
	public String obterEscolaridadeCadastrada() {
		return dsl.obterTexto(By.xpath(".//*[@id='descEscolaridade']/span"));
	}
	
}

