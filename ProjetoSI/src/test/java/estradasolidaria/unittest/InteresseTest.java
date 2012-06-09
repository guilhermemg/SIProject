package estradasolidaria.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import estradasolidaria.ui.server.logic.Interesse;
import estradasolidaria.ui.server.logic.Usuario;


public class InteresseTest {
	Interesse i1, i2, i3, i4, i5;
	Usuario u1, u2, u3, u4, u5;

	@Before
	public void criaInteressesUsuarios() {
		i1 = new Interesse("Campina Grande", "João Pessoa", "30/12/2012",
				"11:00", "12:00");
		i2 = new Interesse("Campina Grande", "João Pessoa", "30/12/2012", "",
				"12:00");
		i3 = new Interesse("Campina Grande", "João Pessoa", "30/12/2012",
				"11:00", "");
		i4 = new Interesse("Campina Grande", "João Pessoa", "30/12/2012",
				"11:00", "12:00");
		i5 = new Interesse("Campina Grande", "João Pessoa", "", "11:00",
				"12:00");

		u1 = new Usuario("u1", "123", "u1", "Rua Santo Andre", "u1@gmail.com");
		u1 = new Usuario("u2", "123", "u2", "Rua Santo Andre", "u2@gmail.com");
	}

	@Test
	public void testConstrutorData() {
		/*
		 * assertEquals("Hora de Inicio esta errada.Espected \" \"",i2.getHoraInicio
		 * (), "");
		 * assertEquals("Hora de fim esta errada.Espected \" \"",i3.getHoraFim
		 * (), "");
		 */
		assertEquals("Data não é correspondente a esperada. Espected \" \" ",
				i5.getData(), "");
		try {
			new Interesse("Campina Grande", "João Pessoa", null, "11:00",
					"12:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Data inválida");
		} catch (Exception e) {
			fail("Expected DataInvalida Exceptions");
		}

		try {
			new Interesse("Campina Grande", "João Pessoa", "30/02/2012",
					"11:00", "12:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Data inválida");
		} catch (Exception e) {
			fail("Expected DataInvalida Exceptions");
		}

		try {
			new Interesse("Campina Grande", "João Pessoa", "12/05/12", "11:00",
					"");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Data inválida");
		} catch (Exception e) {
			fail("Expected DataInvalida Exceptions");
		}

		try {
			// the format of the date is dd/mm/yyyy
			new Interesse("Campina Grande", "João Pessoa", "12/13/2012",
					"11:00", "");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Data inválida");
		} catch (Exception e) {
			fail("Expected DataInvalida Exceptions");
		}

		try {
			new Interesse("Campina Grande", "João Pessoa", "32/12/2012",
					"11:00", "");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Data inválida");
		} catch (Exception e) {
			fail("Expected DataInvalida Exceptions");
		}

		try {
			new Interesse("Campina Grande", "João Pessoa", "29/02/2011",
					"11:00", "");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Data inválida");
		} catch (Exception e) {
			fail("Expected DataInvalida Exceptions");
		}

	}

	@Test
	public void testConstrutorHora() {
		assertEquals("Hora de Inicio esta errada.Espected \" \"",
				i2.getHoraInicio(), "");
		try {
			new Interesse("Campina Grande", "João Pessoa", "28/02/2016",
					"11:60", "");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Data inválida");
		} catch (Exception e) {
			fail("Expected HoraInvalida Exceptions");
		}

		assertEquals("Hora de Inicio esta errada.Espected \" \"",
				i2.getHoraInicio(), "");
		try {
			new Interesse("Campina Grande", "João Pessoa", "30/11/2012",
					"11:60", "");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Hora inválida");
		} catch (Exception e) {
			fail("Expected HoraInvalida Exceptions");
		}

		try {
			new Interesse("Campina Grande", "João Pessoa", "29/02/2016", // proximo ano bissexto
					"25:00", "");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Hora inválida");
		} catch (Exception e) {
			fail("Expected HoraInvalida Exceptions");
		}

		try {
			new Interesse("Campina Grande", "João Pessoa", "29/02/2016", "",
					"11:60");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Hora inválida");
		} catch (Exception e) {
			fail("Expected HoraInvalida Exceptions");
		}

		try {
			new Interesse("Campina Grande", "João Pessoa", "29/02/2016", "",
					"25:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Hora inválida");
		} catch (Exception e) {
			fail("Expected HoraInvalida Exceptions");
		}
	}

	@Test
	public void testConstrutorOrigem() {
		try {
			new Interesse("!", "João Pessoa", "29/02/2016", "", "15:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Origem inválida");
		} catch (Exception e) {
			fail("Expected OrigemCaronaException");
		}

		try {
			new Interesse("", "João Pessoa", "29/02/2016", "", "15:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Origem inválida");
		} catch (Exception e) {
			fail("Expected OrigemCaronaException");
		}
		try {
			new Interesse(null, "João Pessoa", "29/02/2016", "", "15:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Origem inválida");
		} catch (Exception e) {
			fail("Expected OrigemCaronaException");
		}
	}

	@Test
	public void testConstrutorDestino() {
		try {
			new Interesse("Campina Grande", "!", "29/02/2016", "", "15:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Destino inválido");
		} catch (Exception e) {
			fail("Expected DestinoCaronaException");
		}

		try {
			new Interesse("Campina Grande", "", "29/02/2016", "", "15:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Destino inválido");
		} catch (Exception e) {
			fail("Expected DestinoCaronaException");
		}
		try {
			new Interesse("Campina Grande", null, "29/02/2016", "", "15:00");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "Destino inválido");
		} catch (Exception e) {
			fail("Expected DestinoCaronaException");
		}
	}

}