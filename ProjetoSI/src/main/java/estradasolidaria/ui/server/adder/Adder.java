package estradasolidaria.ui.server.adder;

import java.util.Collection;
import java.util.Iterator;

import javax.mail.MessagingException;

import estradasolidaria.ui.server.logic.CadastroEmCaronaPreferencialException;
import estradasolidaria.ui.server.logic.CaronaInexistenteException;
import estradasolidaria.ui.server.logic.CaronaInvalidaException;
import estradasolidaria.ui.server.logic.EstadoCaronaException;
import estradasolidaria.ui.server.logic.EstadoSolicitacaoException;
import estradasolidaria.ui.server.logic.EstradaSolidariaController;
import estradasolidaria.ui.server.logic.MessageException;
import estradasolidaria.ui.server.logic.Solicitacao;

/**
 * Classe responsavel por popular
 * o sistema, baseado em uma serie de casos
 * de testes previamente estudados.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 * obs.: lembre-se aqui que o index da carona equivale a
 * sua ordem de insercao (que começa com 0) somada com 1.
 *
 */
public class Adder {
	private EstradaSolidariaController sistema;
	Integer idSessao1; 
	Integer idSessao2; 
	Integer idSessao3;
	Integer idSessao4;
	Integer idSessao5; 
	
	public Adder(EstradaSolidariaController uniqueInstance) {
		this.sistema = uniqueInstance;
	}

	public void addElements() throws MessagingException, CaronaInvalidaException, EstadoCaronaException, MessageException {
		for(int i = 1; i < 6; i++) {
			try {
				sistema.criarUsuario("l"+i, "s"+i, "n"+i, "e"+i, "em"+i);
			} catch (MessageException e) {
				e.printStackTrace();
			}
		}
		
		idSessao1 = sistema.abrirSessao("l1", "s1").getIdSessao();
		idSessao2 = sistema.abrirSessao("l2", "s2").getIdSessao();
		idSessao3 = sistema.abrirSessao("l3", "s3").getIdSessao();
		idSessao4 = sistema.abrirSessao("l4", "s4").getIdSessao();
		idSessao5 = sistema.abrirSessao("l5", "s5").getIdSessao();
		
		try {
			cadastraInteresses();
			cadastraCaronas();
			cadastraCaronasDeTiposVariados();
			try {
				solicitacaoDeVagas();
				aceitacaoDeVagas();
			} catch (Exception e) {
			}
			adicionaSugestoes();
			encerrarSessoes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			sistema.criarUsuario("si1", "si1si1", "nn", "ee", "emem");
		} catch (MessageException e) {
			e.printStackTrace();
		}
	}

	private void cadastraCaronasDeTiposVariados() throws MessagingException, CaronaInvalidaException, EstadoCaronaException, MessageException {
		sistema.cadastrarCarona(idSessao2, "a", "b", "12/12/2012", "12:12", 1);
		sistema.definirCaronaPreferencial(sistema.getCaronaUsuario(idSessao2, 2).getIdCarona());
		
		sistema.cadastrarCaronaMunicipal(idSessao2, "b", "a", "c2", "12/12/2012", "12:12", 2);
		
		sistema.cadastrarCaronaRelampago(idSessao3, "c", "d", "12/12/2012", "13/12/2012", "12:12", 3, 1);
		
		sistema.cadastrarCaronaMunicipal(idSessao4, "d", "c", "c4", "15/03/2015", "09:23", 4);
		sistema.cadastrarCaronaMunicipal(idSessao4, "d", "c", "c6", "12/12/2012", "12:12", 2);
		sistema.definirCaronaPreferencial(sistema.getCaronaUsuario(idSessao4, 3).getIdCarona());
		
		sistema.cadastrarCaronaRelampago(idSessao5, "e", "f", "15/08/2015", "18/08/2015", "14:13", 1, 1);
		sistema.definirCaronaPreferencial(sistema.getCaronaUsuario(idSessao5, 2).getIdCarona());
	}

