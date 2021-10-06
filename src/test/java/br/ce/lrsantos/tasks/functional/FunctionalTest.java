package br.ce.lrsantos.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FunctionalTest {
	
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefa() {
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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
	
	
}
