package estradasolidaria.ui.server;

import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import estradasolidaria.ui.client.EstradaSolidariaService;
import estradasolidaria.ui.client.GWTException;
import estradasolidaria.ui.server.logic.Carona;
import estradasolidaria.ui.server.logic.CaronaInexistenteException;
import estradasolidaria.ui.server.logic.CaronaInvalidaException;
import estradasolidaria.ui.server.logic.EstradaSolidariaController;
import estradasolidaria.ui.server.logic.Solicitacao;
import estradasolidaria.ui.server.logic.TrajetoInexistenteException;
import estradasolidaria.ui.server.logic.Usuario;
import estradasolidaria.ui.server.logic.UsuarioInexistenteException;

/**
 * The server side implementation of the RPC service.
 */
public class EstradaSolidariaServiceImpl extends RemoteServiceServlet implements
		EstradaSolidariaService {
	private static final long serialVersionUID = -1007968486871020509L;
	private EstradaSolidariaController controller = EstradaSolidariaController.getInstance();

	@Override
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws GWTException {
		try {
			controller.criarUsuario(login, senha, nome, endereco, email);
		}
		catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public String cadastrarCarona(Integer idSessao, String origem,
			String destino, String data, String hora, String vagas) throws GWTException {
		try {
			Integer vagasInt = Integer.parseInt(vagas);
			Carona c = controller.cadastrarCarona(idSessao, origem, destino, data, hora, vagasInt);
			System.out.println("Carona cadastrada:" + c.toString());
			return c.getIdCarona().toString();
		} catch (NumberFormatException nfe) {
			throw new GWTException("Formato de vagas inválido.");
		} catch (IllegalArgumentException iae) {
			throw new GWTException(iae.getMessage());
		}
	}

	@Override
	public Integer abrirSessao(String login, String senha) throws GWTException {
		try {
			return controller.abrirSessao(login, senha).getIdSessao();
		}
		catch(UsuarioInexistenteException uie) {
			throw new GWTException("Usuario inexistente exception");
		}
		catch(IllegalArgumentException iae) {
			throw new GWTException(iae.getMessage());
		}
	}

	@Override
	public String localizarCarona(String idSessao, String origem, String destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCarona(String idCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encerrarSessao(String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zerarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reiniciarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInvalidaException,
			CaronaInexistenteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aceitarSolicitacao(String idSessao, String idSolicitacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String visualizarPerfil(String idSessao, String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reviewCarona(String idSessao, String idCarona, String review)
			throws CaronaInexistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String cadastrarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(String idSessao,
			String cidade, String origem, String destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(String idSessao, String cidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCaronaUsuario(String idSessao, int indexCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getTodasCaronasUsuario(Integer idSessao) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<Carona> listaCaronas = controller.getTodasCaronasUsuario(idSessao);
		for (Carona c : listaCaronas) {
			List<String> caronaList = new LinkedList<String>();
			caronaList.add(c.getOrigem());
			caronaList.add(c.getDestino());
			caronaList.add(c.getData());
			caronaList.add(c.getHora());
			caronaList.add(c.getVagas().toString());
//			caronaList.add(c.getDonoReviewCaroneiros().toString()); //Será esse
			caronaList.add("Review");
			result.add(caronaList);
		}
		return result;
	}

	@Override
	public List<List<String>> getSolicitacoesConfirmadas(Integer idSessao, Integer idCarona) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<Solicitacao> listaCaronas = controller.getSolicitacoesConfirmadas(idSessao, idCarona);
		for (Solicitacao s : listaCaronas) {
			Usuario dono = s.getDonoDaCarona();
			Carona carona = null;
			for (Carona c : dono.getTodasCaronasUsuario()) {
				if (c.getIdCarona().equals(idCarona)) {
					carona = c;
				}
			}
			List<String> caronaList = new LinkedList<String>();
			caronaList.add(carona.getOrigem());
			caronaList.add(carona.getDestino());
			caronaList.add(carona.getData());
			caronaList.add(carona.getHora());
			caronaList.add(carona.getVagas().toString());
//			caronaList.add(c.getDonoReviewCaroneiros().toString()); //Será esse
			caronaList.add("Review");
			result.add(caronaList);
		}
		return result;
	}

	@Override
	public List<List<String>> getSolicitacoesPendentes(Integer idSessao, Integer idCarona)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPontosSugeridos(String idSessao, String idCarona)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPontosEncontro(String idSessao, String idCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cadastrarInteresse(String idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String verificarMensagensPerfil(String idSessao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean enviarEmail(String idSessao, String destino, String message)
			throws MessagingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<List<String>> getTodasCaronasPegas(Integer idSessao) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<Carona> listaCaronas = controller.getTodasCaronasPegas(idSessao);
		for (Carona c : listaCaronas) {
			List<String> caronaList = new LinkedList<String>();
			caronaList.add(c.getOrigem());
			caronaList.add(c.getDestino());
			caronaList.add(c.getData());
			caronaList.add(c.getHora());
			caronaList.add(c.getVagas().toString());
//			caronaList.add(c.getDonoReviewCaroneiro().toString()); //Será esse
			caronaList.add("Review");
			result.add(caronaList);
		}
		return result;
	}
	
	
}
