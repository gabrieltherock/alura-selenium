package br.com.gabriel.leilao.login;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {

    private static WebDriver browser;

    @BeforeAll
    static void inicializaDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        browser = new ChromeDriver();
    }

    @AfterAll
    static void fechaBrowser() {
        browser.quit();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos() {
        realizaLogin("fulano", "pass");

        assertNotEquals("http://localhost:8080/login", browser.getCurrentUrl());
        assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    void loginInvalido() {
        realizaLogin("errado", "errado");

        assertEquals("http://localhost:8080/login?error", browser.getCurrentUrl());
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    private void realizaLogin(String usuario, String senha) {
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys(usuario);
        browser.findElement(By.id("password")).sendKeys(senha);
        browser.findElement(By.id("login-form")).submit();
    }
}
