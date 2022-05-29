package br.com.gabriel.leilao.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LoginTest {

    private WebDriver browser;

    @BeforeEach
    public void inicializaDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        browser = new ChromeDriver();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos() {
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        assertNotEquals("http://localhost:8080/login", browser.getCurrentUrl());
        assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

        browser.quit();
    }
}
