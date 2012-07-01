package estradasolidaria.unittest;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;

import estradasolidaria.ui.server.logic.CadastroEmCaronaPreferencialException;
import estradasolidaria.ui.server.logic.CaronaInexistenteException;
import estradasolidaria.ui.server.logic.CaronaInvalidaException;
import estradasolidaria.ui.server.logic.EstadoCaronaException;
import estradasolidaria.ui.server.logic.EstadoSolicitacaoException;
import estradasolidaria.ui.server.logic.EstradaSolidariaController;
import estradasolidaria.ui.server.logic.Sessao;
import estradasolidaria.ui.server.logic.Usuario;
import estradasolidaria.ui.server.util.SpecialLinkedListBrackets;

public class RankingUsuariosTest {
	EstradaSolidariaController controller = EstradaSolidariaController
			.getInstance();
	private Integer idSessaoMark;
	private Integer idSessaoBill;
	private Integer idSessaoVader;
	private Integer idSessaoAnakin;

	private Integer idCarona1;
	
	@Before
	public void criaUsuarios() {
		controller.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, Caifornia", "mark@facebook.com");
		controller.criarUsuario("bill", "bilz@o", "William Henry Gates III",
				"Medina, Washington", "billzin@gmail.com");
		controller.criarUsuario("vader", "d4rth", "Anakin Skywalker",
				"Death Star I", "darthvader@empire.com");
		controller.criarUsuario("anakin", "d4rth", "Anakin Skywalker",
				"Dark Side", "anakin@darkside.com");

		idSessaoMark = controller.abrirSessao("mark", "m@rk").getIdSessao();
		idSessaoBill = controller.abrirSessao("bill", "bilz@o").getIdSessao();
		idSessaoVader = controller.abrirSessao("vader", "d4rth").getIdSessao();
		idSessaoAnakin = controller.abrirSessao("anakin", "d4rth").getIdSessao();
	}

	@Test
	public void getRanking1() throws MessagingException, CaronaInvalidaException, EstadoCaronaException, IllegalArgumentException, CadastroEmCaronaPreferencialException, CaronaInexistenteException, EstadoSolicitacaoException {
		List<Usuario> listaUsuarios1 = controller.getRankingUsuarios("crescente");
		List<Integer> listaIdsSessoes1 = getListaIdsSessoes(listaUsuarios1);
		List<Integer> listaDeProva1 = new SpecialLinkedListBrackets<Integer>();
		listaDeProva1.add(idSessaoMark);
		listaDeProva1.add(idSessaoAnakin);
		listaDeProva1.add(idSessaoVader);
		listaDeProva1.add(idSessaoBill);
		
		assertEquals(listaDeProva1.toString(), listaIdsSessoes1.toString());
		
		//----------------------------------------------------------------------------
		
		idCarona1 = controller.cadastrarCarona(idSessaoMark, "Campina Grande", "João Pessoa", "20/06/2013", "12:00", 3).getIdCarona();
		
		Integer idSolicitacao1 = controller.solicitarVaga(idSessaoBill, idCarona1).getIdSolicitacao();
		controller.aceitarSolicitacao(idSessaoMark, idSolicitacao1);
		Integer idSolicitacao2 = controller.solicitarVaga(idSessaoVader, idCarona1).getIdSolicitacao();
		controller.aceitarSolicitacao(idSessaoMark, idSolicitacao2);
		Integer idSolicitacao3 = controller.solicitarVaga(idSessaoAnakin, idCarona1).getIdSolicitacao();
		controller.aceitarSolicitacao(idSessaoMark, idSolicitacao3);

		controller.reviewCarona(idSessaoBill, idCarona1, "segura e tranquila");
		controller.reviewCarona(idSessaoVader, idCarona1, "não funcionou");
		controller.reviewCarona(idSessaoAnakin, idCarona1, "segura e tranquila");
		
		List<Usuario> listaUsuarios2 = controller.getRankingUsuarios("crescente");
		List<Integer> listaIdsSessoes2 = getListaIdsSessoes(listaUsuarios2);
		List<Integer> listaDeProva2 = new SpecialLinkedListBrackets<Integer>();
		listaDeProva2.add(idSessaoAnakin);
		listaDeProva2.add(idSessaoVader);
		listaDeProva2.add(idSessaoBill);
		listaDeProva2.add(idSessaoMark);
		
		assertEquals(listaDeProva2.toString(), listaIdsSessoes2.toString());
	}
	
	private List<Integer> getListaIdsSessoes(List<Usuario> listaUsuarios) {
		List<Integer> listaIdsSessoesDeUsuarios = new SpecialLinkedListBrackets<Integer>();
		Iterator<Usuario> itUsuarios = listaUsuarios.iterator();
		while (itUsuarios.hasNext()) {
			Usuario u = itUsuarios.next();
			Iterator<Sessao> idSessao = controller.getMapIdSessao().values()
					.iterator();
			while (idSessao.hasNext()) {
				Sessao s = idSessao.next();
				if (s.getIdUser().equals(u.getIdUsuario())) {
					listaIdsSessoesDeUsuarios.add(s.getIdSessao());
				}
			}
		}
		return listaIdsSessoesDeUsuarios;
	}

}
