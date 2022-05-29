package br.com.gabriel.leilao.login;

import br.com.gabriel.leilao.lance.LancesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fechar();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuarLogin();

        String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
        assertEquals("fulano", nomeUsuarioLogado);
        assertFalse(paginaDeLogin.isPaginaAtual());
    }

    @Test
    void naoDeveriaEfetuarLoginComDadosInvalidos() {
        paginaDeLogin.preencherFormularioDeLogin("invalido", "1233");
        paginaDeLogin.efetuarLogin();

        assertNull(paginaDeLogin.getNomeUsuarioLogado());
        assertTrue(paginaDeLogin.isPaginaAtual());
        assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
    }

    @Test
    void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
        LancesPage paginaDeLances = new LancesPage();

        assertFalse(paginaDeLances.isPaginaAtual());
        assertFalse(paginaDeLances.isTituloLeilaoVisivel());

        paginaDeLances.fechar();
    }
}
