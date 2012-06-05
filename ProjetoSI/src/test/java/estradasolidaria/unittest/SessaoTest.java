package estradasolidaria.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import estradasolidaria.ui.server.logic.Sessao;
import estradasolidaria.ui.server.logic.Usuario;


public class SessaoTest {
	
	Usuario italo;
	Sessao sessaoItalo;

	@Before
	public void setUp() throws Exception {
		italo = new Usuario("italomgx", "1234", "Italo Guedes", "Rua Hell", "italo@insano.com");
	}

	@Test
	public void testIdUsuario() {
		try{
			sessaoItalo = new Sessao(0);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Identificador de usuario invalido");
		}
		
		try{
			sessaoItalo = new Sessao(null);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Identificador de usuario invalido");
		}
	}
	
	@Test
	public void testGetIdUser(){
		sessaoItalo = new Sessao(italo.getIdUsuario());
		assertEquals(italo.getIdUsuario(), sessaoItalo.getIdUser());
	}

}
