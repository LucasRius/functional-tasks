package br.ce.lrsantos.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FunctionalTest {
	
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.15:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.15:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefa() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		//Clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/9999");
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		//Clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descrição
		//driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/9999");
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		//Clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		//escrever data
		//driver.findElement(By.id("dueDate")).sendKeys("10/10/9999");
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		//Clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/1999");
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void deveRemoverTarefa() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		//salvando tarefa
		//Clicar em add todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium 2");
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/9999");
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", mensagem);
		
		//removendo tarefa
		driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
		Assert.assertEquals("Success!", mensagem);
		} finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	
}
