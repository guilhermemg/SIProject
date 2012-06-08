package estradasolidaria.ui.server.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;


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
	private String fname = "xml/sistemaCaronas.xml";
	
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
//		writeIntoFile(usuarios);
	}
	
//	private void writeIntoFile(String x) {
//		try{
//			FileOutputStream file = new FileOutputStream(fname, false);
//			PrintWriter outputStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(file ,"UTF-8")));
//			outputStream.write(x);
//	        outputStream.close();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Repovoa o mapa de usuario do
	 * sistema.
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, Usuario> reiniciarSistema() {
		Map<Integer, Usuario> mapaUsuarios = (TreeMap<Integer, Usuario>)xstream.fromXML(new File(fname));	
		return mapaUsuarios;
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
//		writeIntoFile("");
	}
}
