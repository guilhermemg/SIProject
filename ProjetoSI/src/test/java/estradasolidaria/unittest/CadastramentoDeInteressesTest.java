package estradasolidaria.unittest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;

import estradasolidaria.ui.server.logic.CaronaInvalidaException;
import estradasolidaria.ui.server.logic.EstadoCaronaException;
import estradasolidaria.ui.server.logic.EstradaSolidariaController;
import estradasolidaria.ui.server.logic.MessageException;
import estradasolidaria.ui.server.logic.Usuario;
import estradasolidaria.ui.server.util.SpecialLinkedListBrackets;

public class CadastramentoDeInteressesTest {
	private EstradaSolidariaController controller = EstradaSolidariaController.getInstance();
	
	/*
	 * criarUsuario login="zezyt0" senha="z3z1t0" nome="Jose de zito" endereco="Rua belarmina pereira 452, João Pessoa" email="zezyto@gmail.com"
	 * criarUsuario login="manelito" senha="w4n3l1t0" nome="Manel da Silva" endereco="Rua adamastor pitaco 24, João Pessoa" email="manel@yahoo.com.br"
	 * criarUsuario login="jucaPeroba" senha="juqinha" nome="Juca Peroba" endereco="Rua 13 de maio, Caruaru" email="jucaPeroba@gmail.com"
	 * criarUsuario login="mariano0ab" senha="mariozinho" nome="Mariano Silva" endereco="Rua 27 de maio, Campina Grande" email="marianoAdvogado@gmail.com"
	 * criarUsuario login="caba" senha="Marcin" nome="Marcio Sarvai" endereco="Rua 21 de maio, Campina Grande" email="marcioSarvai@gmail.com"
	 */
	
	Usuario u1, u2, u3, u4, u5;
	
	Integer idSessaoZezito;
	Integer idSessaoManelito;
	Integer idSessaoJuca;
	Integer idSessaoMariano;
	Integer idSessaoMarcio;
	
	@Before
	public void setup() throws MessageException {
		controller.criarUsuario("zezyt0", "z3z1t0", "Jose de zito", "Rua belarmina pereira 452, João Pessoa", "zezyto@gmail.com");
		controller.criarUsuario("manelito", "w4n3l1t0", "Manel da Silva", "Rua adamastor pitaco 24, João Pessoa", "manel@yahoo.com.br");
		controller.criarUsuario("jucaPeroba", "juqinha", "Juca Peroba", "Rua 13 de maio, Caruaru", "jucaPeroba@gmail.com");
		controller.criarUsuario("mariano0ab", "mariozinho", "Mariano Silva", "Rua 27 de maio, Campina Grande", "marianoAdvogado@gmail.com");
		controller.criarUsuario("caba", "Marcin", "Marcio Sarvai", "Rua 21 de maio, Campina Grande", "marcioSarvai@gmail.com");
		
		idSessaoZezito = controller.abrirSessao("zezyt0", "z3z1t0").getIdSessao();
		idSessaoManelito = controller.abrirSessao("manelito", "w4n3l1t0").getIdSessao();
		idSessaoJuca = controller.abrirSessao("jucaPeroba", "juqinha").getIdSessao();
		idSessaoMariano = controller.abrirSessao("mariano0ab", "mariozinho").getIdSessao();
		idSessaoMarcio = controller.abrirSessao("caba", "Marcin").getIdSessao();
	}
	
	/*
	 * cadastrarInteresse idSessao=${sessaoZezito} origem="João Pessoa" destino="João Pessoa" data="23/06/2013" horaInicio="06:00" horaFim="16:00"
	 * cadastrarInteresse idSessao=${sessaoManelito} origem="Campina Grande" destino="João Pessoa" data="25/06/2013" horaInicio="11:00" horaFim="18:00"
	 * cadastrarInteresse idSessao=${sessaoMariano} origem="Campina Grande" destino="João Pessoa" data="23/06/2013" horaInicio="" horaFim="18:00"
	 * cadastrarInteresse idSessao=${sessaoMarcio} origem="Campina Grande" destino="João Pessoa" data="" horaInicio="" horaFim="18:00"
	 */
	/*
	 * cadastrarCarona idSessao=${sessaoJuca} origem="Campina Grande" destino="João Pessoa" data="23/06/2013" hora="16:00" vagas=3
	 * cadastrarCarona idSessao=${sessaoJuca} origem="João Pessoa" destino="Campina Grande" data="25/06/2013" hora="14:00" vagas=4
	 * cadastrarCarona idSessao=${sessaoMariano} origem="João Pessoa" destino="Campina Grande" data="25/06/2013" hora="15:00" vagas=1
	 */
	
	@Test
	public void cadastramentoDeInteresses() throws CaronaInvalidaException, MessagingException, EstadoCaronaException, MessageException {
		controller.cadastrarInteresse(idSessaoZezito, "João Pessoa", "Campina Grande", "23/06/2013", "06:00", "16:00");
		controller.cadastrarInteresse(idSessaoManelito, "Campina Grande", "João Pessoa", "25/06/2013", "11:00", "18:00");
		controller.cadastrarInteresse(idSessaoMariano, "Campina Grande", "João Pessoa", "23/06/2013", "", "18:00");
		controller.cadastrarInteresse(idSessaoMarcio, "Campina Grande", "João Pessoa", "", "", "18:00");
		
		controller.cadastrarCarona(idSessaoJuca, "Campina Grande", "João Pessoa", "23/06/2013", "16:00", 3);
		controller.cadastrarCarona(idSessaoJuca, "João Pessoa", "Campina Grande", "25/06/2013", "14:00", 4);
		controller.cadastrarCarona(idSessaoMariano, "João Pessoa", "Campina Grande", "25/06/2013", "15:00", 1);
		
		List<String> listaMensagens1 = new SpecialLinkedListBrackets<String>();
		List<String> listaMensagens2 = new SpecialLinkedListBrackets<String>();
		List<String> listaMensagens3 = new SpecialLinkedListBrackets<String>();
		List<String> listaMensagens4 = new SpecialLinkedListBrackets<String>();
		
		listaMensagens1.addAll(controller.verificarMensagensPerfil(idSessaoZezito));
		listaMensagens2.addAll(controller.verificarMensagensPerfil(idSessaoManelito));
		listaMensagens3.addAll(controller.verificarMensagensPerfil(idSessaoMariano));
		listaMensagens4.addAll(controller.verificarMensagensPerfil(idSessaoMarcio));
		
		assertEquals(listaMensagens1.toString(), "[]");
		assertEquals(listaMensagens2.toString(), "[]");
		assertEquals(listaMensagens3.toString(), "[Carona cadastrada no dia 23/06/2013, às 16:00 de acordo com os seus interesses registrados. Entrar em contato com jucaPeroba@gmail.com]");
		assertEquals(listaMensagens4.toString(), "[Carona cadastrada no dia 23/06/2013, às 16:00 de acordo com os seus interesses registrados. Entrar em contato com jucaPeroba@gmail.com]");
	}
	
	
	
	
}
