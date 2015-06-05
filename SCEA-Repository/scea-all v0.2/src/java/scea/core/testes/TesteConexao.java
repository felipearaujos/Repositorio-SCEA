package scea.core.testes;

import java.sql.Connection;
import java.sql.SQLException;

import scea.core.util.Conexao;

public class TesteConexao {
	
	Connection connection;
	
	
	public void testeDeveRealizarConexaoComOBanco() throws ClassNotFoundException, SQLException{
		connection = new Conexao().getConnection();
		
		if(connection == null)
		{
			System.out.println("Erro na conexão ao banco!");
		}
		else
		{
			System.out.println("Conexão realizada com sucesso!");
		}
	}
}
