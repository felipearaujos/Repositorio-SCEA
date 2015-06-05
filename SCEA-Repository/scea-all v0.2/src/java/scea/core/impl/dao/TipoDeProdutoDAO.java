package scea.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import scea.core.impl.dao.AbstractJdbcDAO;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;

public class TipoDeProdutoDAO extends AbstractJdbcDAO{
	public TipoDeProdutoDAO() {
		super("tb_tipoproduto", "id_tipoproduto");		
	}
	public void salvar(EntidadeDominio entidade) {
	}

	
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void excluir(EntidadeDominio entidade){
		
	}
	
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)
			 {
				PreparedStatement pst = null;
		
		TipoDeProduto TipoDeProduto = (TipoDeProduto)entidade;
		String sql=null;
		
		if(TipoDeProduto.getId() ==  null){
			sql = "SELECT * FROM  tb_tipodeproduto  ORDER BY id_tipodeproduto";
		}
		else {
			sql = "SELECT * FROM tb_tipodeproduto  WHERE id_tipodeproduto= "+ TipoDeProduto.getId() +" ORDER BY id_tipodeproduto";
		}

	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		
		
		
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> tipos = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
                    TipoDeProduto p = new TipoDeProduto();
                    p.setId(rs.getInt("id_tipodeproduto"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setTipo(rs.getString("tipo"));
                    p.setQtdeMax(rs.getInt("qtdeMax"));
                    p.setQtdeMin(rs.getInt("qtdeMin"));
                    tipos.add(p);
		}
		return tipos;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;


	}

	
	
	
}


