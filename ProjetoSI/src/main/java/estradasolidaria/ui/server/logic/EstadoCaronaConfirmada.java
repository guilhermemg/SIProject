package estradasolidaria.ui.server.logic;

import java.io.Serializable;
import java.util.Iterator;

import javax.mail.MessagingException;

import estradasolidaria.ui.server.util.SenderMail;

/**
 * Classe que representa o estado de uma carona confirmada.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class EstadoCaronaConfirmada implements EstadoCaronaInterface, Serializable {

	private static final long serialVersionUID = 1125288088782552956L;

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#confirmar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void confirmar(Carona carona) throws CaronaInvalidaException, EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		throw new EstadoCaronaException("Uma carona confirmada não pode ser confirmada novamente.");
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#cancelar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void cancelar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException, MessagingException {
		if(carona == null)
			throw new CaronaInvalidaException();
		if(!carona.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.CONFIRMADA))
			throw new EstadoCaronaException("Somente uma carona confirmada pode ser cancelada.");
		
		Iterator<Solicitacao> itSolicitacoes = carona.getMapIdSolicitacao().values().iterator();
		while(itSolicitacoes.hasNext()) {
			Solicitacao s = itSolicitacoes.next();
			if(s.getEstado().equals(EnumNomeDoEstadoDaSolicitacao.ACEITA)) {
				SenderMail.sendMail(s.getDonoDaSolicitacao().getEmail(), "Esta carona cancela foi cancelada. Mais detalhes entrar em contato" +
						"com o dono da carona");
			}
		}
		carona.setEstadoDaCarona(new EstadoCaronaCancelada());
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#realizar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void realizar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		if(!carona.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.CONFIRMADA))
			throw new EstadoCaronaException("Uma carona que nao esteja confirmada não pode ser realizada.");
		carona.setEstadoDaCarona(new EstadoCaronaOcorrendo());
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#encerrar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void encerrar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		if(!carona.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.OCORRENDO))
			throw new EstadoCaronaException("Somente uma carona que ocorreu ou está ocorrendo pode ser encerrada");
		carona.setEstadoDaCarona(new EstadoCaronaEncerrada());
	}
	
	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#expirar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void expirar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		if(!carona.getEstadoDaCarona().getNomeEstado().equals(EnumNomeEstadoDaCarona.CONFIRMADA)) 
			throw new EstadoCaronaException("Uma carona que não esteja confirmada não pode ser expirada");
		carona.setEstadoDaCarona(new EstadoCaronaExpired());
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#getNomeEstado()
	 */
	@Override
	public EnumNomeEstadoDaCarona getNomeEstado() {
		return EnumNomeEstadoDaCarona.CONFIRMADA;
	}

	/*
	 * (non-Javadoc)
	 * @see estradasolidaria.ui.server.logic.EstadoCaronaInterface#esperar(estradasolidaria.ui.server.logic.Carona)
	 */
	@Override
	public void esperar(Carona carona) throws CaronaInvalidaException,
			EstadoCaronaException, CaronaInvalidaException {
		if(carona == null)
			throw new CaronaInvalidaException();
		carona.setEstadoDaCarona(new EstadoCaronaEsperando());
	}
}
