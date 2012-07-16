package estradasolidaria.ui.server.data;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import estradasolidaria.ui.server.logic.Carona;
import estradasolidaria.ui.server.logic.Solicitacao;
import estradasolidaria.ui.server.logic.Usuario;


/**
 * Classe responsavel por fazer
 * a persistencia de dados e a 
 * manipulacao destes na base do
 * sistema.
 * 
 * @author Guilherme Monteiro
 * @author Leonardo Santos
 * @author Hema Vidal
 * @author Italo Silva
 *
 */
public class GerenciadorDeDados {
	private XStream xstream;
	private Map<Integer, Usuario> mapIdUsuario;
	private String fname = "xml/estradaSolidaria.xml";
	
	private static volatile GerenciadorDeDados uniqueInstance;
	
	private GerenciadorDeDados(Map<Integer, Usuario> mapIdUsuario) {
		xstream = new XStream(new DomDriver());
		xstream.alias("usuario", Usuario.class);
		xstream.alias("carona", Carona.class);
		xstream.alias("solicitacao", Solicitacao.class);
		this.mapIdUsuario = mapIdUsuario;
	}
	
	public static synchronized GerenciadorDeDados getInstance(Map<Integer, Usuario> mapIdUsuario) {
		if(uniqueInstance == null) {
			synchronized(GerenciadorDeDados.class) {
				if(uniqueInstance == null)
					uniqueInstance = new GerenciadorDeDados(mapIdUsuario);
			}
		}
		return uniqueInstance;
	}
	
	private void makePersistence() {
		String usuarios = xstream.toXML(mapIdUsuario); 
		writeIntoFile(usuarios);
	}
	
	private void writeIntoFile(String x) {
		try{
			File file = new File(fname);
			PrintWriter outputStream = new PrintWriter(file ,"UTF-8");
			outputStream.write(x);
	        outputStream.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Repovoa o mapa de usuario do
	 * sistema.
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, Usuario> reiniciarSistema() {
		File file = new File(fname);
		return Collections.synchronizedMap((Map<Integer, Usuario>) xstream.fromXML(file));	
	}
	
	/**
	 * Persiste os dados do sistema.
	 */
	public void encerrarSistema() {
		makePersistence();
	}
	
	/**
	 * Zera arquivo xml.
	 */
	public void zerarSistema() {
		writeIntoFile("");
	}
}
