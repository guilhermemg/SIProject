package estradasolidaria.ui.server;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.MessagingException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.SimpleDateFormat;

import estradasolidaria.ui.client.EstradaSolidariaService;
import estradasolidaria.ui.client.GWTException;
import estradasolidaria.ui.server.adder.Adder;
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
	
	public EstradaSolidariaServiceImpl() {
		Adder adder = new Adder(this.controller);
		adder.addElements();
	}
	
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
	public Map<String, Integer> localizarCarona(Integer idSessao, String origem, String destino) throws GWTException {
		try {
			Map<String, Integer> mapaIdCaronasToString = new TreeMap<String, Integer>();
			List<Carona> listaCaronas = controller.localizarCarona(idSessao, origem, destino);
			for(Carona c : listaCaronas){
				mapaIdCaronasToString.put(c.toString(), c.getIdCarona());
			}
			return mapaIdCaronasToString;
		} catch(Exception e){
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public String getTrajeto(Integer idCarona)
			throws TrajetoInexistenteException {
		String[] trajetoString = controller.getTrajeto(idCarona);
		return "De " + trajetoString[0] + " para " + trajetoString[1];
	}

	@Override
	public String getCarona(Integer idCarona) {
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
	public String sugerirPontoEncontro(Integer idSessao, Integer idCarona,
			String pontos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void responderSugestaoPontoEncontro(Integer idSessao,
			Integer idCarona, String idSugestao, String pontos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String solicitarVagaPontoEncontro(Integer idSessao, Integer idCarona,
			String ponto) throws CaronaInvalidaException,
			CaronaInexistenteException {
		return controller.solicitarVagaPontoEncontro(idSessao, idCarona, ponto).toString();
	}

	@Override
	public void aceitarSolicitacaoPontoEncontro(Integer idSessao,
			Integer idSolicitacao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aceitarSolicitacao(Integer idSessao, Integer idSolicitacao) throws GWTException {
		try {
			controller.aceitarSolicitacao(idSessao, idSolicitacao);
		} catch (IllegalArgumentException e) {
			throw new GWTException(e.getMessage());
		} catch (CaronaInexistenteException e) {
			throw new GWTException(e.getMessage());
		}
		
	}

	@Override
	public String solicitarVaga(Integer idSessao, Integer idCarona)
			throws CaronaInvalidaException {
		Solicitacao s = controller.solicitarVaga(idSessao, idCarona);
		return s.toString();
	}

	@Override
	public void rejeitarSolicitacao(Integer idSessao, Integer idSolicitacao) {
		controller.rejeitarSolicitacao(idSessao, idSolicitacao);
		
	}

	@Override
	public void desistirRequisicao(Integer idSessao, Integer idCarona,
			Integer idSolicitacao) throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String visualizarPerfil(Integer idSessao, String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reviewVagaEmCarona(Integer idSessao, Integer idCarona,
			String loginCaroneiro, String review)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reviewCarona(Integer idSessao, Integer idCarona, String review)
			throws CaronaInexistenteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String cadastrarCaronaMunicipal(Integer idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(Integer idSessao,
			String cidade, String origem, String destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(Integer idSessao, String cidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCaronaUsuario(Integer idSessao, int indexCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getTodasCaronasUsuario(Integer idSessao) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<Carona> listaCaronas = controller.getTodasCaronasUsuario(idSessao);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		for (Carona c : listaCaronas) {
			List<String> caronaList = new LinkedList<String>();
			
			Usuario donoDaCarona = controller.getMapIdUsuario().get(c.getIdDonoDaCarona());
			
			caronaList.add(c.getIdDonoDaCarona().toString());
			caronaList.add(c.getOrigem());
			caronaList.add(c.getDestino());
			caronaList.add(formatter.format(c.getData().getTime()));
			caronaList.add(c.getHora());
			caronaList.add(c.getVagas().toString());
			if (c.getPontoEncontro() == null) {
				caronaList.add(c.getPontoEncontro());
			}else {
				caronaList.add(new String(""));
			}
			caronaList.add(donoDaCarona.getNome());
			caronaList.add(c.getIdCarona().toString());
			
			result.add(caronaList);
		}
		return result;
	}

	@Override
	public List<List<String>> getSolicitacoesFeitasConfirmadas(Integer idSessao) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		List<List<String>> result = new LinkedList<List<String>>();
		for (Solicitacao s : controller.getMapaSolicitacoesFeitas(idSessao).values()) {
			if (s.isAceita()) {
				List<String> solicitacaoList = new LinkedList<String>();
				
				Usuario donoDaCarona = s.getDonoDaCarona();
				Carona c = donoDaCarona.getMapIdCaronasOferecidas().get(s.getIdCarona());
				
				solicitacaoList.add(c.getIdDonoDaCarona().toString());
				solicitacaoList.add(c.getOrigem());
				solicitacaoList.add(c.getDestino());
				solicitacaoList.add(formatter.format(c.getData().getTime()));
				solicitacaoList.add(c.getHora());
				solicitacaoList.add(c.getVagas().toString());
				if (c.getPontoEncontro() != null) {
					solicitacaoList.add(c.getPontoEncontro());
				}else {
					solicitacaoList.add(new String(""));
				}
				solicitacaoList.add(donoDaCarona.getNome());
				solicitacaoList.add(c.getIdCarona().toString());
				
				result.add(solicitacaoList);
			}
		}
		return result;
	}

	@Override
	public List<List<String>> getSolicitacoesFeitasPendentes(Integer idSessao) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		List<List<String>> result = new LinkedList<List<String>>();
		for (Solicitacao s : controller.getMapaSolicitacoesFeitas(idSessao).values()) {
			if (s.isPendente()) {
				List<String> solicitacaoList = new LinkedList<String>();
				
				Usuario donoDaCarona = s.getDonoDaCarona();
				Carona c = donoDaCarona.getMapIdCaronasOferecidas().get(s.getIdCarona());
				
				solicitacaoList.add(c.getIdDonoDaCarona().toString());
				solicitacaoList.add(c.getOrigem());
				solicitacaoList.add(c.getDestino());
				solicitacaoList.add(formatter.format(c.getData().getTime()));
				solicitacaoList.add(c.getHora());
				solicitacaoList.add(c.getVagas().toString());
				if (c.getPontoEncontro() != null) {
					solicitacaoList.add(c.getPontoEncontro());
				}else {
					solicitacaoList.add(new String(""));
				}
				solicitacaoList.add(donoDaCarona.getNome());
				solicitacaoList.add(c.getIdCarona().toString());
				
				result.add(solicitacaoList);
			}
		}
		return result;
	}

	@Override
	public String getPontosSugeridos(Integer idSessao, Integer idCarona)
			throws CaronaInvalidaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPontosEncontro(Integer idSessao, Integer idCarona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cadastrarInteresse(Integer idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String verificarMensagensPerfil(Integer idSessao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean enviarEmail(Integer idSessao, String destino, String message)
			throws MessagingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<List<String>> getTodasCaronasPegas(Integer idSessao) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		List<List<String>> result = new LinkedList<List<String>>();
		List<Carona> listaCaronas = controller.getTodasCaronasPegas(idSessao);
		for (Carona c : listaCaronas) {
			List<String> caronaList = new LinkedList<String>();
			
			Usuario donoDaCarona = controller.getMapIdUsuario().get(c.getIdDonoDaCarona());
			
			caronaList.add(c.getIdDonoDaCarona().toString());
			caronaList.add(c.getOrigem());
			caronaList.add(c.getDestino());
			caronaList.add(formatter.format(c.getData().getTime()));
			caronaList.add(c.getHora());
			caronaList.add(c.getVagas().toString());
			caronaList.add(c.getPontoEncontro());
			caronaList.add(donoDaCarona.getNome());
			
			result.add(caronaList);
		}
		return result;
	}

	@Override
	public void editarSenha(Integer idSessaoAberta, String novaSenha) {
		controller.setSenha(idSessaoAberta, novaSenha);
		
	}

	@Override
	public List<List<String>> getCaroneiros(Integer idSessao, String idCarona) {
		List<List<String>> result = new LinkedList<List<String>>();
		Integer idCaronaInt = Integer.parseInt(idCarona);
		
		Usuario donoDaCarona = controller.getUsuario(idSessao);
		Carona c = donoDaCarona.getMapIdCaronasOferecidas().get(idCaronaInt);
		
		List<Solicitacao> solicitacoesConfirmadas = controller.getSolicitacoesConfirmadas(idSessao, idCaronaInt);
		
		if (solicitacoesConfirmadas.size() > 0) {
			for (Solicitacao s : solicitacoesConfirmadas) {
				List<String> infCaroneiro = new LinkedList<String>();
				
				Usuario donoSolicitacao = s.getDonoDaSolicitacao();
				
				infCaroneiro.add(donoSolicitacao.getIdUsuario().toString());
				infCaroneiro.add(donoSolicitacao.getNome());
				if (c.getDonoReviewCaroneiro(donoSolicitacao.getIdUsuario()) == 0) {
					infCaroneiro.add("1");
				} else {
					infCaroneiro.add("0");
				}
				//Review
			}
		}
		
		return result;
	}
	
	@Override
	public void editarLogin(Integer idSessaoAberta, String novoLogin) {
		controller.setLogin(idSessaoAberta, novoLogin);
	}
	
	@Override
	public void editarNome(Integer idSessaoAberta, String novoNome) {
		controller.setNome(idSessaoAberta, novoNome);
	}
	
	@Override
	public void editarEmail(Integer idSessaoAberta, String novoEmail) {
		controller.setEmail(idSessaoAberta, novoEmail);
	}
	
	@Override
	public void editarEndereco(Integer idSessaoAberta, String novoEndereco) {
		controller.setEndereco(idSessaoAberta, novoEndereco);
	}
	
	
	@Override
	public String[] getUsuario(Integer idSessao){
		Usuario u = controller.getUsuario(idSessao);
		String[] dadosUsuario = {u.getLogin(), u.getSenha(), u.getNome(), u.getEndereco(), u.getEmail()};
		return dadosUsuario;
	}

	@Override
	public List<String[]> getSolicitacoes(Integer idSessao, Integer idCarona) throws GWTException {
		Usuario donoDaCarona = controller.getUsuario(idSessao);
		Collection<Solicitacao> solicitacoes = donoDaCarona.getMapIdCaronasOferecidas().get(idCarona).getMapIdSolicitacao().values();
		List<String[]> result = new LinkedList<String[]>();
		
		for (Solicitacao s : solicitacoes) {
			result.add(new String[] {s.getDonoDaSolicitacao().getIdUsuario().toString(), s.getDonoDaSolicitacao().getNome(), s.getIdSolicitacao().toString()});
		}
		return result;
	}
	
	
}
