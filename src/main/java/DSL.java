
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void escreve( String id_campo, String texto) {
		driver.findElement(By.id(id_campo)).sendKeys(texto);
		
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadio(By by) {
		driver.findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public boolean verificarRadio(String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);		
	}
	
	public String verificarCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();		
	}
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	public void alertaObterTextoEAceita(String id) {
		Alert alert=driver.switchTo().alert();
		driver.findElement(By.id(id)).getText();		
	}
	
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
		
	}
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTab) {
		//procurar coluna do registro 
		WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro 
		int idLinha = obterIndiceLinhas(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula= tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinhas(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas=tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha=-1;
		for(int i=0; i<linhas.size();i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha=i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas =tabela.findElements(By.xpath(".//th"));
		int idColuna=-1;
		for(int i=0; i<colunas.size();i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna=i+1;
				break;
			}
		}
		return idColuna;
	}
}
