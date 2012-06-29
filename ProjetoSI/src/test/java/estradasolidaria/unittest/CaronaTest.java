package estradasolidaria.unittest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import estradasolidaria.ui.server.logic.*;


public class CaronaTest {
	Usuario donoCaronas;
	Carona carona1, carona2, carona3; 
	
	@Before
	public void setup() throws Exception {
		donoCaronas = new Usuario("italomgx", "1234", "Italo Guedes", "Rua Hell", "italo@insano.com");
		carona3 = new Carona(donoCaronas.getIdUsuario(), "Centro", "Catol�", "15/07/2012", "08:30", 2, "Campina Grande", 5);
	}

	// two blocks of test for each constructor
	
	@Test
	public void idUsuarioTest(){
		try {
			carona1 = new Carona(null, "Campina Grande", "Jo�o Pessoa", "21/06/2013", "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "IdDonoDaCarona inválido");
		}
	}
	
	@Test
	public void idUsuarioTest2(){
		try {
			carona2 = new Carona(null, "Centen�rio", "Conjunto dos Professores", "21/06/2013", "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "IdDonoDaCarona inválido");
		}
	}
	
	@Test
	public void origemTest(){
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "", "Jo�o Pessoa", "21/06/2013", "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Origem inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), null, "Jo�o Pessoa", "21/06/2013", "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Origem inválida");
		}
	}
	
	@Test
	public void origemTest2(){
		try {
			carona2 = new Carona(donoCaronas.getIdUsuario(), "", "Conjunto dos Professores", "21/06/2013", "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Origem inválida");
		}
		
		try {
			carona2 = new Carona(donoCaronas.getIdUsuario(), null, "Conjunto dos Professores", "21/06/2013", "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Origem inválida");
		}
	}
	
	@Test
	public void destinoTest(){
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "", "21/06/2013", "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Destino inválido");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", null, "21/06/2013", "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Destino inválido");
		}
	}
	
	@Test
	public void destinoTest2(){
		try {
			carona2 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "", "21/06/2013", "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Destino inválido");
		}
		
		try {
			carona2 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", null, "21/06/2013", "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Destino inválido");
		}
	}
	
	@Test
	public void dataTest(){
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "", "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Data inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", null, "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Data inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "16/11/2000", "05:30", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Data inválida");
		}
	}
	
	@Test
	public void dataTest2(){
		try {
			carona2 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "", "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Data inválida");
		}
		
		try {
			carona2 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", null, "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Data inválida");
		}
		
		try {
			carona2 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "16/11/2000", "05:30", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Data inválida");
		}
	}
	
	@Test
	public void horaTest(){
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "21/07/2013", "", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "21/07/2013", null, 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "21/07/2013", "24:00", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "21/06/2013", "13:78", 4, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
	}
	
	@Test
	public void horaTest2(){
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "21/06/2013", "", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "21/06/2013", null, 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "21/06/2013", "24:00", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "21/06/2013", "13:78", 4, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Hora inválida");
		}
	}
	
	@Test
	public void vagasTest(){
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "21/06/2013", "05:30", -0, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Vaga inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "21/06/2013", "05:30", null, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Vaga inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Campina Grande", "Jo�o Pessoa", "21/06/2013", "05:30", -1, 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Vaga inválida");
		}
	}
	
	@Test
	public void vagasTest2(){
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "21/06/2013", "05:30", 0, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Vaga inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "21/06/2013", "05:30", null, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Vaga inválida");
		}
		
		try {
			carona1 = new Carona(donoCaronas.getIdUsuario(), "Centen�rio", "Conjunto dos Professores", "21/06/2013", "05:30", -1, "Campina Grande", 1);
		} catch (Exception e){
			assertEquals(e.getMessage(), "Vaga inválida");
		}
	}
	
	@Test
	public void equalsTest() throws Exception{
		Carona carona4 = carona3;
		assertTrue(carona4.equals(carona3));
	}
	
	@Test 
	public void getTrajetoTest(){
        assertEquals("Centro - Catol�", carona3.getTrajeto()[0] + " - " + carona3.getTrajeto()[1]);
	}

	@Test
	public void testGetCarona() {
		assertEquals("Centro para Catol�, no dia 15/07/2012, as 08:30", carona3.getCarona().toString());
	}
}