	private void adicionaSugestoes() throws MessageException {
		sistema.sugerirPontoEncontro(idSessao2, sistema.getCaronaUsuario(idSessao1, 1).getIdCarona(), "ponto3");
		sistema.sugerirPontoEncontro(idSessao1, sistema.getCaronaUsuario(idSessao2, 1).getIdCarona(), "ponto4");
		sistema.sugerirPontoEncontro(idSessao3, sistema.getCaronaUsuario(idSessao4, 1).getIdCarona(), "ponto5");
		sistema.sugerirPontoEncontro(idSessao4, sistema.getCaronaUsuario(idSessao3, 1).getIdCarona(), "ponto6");
		sistema.sugerirPontoEncontro(idSessao5, sistema.getCaronaUsuario(idSessao1, 1).getIdCarona(), "ponto7");
	}

	private void cadastraInteresses() throws CaronaInvalidaException {
		sistema.cadastrarInteresse(idSessao1, "a", "b", "12/12/2012", "12:00", "");
		sistema.cadastrarInteresse(idSessao1, "c", "d", "12/12/2012", "12:00", "");
		
		sistema.cadastrarInteresse(idSessao2, "a", "b", "", "11:00", "18:00");
		sistema.cadastrarInteresse(idSessao2, "c", "d", "", "11:00", "18:00");
		
		sistema.cadastrarInteresse(idSessao3, "b", "a", "12/12/2012", "", "18:00");
		sistema.cadastrarInteresse(idSessao3, "b", "a", "", "", "");
		
		sistema.cadastrarInteresse(idSessao4, "e", "f", "", "", "18:00");
		sistema.cadastrarInteresse(idSessao4, "c", "d", "", "", "18:00");
	}

	private void encerrarSessoes() {
		sistema.encerrarSessao("l1");
		sistema.encerrarSessao("l2");
		sistema.encerrarSessao("l3");
		sistema.encerrarSessao("l4");
		sistema.encerrarSessao("l5");
	}

	private void aceitacaoDeVagas() throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException, MessageException {
		Collection<Solicitacao> solicitacoesFeitasPorU2 = sistema.getUsuarioAPartirDeIDSessao(idSessao2).getMapIdSolicitacoesFeitas().values();
		Iterator<Solicitacao> it = solicitacoesFeitasPorU2.iterator();
		sistema.aceitarSolicitacao(idSessao1, it.next().getIdSolicitacao());
		
		Collection<Solicitacao> solicitacoesFeitasPorU5 = sistema.getUsuarioAPartirDeIDSessao(idSessao5).getMapIdSolicitacoesFeitas().values();
		it = solicitacoesFeitasPorU5.iterator();
		Solicitacao s1 = it.next();
		sistema.aceitarSolicitacao(idSessao4, s1.getIdSolicitacao());
	}

	private void solicitacaoDeVagas() throws CaronaInvalidaException, CaronaInexistenteException, CadastroEmCaronaPreferencialException, IllegalArgumentException, MessageException {
		sistema.solicitarVaga(idSessao2, sistema.getCaronaUsuario(idSessao1, 1).getIdCarona());
		sistema.solicitarVagaPontoEncontro(idSessao3, (sistema.getCaronaUsuario(idSessao2, 1).getIdCarona()), "ponto1");
		sistema.solicitarVagaPontoEncontro(idSessao4, (sistema.getCaronaUsuario(idSessao5, 2).getIdCarona()), "ponto2");
		sistema.solicitarVaga(idSessao5, sistema.getCaronaUsuario(idSessao4, 1).getIdCarona());
	}

	private void cadastraCaronas() throws MessagingException, CaronaInvalidaException, EstadoCaronaException, MessageException {
		for(int j = 11; j < 50; j++) {
			sistema.cadastrarCarona(idSessao1, "o"+j, "d"+j, "12/12/2012", "12:12", j);
		}
		
		for(int j = 3; j < 5; j++) {
			sistema.cadastrarCarona(idSessao2, "o"+j, "d"+j, "12/12/2012", "12:12", j);
		}
		
		for(int j = 5; j < 7; j++) {
			sistema.cadastrarCarona(idSessao3, "o"+j, "d"+j, "12/12/2012", "12:12", j);
		}
		
		for(int j = 7; j < 9; j++) {
			sistema.cadastrarCarona(idSessao4, "o"+j, "d"+j, "12/12/2012", "12:12", j);
		}
		
		for(int j = 9; j < 11; j++) {
			sistema.cadastrarCarona(idSessao5, "o"+j, "d"+j, "12/12/2012", "12:12", j);
		}
	}
}

