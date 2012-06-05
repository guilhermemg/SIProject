package estradasolidaria.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import estradasolidaria.ui.server.logic.Usuario;


public class UsuarioTest {
	Usuario u;
	String endereco = "Rua do alem";
	String nome = "Leonardo Alves";
	String login = "login";
	String senha = "testando";
	String email = "leonardo@gmail.com";
	
	@Test
	public void testUsuarioLogin() throws Exception {
		try {
			u = new Usuario(null, senha, nome, endereco, email);
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Login inválido");
		}
		try { 
			u = new Usuario("", senha, nome, endereco, email);			
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Login inválido");
		}
	}
	
	@Test
	public void testUsuarioSenha() throws Exception {
		try {
			u = new Usuario(login, null, nome, endereco, email);
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Senha inválida");
		}
		try {
			u = new Usuario(login , ""    , nome, endereco, email);
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Senha inválida");
		}
	}
	
	@Test
	public void testUsuarioNome() throws Exception {
		try {
			u = new Usuario(login, senha, null, endereco, email);
		}
		catch (Exception e) {
			assertEquals(e.getMessage(), "Nome inválido");
		}
		try {
			u = new Usuario(login, senha, "" , endereco, email);
		}
		catch (Exception e) {
			assertEquals(e.getMessage(), "Nome inválido");
		}
	}
	
	@Test
	public void testUsuarioEndereco() throws Exception {
		try {
			u = new Usuario(login, senha, nome , null     , email);
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Endereço inválido");
		}
		try {
			u = new Usuario(login , senha , nome , ""       , email);
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Endereço inválido");
		}
	}
	
	@Test
	public void testUsuarioEmail() throws Exception {
		try {
			u = new Usuario(login , senha , nome , endereco , null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Email inválido");
		}
		try {
			u = new Usuario(login , senha , nome , endereco , "");
		}
		catch(Exception e) {
			assertEquals(e.getMessage(), "Email inválido");
		}
	}

}
