package estradasolidaria.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import estradasolidaria.ui.server.logic.*;


//Tests for constructor and getAtributo method.

public class SolicitacaoTest {

	Usuario solicitante;
	Usuario donoDaCarona;
	Solicitacao solicitacaoTeste;
	Carona carona;
	
	@Before
	public void criaUsuarios() throws Exception {
		solicitante = new Usuario("italomgx", "1234", "Italo Guedes", "Rua Hell", "italo@insano.com");
		donoDaCarona = new Usuario("lemmy", "gonnaliveforever", "Lemmy Kilmister", "Overkill Street", "lemmy@god.com");
		carona = new Carona(donoDaCarona.getIdUsuario(), "Campina Grande", "João Pessoa", "02/02/2013", "15:00", 2, 1);
	}
	
	@Test
	public void origemTest() throws Exception {
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "", "Joao Pessoa", donoDaCarona, solicitante, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Origem invalida");
		}
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), null, "Joao Pessoa", donoDaCarona, solicitante, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Origem invalida");
		}
	}
	
	@Test
	public void destinoTest() throws Exception {
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "", donoDaCarona, solicitante, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Destino invalido");
		}
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", null, donoDaCarona, solicitante, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Destino invalido");
		}
	}
	
	@Test
	public void idCaroneiroTest() throws Exception {	
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "Joao Pessoa", null, solicitante, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "idUsuario invalido");
		}
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "Joao Pessoa", null, solicitante, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "idUsuario invalido");
		}
	}
	
	@Test
	public void idSolicitanteTest() throws Exception {
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "Joao Pessoa", donoDaCarona, null, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "idUsuario invalido");
		}
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "Joao Pessoa", donoDaCarona, null, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "idUsuario invalido");
		}
	}
	
	@Test
	public void pontoDeEncontroTest() throws Exception {	
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "Joao Pessoa", donoDaCarona, solicitante, new Sugestao("", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Ponto de encontro invalido");
		}	
		try{
			solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "Joao Pessoa", donoDaCarona, solicitante, null);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Ponto de encontro invalido");
		}	
	}
	
	@Test
	public void getAtributoTest() throws Exception{		
		solicitacaoTeste = new Solicitacao(carona.getIdCarona(), "Campina Grande", "Joao Pessoa", donoDaCarona, solicitante, new Sugestao("Pub 10", carona), EnumTipoSolicitacao.SOLICITACAO_COM_PONTO_ENCONTRO);
		assertEquals("Campina Grande", solicitacaoTeste.getOrigem());
		assertEquals("Joao Pessoa", solicitacaoTeste.getDestino());
		assertEquals(donoDaCarona, solicitacaoTeste.getDonoDaCarona());
		assertEquals(solicitante, solicitacaoTeste.getDonoDaSolicitacao());
		assertEquals("Pub 10", solicitacaoTeste.getPontoEncontro());
	}
}
