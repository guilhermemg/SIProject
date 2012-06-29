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
import estradasolidaria.ui.server.logic.Solicitacao;

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

	public void addElements() throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
		for(int i = 1; i < 6; i++) {
			sistema.criarUsuario("l"+i, "s"+i, "n"+i, "e"+i, "em"+i);
		}
		
		idSessao1 = sistema.abrirSessao("l1", "s1").getIdSessao();
		idSessao2 = sistema.abrirSessao("l2", "s2").getIdSessao();
		idSessao3 = sistema.abrirSessao("l3", "s3").getIdSessao();
		idSessao4 = sistema.abrirSessao("l4", "s4").getIdSessao();
		idSessao5 = sistema.abrirSessao("l5", "s5").getIdSessao();
		
		cadastraCaronas();
		try {
			solicitacaoDeVagas();
			aceitacaoDeVagas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sistema.criarUsuario("si1", "si1si1", "nn", "ee", "emem");
		encerrarSessoes();
	}

	private void encerrarSessoes() {
		sistema.encerrarSessao("l1");
		sistema.encerrarSessao("l2");
		sistema.encerrarSessao("l3");
		sistema.encerrarSessao("l4");
		sistema.encerrarSessao("l5");
	}

	private void aceitacaoDeVagas() throws IllegalArgumentException, CaronaInexistenteException, EstadoSolicitacaoException {
		Collection<Solicitacao> solicitacoesFeitasPorU2 = sistema.getUsuarioAPartirDeIDSessao(idSessao2).getMapIdSolicitacoesFeitas().values();
		Iterator<Solicitacao> it = solicitacoesFeitasPorU2.iterator();
		sistema.aceitarSolicitacao(idSessao1, it.next().getIdSolicitacao());
		
		Collection<Solicitacao> solicitacoesFeitasPorU5 = sistema.getUsuarioAPartirDeIDSessao(idSessao5).getMapIdSolicitacoesFeitas().values();
		it = solicitacoesFeitasPorU5.iterator();
		Solicitacao s1 = it.next();
		sistema.aceitarSolicitacao(idSessao4, s1.getIdSolicitacao());
	}

	private void solicitacaoDeVagas() throws CaronaInvalidaException, CaronaInexistenteException, CadastroEmCaronaPreferencialException {
		sistema.solicitarVaga(idSessao2, sistema.getCaronaUsuario(idSessao1, 1).getIdCarona());
		sistema.solicitarVagaPontoEncontro(idSessao3, (sistema.getCaronaUsuario(idSessao2, 1).getIdCarona()), "ponto1");
		sistema.solicitarVagaPontoEncontro(idSessao4, (sistema.getCaronaUsuario(idSessao5, 2).getIdCarona()), "ponto2");
		sistema.solicitarVaga(idSessao5, sistema.getCaronaUsuario(idSessao4, 1).getIdCarona());
	}

	private void cadastraCaronas() throws MessagingException, CaronaInvalidaException, EstadoCaronaException {
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
