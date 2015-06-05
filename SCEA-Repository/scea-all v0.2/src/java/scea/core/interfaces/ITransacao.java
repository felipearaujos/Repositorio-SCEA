package scea.core.interfaces;

import java.sql.SQLException;

import scea.dominio.modelo.EntidadeDominio;



public interface ITransacao extends IDAO{

	public void entrar(EntidadeDominio entidade) throws SQLException;
	public void sair(EntidadeDominio entidade)throws SQLException;
	
}
