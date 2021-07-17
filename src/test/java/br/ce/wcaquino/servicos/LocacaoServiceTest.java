package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquinho.exceptions.FilmesSemEstoqueException;
import br.ce.wcaquinho.exceptions.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	private LocacaoService service;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException expect = ExpectedException.none();

	@Before
	public void setup() {
		service = new LocacaoService();
	}

	@Test
	public void testeLocacao() throws Exception {
		// cenario
		Usuario usuario = new Usuario("User 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		// acao
		Locacao locacao = service.alugarFilme(usuario, filmes);

		// verificação
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));
	}

	@Test(expected = FilmesSemEstoqueException.class) // maneira elegante onde se declara que a exceção é espera no
														// @Test(...)
	public void testeLocacao_FilmeSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("User 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		// acao
		service.alugarFilme(usuario, filmes);
	}

	@Test
	public void testLocacao_UsuarioVazio() throws FilmesSemEstoqueException {
		// cenario
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		try {
			// acao
			service.alugarFilme(null, filmes);
			// verificação
			Assert.fail();
		} catch (LocadoraException e) {
			// verificação
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}

	}

	@Test
	public void testLocacao_FilmeVazio() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("User 1");

		// verificação
		expect.expect(LocadoraException.class);
		expect.expectMessage("Filme vazio");

		// acao
		service.alugarFilme(usuario, null);
	}
}
