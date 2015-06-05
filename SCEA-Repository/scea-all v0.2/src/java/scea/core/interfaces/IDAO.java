package scea.core.interfaces;

import java.sql.SQLException;
import java.util.List;

import scea.dominio.modelo.EntidadeDominio;



public interface IDAO {

	
	public void salvar(EntidadeDominio entidade)  throws SQLException;
	public void alterar(EntidadeDominio entidade) throws SQLException;
	public void excluir(EntidadeDominio entidade) throws SQLException;
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)throws SQLException;
	//public void consultar(EntidadeDominio entidade);
	//public EntidadeDominio consultar(EntidadeDominio entidade);
	//public List<EntidadeDominio> consultar(EntidadeDominio entidade)throws SQLException;
	
}
