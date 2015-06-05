package scea.core.testes.testesDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import scea.core.impl.dao.AcessoDAO;
import scea.dominio.modelo.*;
public class testaDAOAcesso {

	public void testaListarAcessoUnico(){
		Acesso usuario = new Acesso();
		usuario.setLogin("f");
		
		AcessoDAO DaoAcesso = new AcessoDAO();
		
		try {
			List<EntidadeDominio> listaUsuarios = DaoAcesso.consultar(usuario);
			
			for(EntidadeDominio e : listaUsuarios)
			{
				usuario = (Acesso)e;
				System.out.println(usuario.getLogin());
				System.out.println(usuario.getSenha());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void testaCadastrarAcesso(){
		Acesso usuario = new Acesso();
		usuario.setLogin("Jonathan");
		usuario.setSenha("12345");
		AcessoDAO dao = new AcessoDAO();
		try {
			dao.salvar(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testaExcluirAcesso(){
		Acesso usuario = new Acesso();
		usuario.setLogin("Jonathan");
		usuario.setSenha("12345");
		AcessoDAO dao = new AcessoDAO();
		try {
			dao.excluir(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testaAlteraAcesso(){
		Acesso usuario = new Acesso();
		usuario.setLogin("Func");
		usuario.setSenha("1234531231");
		AcessoDAO dao = new AcessoDAO();
		try {
			dao.alterar(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
