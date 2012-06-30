package estradasolidaria.ui.server.logic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import estradasolidaria.ui.server.util.DateUtil;

/**
 * Classe representante de um interesse
 * de um usuario por caronas segundo certos
 * requisitos.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class Interesse implements Serializable {
	private static final long serialVersionUID = -3777456363290552046L;
	
	private String origem;
	private String destino;
	private Integer idInteresse;
	
	private DateUtil dateUtil;
	private Calendar horaFim;
	private Calendar horaInicio;
	private Calendar data;
	
	/**
	 * Construtor de interesse.
	 * 
	 * @param origem: origem das caronas em que se interessa o usuario
	 * @param destino: destino das caronas em que se interessa o usuario
	 * @param data: data das caronas em que se interessa o usuario
	 * @param horaInicio: hora de inicio do intervalo em que se interessa o usuario
	 * @param horaFim: hora de fim do intervalo em que se interessa o usuario
	 */
	public Interesse(String origem, String destino, String data,
			String horaInicio, String horaFim) {
		setOrigem(origem);
		setDestino(destino);
		setData(data);
		setHoraInicio(horaInicio);
		setHoraFim(horaFim);
		setIdInteresse(hashCode());
	}

	/**
	 * Configura id de interesse.
	 * 
	 * @param id
	 */
	private void setIdInteresse(Integer id) {
		this.idInteresse = id;
	}
	
	/**
	 * Retorna id do interesse.
	 */
	public Integer getIdInteresse() {
		return idInteresse;
	}

	/**
	 * Retorna hora de inicio.
	 * 
	 * @return hora de inicio
	 */
	public Calendar getHoraInicio() {
		return this.horaInicio;
	}

	/**
	 * Retorna data.
	 * 
	 * @return data
	 */
	public Calendar getData() {
		return this.data;
	}

	/**
	 * Retorna hora de fim.
	 * 
	 * @return hora de fim
	 */
	public Calendar getHoraFim() {
		return this.horaFim;
	}

	/**
	 * Retorna destino.
	 * 
	 * @return destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Configura destino.
	 * 
	 * @param destino
	 */
	public void setDestino(String destino) {
		if(destino == null || destino.equals(""))
			throw new IllegalArgumentException("Destino inválido");
		
		for (int i = 0; i < destino.length(); i++) {
			if (!Character.isLetter(destino.charAt(i)) && destino.charAt(i) != ' ') {
				throw new IllegalArgumentException("Destino inválido");
			}
		}
		this.destino = destino;
	}
	
	/**
	 * Modifica a data da carona que o usuario se interessa. Se o parametro for null
	 * ou não for uma data no formato dd/mm/aaaa, é lançada uma exceção.
	 * 
	 * obs.: se data == "", o
	 * atributo fica null
	 * 
	 * @param data
	 */
	public void setData(String data) {
		dateUtil = new DateUtil(new GregorianCalendar());
		if(data == null)
			throw new IllegalArgumentException("Data inválida");
		else if(data.equals(""))
			this.data = null;
		else if(dateUtil.validaData(data) && dateUtil.validaDataJaPassou())
			this.data = dateUtil.getCalendar();
		else
			throw new IllegalArgumentException("Data inválida");
	}

	/**
	 * Retorna origem.
	 * 
	 * @return origem
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * Configura origem.
	 * 
	 * @param origem
	 */
	public void setOrigem(String origem) {
		if(origem == null || origem.equals(""))
			throw new IllegalArgumentException("Origem inválida");;
		
		for (int i = 0; i < origem.length(); i++) {
			if (!Character.isLetter(origem.charAt(i)) && origem.charAt(i) != ' ') {
				throw new IllegalArgumentException("Origem inválida");
			}
		}
		this.origem = origem;
	}
	
	/**
	 * Configura hora de fim.
	 * 
	 * obs.: se horaFim == "", o
	 * atributo fica null
	 * 
	 * @param horaFim
	 */
	private void setHoraFim(String horaFim) {
		dateUtil = new DateUtil(new GregorianCalendar());
		if(horaFim == null)
			throw new IllegalArgumentException("Hora inválida");
		else if(horaFim.equals(""))
			this.horaFim = null; 
		else if(dateUtil.validaHora(horaFim))
			this.horaFim = dateUtil.getCalendar();
		else
			throw new IllegalArgumentException("Hora inválida");
	}
	
	/**
	 * Configura hora incial
	 * do intervalo de tempo em 
	 * que o usuario se interessa
	 * para uma carona.
	 * 
	 * obs.: se horaInicio == "", o
	 * atributo fica null
	 * 
	 * @param horaInicio
	 */
	private void setHoraInicio(String horaInicio) {
		dateUtil = new DateUtil(new GregorianCalendar());
		if(horaInicio == null)
			throw new IllegalArgumentException("Hora inválida");
		else if(horaInicio.equals(""))
			this.horaInicio = null;
		else if(dateUtil.validaHora(horaInicio))
			this.horaInicio = dateUtil.getCalendar();
		else
			throw new IllegalArgumentException("Hora inválida");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((dateUtil == null) ? 0 : dateUtil.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((horaFim == null) ? 0 : horaFim.hashCode());
		result = prime * result
				+ ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result
				+ ((idInteresse == null) ? 0 : idInteresse.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interesse other = (Interesse) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dateUtil == null) {
			if (other.dateUtil != null)
				return false;
		} else if (!dateUtil.equals(other.dateUtil))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (horaFim == null) {
			if (other.horaFim != null)
				return false;
		} else if (!horaFim.equals(other.horaFim))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (idInteresse == null) {
			if (other.idInteresse != null)
				return false;
		} else if (!idInteresse.equals(other.idInteresse))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		return true;
	}

	/**
	 * Verifica correspondencia entre interesse (this) e
	 * uma carona.
	 * 
	 * Para os horários o sistema poderá deixar o usuário livre, ou seja, ele
	 * poderá não colocar horaInicio(pegará todos a partir das 0h do dia
	 * especificado ou horaFim(pegará todos até às 11:59 do dia especificado)
	 * que a consulta será realizada. data="" retornará todas as caronas que tem
	 * marcadas da data atual em diante.
	 * 
	 * @param c: carona
	 * @return true se carona corresponde a este interesse
	 */
	public boolean verificaCorrespondencia(Carona carona) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(this.getData() == null) { // retorna todas que ainda vao acontecer e tem origem e destino semelhantes ao do interesse
			if(carona.getOrigem().equals(this.getOrigem()) && carona.getDestino().equals(this.getDestino())
				&& (carona.getData().getTimeInMillis() >= Calendar.getInstance().getTimeInMillis())) {
				if(getHoraInicio() != null && getHoraFim() != null) {
					Calendar timeDaCarona = carona.getHora();
					return (getHoraInicio().getTimeInMillis() <= timeDaCarona.getTimeInMillis())
							&& (getHoraFim().getTimeInMillis() >= timeDaCarona.getTimeInMillis());
				}
				return true; // nao precisa especificar hora
			}
		} 
		else {
			if(carona.getOrigem().equals(this.getOrigem()) && carona.getDestino().equals(this.getDestino()) 
				&& dateFormatter.format(carona.getData().getTime()).equals(dateFormatter.format(this.getData().getTime()))) {
				if( getHoraInicio() != null && getHoraFim() != null ) {
					Long timeDaCarona = carona.getHora().getTimeInMillis();
					return getHoraInicio().getTimeInMillis() <= timeDaCarona && 
							getHoraFim().getTimeInMillis() >= timeDaCarona;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm");
		if(this.getData() != null && getHoraFim() != null && getHoraInicio() != null) {
			return "Interesse [origem=" + origem + ", destino=" + destino
					+ ", idInteresse=" + idInteresse + ", horaFim=" + 
			hourFormatter.format(this.getHoraFim().getTime()) + 
			", horaInicio=" + hourFormatter.format(this.getHoraInicio().getTime())
					+ ", data=" + dateFormatter.format(this.getData().getTime()) + "]";
		}
		else {
			return "Interesse [origem=" + origem + ", destino=" + destino
					+ ", idInteresse=" + idInteresse + ", horaFim=" + null + 
			", horaInicio=" + null +  ", data=" + null + "]";
		}
	}
}
