package estradasolidaria.ui.server;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import estradasolidaria.ui.client.EstradaSolidariaService;
import estradasolidaria.ui.client.GWTCarona;
import estradasolidaria.ui.client.GWTException;
import estradasolidaria.ui.client.GWTInteresse;
import estradasolidaria.ui.server.adder.Adder;
import estradasolidaria.ui.server.logic.Carona;
import estradasolidaria.ui.server.logic.CaronaInexistenteException;
import estradasolidaria.ui.server.logic.EstradaSolidariaController;
import estradasolidaria.ui.server.logic.Interesse;
import estradasolidaria.ui.server.logic.Solicitacao;
import estradasolidaria.ui.server.logic.Usuario;
import estradasolidaria.ui.server.logic.UsuarioInexistenteException;

/**
 * The server side implementation of the RPC service.
 */
public class EstradaSolidariaServiceImpl extends RemoteServiceServlet implements
		EstradaSolidariaService {
	private static final long serialVersionUID = -1007968486871020509L;
	private EstradaSolidariaController controller = EstradaSolidariaController.getInstance();
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
	
	public EstradaSolidariaServiceImpl() {
		try {
			Adder adder = new Adder(this.controller);
			adder.addElements();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
		} catch(Exception e) {
			throw new GWTException(e.getMessage());
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

//	@Override
//	public Map<Integer, String> localizarCarona(Integer idSessao, String origem, String destino) throws GWTException {
//		try {
//			Map<Integer, String> mapaIdCaronasToString = new TreeMap<Integer, String>();
//			List<Carona> listaCaronas = controller.localizarCarona(idSessao, origem, destino);
//			for(Carona c : listaCaronas){
//				mapaIdCaronasToString.put(c.getIdCarona(), c.toString());
//			}
//			return mapaIdCaronasToString;
//		} catch(Exception e){
//			throw new GWTException(e.getMessage());
//		}
//	}
	
	@Override
	public List<GWTCarona> localizarCarona(Integer idSessao, String origem, String destino) throws GWTException {
		try {
			List<GWTCarona> result = new LinkedList<GWTCarona>();
			List<Carona> listaCaronas = controller.localizarCarona(idSessao, origem, destino);
			for(Carona c : listaCaronas) {
				GWTCarona gwt_c = new GWTCarona();
				Date data = c.getData().getTime();
				Date hora = c.getHora().getTime();
				gwt_c.setData(dateFormat.format(data));
				gwt_c.setDestino(c.getDestino());
				gwt_c.setHora(hourFormat.format(hora));
				gwt_c.setIdCarona(c.getIdCarona().toString());
				gwt_c.setOrigem(c.getOrigem());
				gwt_c.setVagas(c.getVagas().toString());
				
				result.add(gwt_c);
			}
			return result;
		}
		catch(Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public String getTrajeto(Integer idCarona) throws GWTException {
		try {
			String[] trajetoString = controller.getTrajeto(idCarona);
			return "De " + trajetoString[0] + " para " + trajetoString[1];
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public List<String> getCarona(Integer idCarona) throws GWTException {
		try {
			Carona c = controller.getCarona(idCarona);
			List<String> dadosCarona = new LinkedList<String>();
			dadosCarona.add(c.getIdCarona().toString());
			dadosCarona.add(c.getIdDonoDaCarona().toString());
			dadosCarona.add(c.getCidade());
			dadosCarona.add(c.getOrigem());
			dadosCarona.add(c.getDestino());
			dadosCarona.add(c.getPontoEncontro());
//		dadosCarona.add(c.getPosicaoNaInsercaoNoSistema());
//		dadosCarona.add(c.getCaroneiroReviewDono(idDonoDaCarona));
//		dadosCarona.add(c.getDonoReviewCaroneiro(idCaroneiro));
//		dadosCarona.add(c.getMinimoCaroneiros().toString());
			dadosCarona.add(c.getVagas().toString());
			dadosCarona.add(c.getData().toString());
			dadosCarona.add(c.getHora().toString());
//		dadosCarona.add(c.getEmailTo());
//		dadosCarona.add(c.getExpired());
//		dadosCarona.add(c.getMapDonoReviewCaroneiro());
//		dadosCarona.add(c.getMapIdSolicitacao());
//		dadosCarona.add(c.getMapSugestoesPontoDeEncontro());
//		dadosCarona.add(c.getPontosSugeridos());
//		dadosCarona.add(c.getTrajeto());
			return dadosCarona;
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public void encerrarSessao(String login) throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zerarSistema() throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarSistema() throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reiniciarSistema() throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String sugerirPontoEncontro(Integer idSessao, Integer idCarona,
			String pontos) throws GWTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void responderSugestaoPontoEncontro(Integer idSessao,
			Integer idCarona, String idSugestao, String pontos) throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String solicitarVagaPontoEncontro(Integer idSessao, Integer idCarona,
			String ponto) throws GWTException {
		try {
			return controller.solicitarVagaPontoEncontro(idSessao, idCarona, ponto).toString();
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
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
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public String solicitarVaga(Integer idSessao, Integer idCarona) throws GWTException {
		try {
			Solicitacao s = controller.solicitarVaga(idSessao, idCarona);
			return s.toString();
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public void rejeitarSolicitacao(Integer idSessao, Integer idSolicitacao) throws GWTException {
		try {
			controller.rejeitarSolicitacao(idSessao, idSolicitacao);
		} catch (CaronaInexistenteException e) {
			throw new GWTException(e.getMessage());
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public void desistirRequisicao(Integer idSessao, Integer idCarona,
			Integer idSolicitacao) throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String visualizarPerfil(Integer idSessao, String login) throws GWTException  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reviewVagaEmCarona(Integer idSessao, Integer idCarona,
			String loginCaroneiro, String review) throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reviewCarona(Integer idSessao, Integer idCarona, String review)
			throws GWTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String cadastrarCaronaMunicipal(Integer idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) throws GWTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(Integer idSessao,
			String cidade, String origem, String destino) throws GWTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> localizarCaronaMunicipal(Integer idSessao, String cidade) throws GWTException  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCaronaUsuario(Integer idSessao, int indexCarona) throws GWTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GWTCarona> getTodasCaronasUsuario(Integer idSessao) throws GWTException {
		List<GWTCarona> result = new LinkedList<GWTCarona>();
		try {
			List<Carona> listaCaronas = controller.getTodasCaronasUsuario(idSessao);
			
			for (Carona c : listaCaronas) {
				result.add(criaGWTCaronaDTO(c));
			}
			
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
		return result;
	}

	/**
	 * Cria uma GWTCarona DTO(Data Transfer Object) a partir de uma Carona
	 * para que o lado-client possa manipular as caronas.
	 * 
	 * @param carona
	 * @return gwtCarona
	 */
	private GWTCarona criaGWTCaronaDTO(Carona carona) {
		GWTCarona gwt_c = new GWTCarona();
		
		Usuario donoDaCarona = controller.getMapIdUsuario().get(carona.getIdDonoDaCarona());
		
		gwt_c.setIdDono(carona.getIdDonoDaCarona().toString());
		gwt_c.setOrigem(carona.getOrigem());
		gwt_c.setDestino(carona.getDestino());
		gwt_c.setData(dateFormat.format(carona.getData().getTime()));
		gwt_c.setHora(hourFormat.format(carona.getHora().getTime()));
		gwt_c.setVagas(carona.getVagas().toString());
		if (carona.getPontoEncontro() == null) {
			gwt_c.setPontoEncontro(carona.getPontoEncontro());
		}else {
			gwt_c.setPontoEncontro(new String(""));
		}
		gwt_c.setNomeDono(donoDaCarona.getNome());
		gwt_c.setIdCarona(carona.getIdCarona().toString());
		
		return gwt_c;
	}

	@Override
	public List<List<String>> getSolicitacoesFeitasConfirmadas(Integer idSessao) throws GWTException {
		try {
			List<List<String>> result = new LinkedList<List<String>>();
			for (Solicitacao s : controller.getMapaSolicitacoesFeitas(idSessao).values()) {
				if (s.isAceita()) {
					List<String> solicitacaoList = new LinkedList<String>();
					
					Usuario donoDaCarona = s.getDonoDaCarona();
					Carona c = donoDaCarona.getMapIdCaronasOferecidas().get(s.getIdCarona());
					
					solicitacaoList.add(c.getIdDonoDaCarona().toString());
					solicitacaoList.add(c.getOrigem());
					solicitacaoList.add(c.getDestino());
					solicitacaoList.add(dateFormat.format(c.getData().getTime()));
					solicitacaoList.add(hourFormat.format(c.getHora().getTime()));
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
			
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public List<List<String>> getSolicitacoesFeitasPendentes(Integer idSessao) throws GWTException {
		try {
			List<List<String>> result = new LinkedList<List<String>>();
			for (Solicitacao s : controller.getMapaSolicitacoesFeitas(idSessao).values()) {
				if (s.isPendente()) {
					List<String> solicitacaoList = new LinkedList<String>();
					
					Usuario donoDaCarona = s.getDonoDaCarona();
					Carona c = donoDaCarona.getMapIdCaronasOferecidas().get(s.getIdCarona());
					
					solicitacaoList.add(c.getIdDonoDaCarona().toString());
					solicitacaoList.add(c.getOrigem());
					solicitacaoList.add(c.getDestino());
					solicitacaoList.add(dateFormat.format(c.getData().getTime()));
					solicitacaoList.add(hourFormat.format(c.getHora().getTime()));
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
			
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public String getPontosSugeridos(Integer idSessao, Integer idCarona)
			throws GWTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPontosEncontro(Integer idSessao, Integer idCarona) throws GWTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cadastrarInteresse(Integer idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim) throws GWTException {
		try {
			return controller.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim).toString();
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public String verificarMensagensPerfil(Integer idSessao) throws GWTException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean enviarEmail(Integer idSessao, String destino, String message)
			throws GWTException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<GWTCarona> getTodasCaronasPegas(Integer idSessao) throws GWTException {
		List<GWTCarona> result = new LinkedList<GWTCarona>();
		try {
			List<Carona> listaCaronas = controller.getTodasCaronasPegas(idSessao);
			for (Carona c : listaCaronas) {
				GWTCarona gwt_c = new GWTCarona();
				
				Usuario donoDaCarona = controller.getMapIdUsuario().get(c.getIdDonoDaCarona());
				
				gwt_c.setIdDono(c.getIdDonoDaCarona().toString());
				gwt_c.setOrigem(c.getOrigem());
				gwt_c.setDestino(c.getDestino());
				gwt_c.setData(dateFormat.format(c.getData().getTime()));
				gwt_c.setHora(hourFormat.format(c.getHora().getTime()));
				gwt_c.setVagas(c.getVagas().toString());
				gwt_c.setPontoEncontro(c.getPontoEncontro());
				gwt_c.setNomeDono(donoDaCarona.getNome());
				gwt_c.setIdCarona(c.getIdCarona().toString());
				
				result.add(gwt_c);
			}
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
		return result;
	}

	@Override
	public void editarSenha(Integer idSessaoAberta, String novaSenha) throws GWTException {
		try {
			controller.setSenha(idSessaoAberta, novaSenha);
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public List<List<String>> getCaroneiros(Integer idSessao, String idCarona) throws GWTException {
		try {
			List<List<String>> result = new LinkedList<List<String>>();
			Integer idCaronaInt = Integer.parseInt(idCarona);
			
			Usuario donoDaCarona = controller.getUsuarioAPartirDeIDSessao(idSessao);
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
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	@Override
	public void editarLogin(Integer idSessaoAberta, String novoLogin) throws GWTException {
		try {
			controller.setLogin(idSessaoAberta, novoLogin);
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	@Override
	public void editarNome(Integer idSessaoAberta, String novoNome) throws GWTException {
		try {
			controller.setNome(idSessaoAberta, novoNome);
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	@Override
	public void editarEmail(Integer idSessaoAberta, String novoEmail) throws GWTException {
		try {
			controller.setEmail(idSessaoAberta, novoEmail);
		}
		catch(Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	@Override
	public void editarEndereco(Integer idSessaoAberta, String novoEndereco) throws GWTException {
		try{
			controller.setEndereco(idSessaoAberta, novoEndereco);
		}
		catch(Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	
	@Override
	public String[] getUsuario(Integer idSessao) throws GWTException{
		try {
			Usuario u = controller.getUsuarioAPartirDeIDSessao(idSessao);
			String[] dadosUsuario = {u.getLogin(), u.getSenha(), u.getNome(), u.getEndereco(), u.getEmail()};
			return dadosUsuario;
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public List<String[]> getSolicitacoes(Integer idSessao, Integer idCarona) throws GWTException {
		try{
			Usuario donoDaCarona = controller.getUsuarioAPartirDeIDSessao(idSessao);
			Collection<Solicitacao> solicitacoes = donoDaCarona.getMapIdCaronasOferecidas().get(idCarona).getMapIdSolicitacao().values();
			List<String[]> result = new LinkedList<String[]>();
			
			for (Solicitacao s : solicitacoes) {
				result.add(new String[] {s.getDonoDaSolicitacao().getIdUsuario().toString(), s.getDonoDaSolicitacao().getNome(), s.getIdSolicitacao().toString()});
			}
			return result;
		} catch(Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	@Override
	public List<GWTInteresse> getInteresses(Integer idSessao) throws GWTException {
		try {
			List<GWTInteresse> result = new LinkedList<GWTInteresse>();
			
			Usuario u = controller.getUsuarioAPartirDeIDSessao(idSessao);
			
			for (Interesse i : u.getMapIdInteresses().values()) {
				
				GWTInteresse gwt_i = new GWTInteresse();
				
				Date data = i.getData().getTime();
				
				gwt_i.setData(dateFormat.format(data));
				gwt_i.setDestino(i.getDestino());
				gwt_i.setHoraInicio(hourFormat.format(i.getHoraInicio().getTime()));
				gwt_i.setHoraFim(hourFormat.format(i.getHoraFim().getTime()));
				gwt_i.setIdInteresse(i.getIdInteresse().toString());
				gwt_i.setOrigem(i.getOrigem());
				
				result.add(gwt_i);
			}
			return result;
		}
		catch(Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	@Override
	public List<String> pesquisaUsuariosNoSistema(String nome) throws GWTException {
		try {
			List<Usuario> listaDeUsuarios = controller.pesquisaUsuariosNoSistema(nome);
			List<String> resultado = new LinkedList<String>();
			for(Usuario u : listaDeUsuarios){
				resultado.add(u.getNome());
			}
			return resultado;
		}
		catch(Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
	@Override
	public List<String> getUsuarioNoSistema(Integer idUsuario) throws GWTException{
		try {
			Usuario u = controller.getUsuarioAPartirDeIDUsuario(idUsuario);
			List<String> dadosUsuario = new LinkedList<String>();
			dadosUsuario.add(u.getNome());
			dadosUsuario.add(u.getLogin());
			dadosUsuario.add(u.getEmail());
			dadosUsuario.add(u.getEndereco());
			return dadosUsuario;
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}

	@Override
	public void deletarInteresse(Integer idSessao, Integer idInteresse) throws GWTException {
		try {
			controller.deletarInteresse(idSessao, idInteresse);
		} catch (Exception e) {
			throw new GWTException(e.getMessage());
		}
	}
	
}
