package br.com.gabriel.leilao.login;

import br.com.gabriel.leilao.lance.LancesPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		paginaDeLogin.efetuarLogin("fulano", "pass");

		String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
		Assertions.assertEquals("fulano", nomeUsuarioLogado);
		Assertions.assertFalse(paginaDeLogin.isPaginaAtual());
	}

	@Test
	void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.efetuarLogin("invalido", "1233");

		Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
		Assertions.assertTrue(paginaDeLogin.isPaginaAtual());
		Assertions.assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
	}

	@Test
	void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		LancesPage paginaDeLances = new LancesPage();

		Assertions.assertFalse(paginaDeLances.isPaginaAtual());
		Assertions.assertFalse(paginaDeLances.isTituloLeilaoVisivel());

		paginaDeLances.fechar();
	}

}
