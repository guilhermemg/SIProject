package si1project2.tests.unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import si1project2.logic.Carona;
import si1project2.logic.Sessao;
import si1project2.logic.Usuario;

public class CaronaTest {
	Carona c1, c2, c3;
	
	@Before
	public void setup() throws Exception {
		Usuario u = new Usuario("login1", "senha1");
		Sessao s = new Sessao(u.getIdUsuario());
		c1 = new Carona(s.getIdSessao(), "origem1", "destino1", "01/01/2000", "05:30", "4");
		c2 = new Carona(s.getIdSessao(), "origem2", "destino2", "01/01/2000", "05:30", "4");
		c3 = new Carona(s.getIdSessao(), "origem3", "destino3", "01/01/2000", "05:30", "4");
	}
		
	@Test
	public void testCarona() {
		try { c1.setOrigem(null);
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Origem inválida");
		}
		
		try { c1.setOrigem("");
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Origem inválida");
		}
		
		try { c1.setDestino(null);
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Destino inválido");
		}
		
		try { c1.setDestino("");
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Destino inválido");
		}
		
		try { c1.setHora(null);
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try { c1.setHora("");
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try { c1.setData(null);
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Data inválida");
		}
		
		try { c1.setData("");
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Data inválida");
		}
		
		try { c1.setVagas("0");
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Vaga inválida");
		}
		
		try { c1.setVagas("-1");
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Vaga inválida");
		}
		
	}

	@Test
	public void testGetTrajeto() {
		assertEquals(c1.getTrajeto(), "origem1 - destino1");
		assertEquals(c2.getTrajeto(), "origem2 - destino2");
		assertEquals(c3.getTrajeto(), "origem3 - destino3");
	}

	@Test
	public void testGetAtributo() throws Exception {
		assertEquals(c1.getAtributo("origem"), "origem1");
		assertEquals(c1.getAtributo("destino"), "destino1");
		assertEquals(c1.getAtributo("hora"), "05:30");
		assertEquals(c1.getAtributo("data"), "01/01/2000");
		assertEquals(c1.getAtributo("vagas"), "4");
	}
	
	@Test
	public void testGetCarona() {
		//"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59"
		assertEquals(c1.getCarona(), "origem1 para destino1, no dia 01/01/2000, as 05:30");
		assertEquals(c2.getCarona(), "origem2 para destino2, no dia 01/01/2000, as 05:30");
		assertEquals(c3.getCarona(), "origem3 para destino3, no dia 01/01/2000, as 05:30");
	}

}
