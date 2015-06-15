package scea.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import scea.core.impl.dao.AbstractJdbcDAO;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

public class ProdutoDAO extends AbstractJdbcDAO{
	public ProdutoDAO() {
		super("tb_produto", "id_produto");		
	}
	public void salvar(EntidadeDominio entidade) {
		//if(connection == null){
		openConnection();
	//}
	PreparedStatement pst=null;
	
	
	Produto produto = (Produto)entidade;
	StringBuilder sql = new StringBuilder();
	
	sql.append("INSERT INTO tb_produto ");
	sql.append("(nome, id_tipodeproduto, quantidade, vlr, id_fornecedor) ");
	sql.append(" VALUES (?, ?, ?, ?, ?)");	
	
	
	try {
		connection.setAutoCommit(false);
		
		//System.out.println("---------------" + transacao.getProduto().getId());		
		pst = connection.prepareStatement(sql.toString());
		pst.setString(1, produto.getNome());
		pst.setInt(2, produto.getTipoDeProduto().getId());
		pst.setInt(3, produto.getQuantidade());
		pst.setDouble(4, produto.getValor());
		pst.setInt(5, produto.getFornecedor().getId());

		
		pst.execute();//pst.executeUpdate(); ????
				
		/*ResultSet rs = pst.getGeneratedKeys();
		
		int idProd=0;
		if(rs.next())
			idProd = rs.getInt(1);
		produto.setId(idProd);
*/
		connection.commit();					
	} catch (SQLException e) {
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}//catch
		e.printStackTrace();	
	}finally{
		if(ctrlTransaction){
			try {
				//pst.close();
				if(ctrlTransaction)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//catch
		}//if
	}//F

}

	
	

	
	public void alterar(EntidadeDominio entidade) {
		//if(connection == null){
			openConnection();
	//	}
		
		Produto produto = (Produto)entidade;
		
		PreparedStatement pst = null;
		
		String sql;
		sql = "UPDATE tb_produto "
				+ "SET "
				+ "quantidade = " + produto.getQuantidade() + " "
				+"WHERE id_produto = "+ produto.getId();;
		try {
			connection.setAutoCommit(false);							
			
			 pst = (PreparedStatement) connection.prepareStatement(sql);    	  	             
			 pst.executeUpdate();
			 connection.commit();	        
		         
		        // return objAluno;
		         
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}//catch
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction){
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}//catch
			}//if			
		}
		

		//return objAluno;
		}//metodo
	
	
	public void excluir(EntidadeDominio entidade){
		
	}

	
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Produto produto = (Produto)entidade;
		String sql=null;
	
		 if(produto.getId() == 0 && produto.getNome() == null && produto.getFornecedor().getId() == 0){
                    sql = "SELECT * FROM "
                            + "tb_produto "
                            + "JOIN tb_tipodeproduto USING(id_tipodeproduto) "
                            + "JOIN tb_fornecedor USING(id_fornecedor) "
                            + "ORDER BY id_produto";
		}
               
		else if(produto.getId() != 0 && produto.getNome() == null && produto.getFornecedor().getId() == 0){
                    sql = "SELECT * "
                            + "FROM tb_produto "
                            + "JOIN tb_tipodeproduto USING(id_tipodeproduto) "
                            + "JOIN tb_fornecedor USING(id_fornecedor) "
                            + "WHERE id_produto=?   ORDER BY id_produto";
		}
                
                else if(produto.getId() == 0 && produto.getNome() != null && produto.getFornecedor().getId() == 0){
                   
		    sql = "SELECT * "
                            + "FROM tb_produto p "
                            + "JOIN tb_tipodeproduto  t USING(id_tipodeproduto) "
                            + "JOIN tb_fornecedor f USING(id_fornecedor) "
                            + "WHERE p.nome like ?  "
                            + "ORDER BY p.id_produto"; 
                } 
                 
                else if(produto.getId() == 0 && produto.getNome() == null && produto.getFornecedor().getId() != 0){
                    sql = "SELECT * "
                            + "FROM tb_produto "
                            + "JOIN tb_tipodeproduto USING(id_tipodeproduto) "
                            + "JOIN tb_fornecedor USING(id_fornecedor) "
                            + "WHERE id_fornecedor=?   "
                            + "ORDER BY id_produto";
		} 
                 

	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		
		if(produto.getId() != 0 && produto.getNome() == null && produto.getFornecedor().getId() == 0 ){
			pst.setInt(1, produto.getId());	
		}
                else if( produto.getId() == 0 && produto.getNome() != null && produto.getFornecedor().getId() == 0){
                    pst.setString(1,"%"+produto.getNome()+"%");
                }
		
                else if( produto.getId() == 0 && produto.getNome() == null && produto.getFornecedor().getId() != 0){
                    pst.setInt(1,produto.getFornecedor().getId());
                }
		
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
                Produto p = new Produto();
			
			
			p.setId(rs.getInt("id_produto"));
			p.setNome(rs.getString("nome"));
			p.setQuantidade(rs.getInt("quantidade"));
			p.setValor(rs.getDouble("vlr"));
			p.getFornecedor().setId((rs.getInt("id_fornecedor")));
			p.getTipoDeProduto().setId(rs.getInt("id_tipodeproduto"));
			p.getTipoDeProduto().setDescricao(rs.getString("descricao"));
			p.getTipoDeProduto().setQtdeMax(rs.getInt("qtdeMax"));
			p.getTipoDeProduto().setQtdeMin(rs.getInt("qtdeMin"));
			p.getTipoDeProduto().setTipo((rs.getString("tipo")));
			
			p.getFornecedor().setId(rs.getInt("id_fornecedor"));
			p.getFornecedor().setNome(rs.getString("nome"));
			p.getFornecedor().setEmail(rs.getString("email"));
			p.getFornecedor().setNomeFantasia(rs.getString("nome_fantasia"));
			p.getFornecedor().setRazaoSocial(rs.getString("rzsocial"));
			p.getFornecedor().setCNPJ(rs.getString("cnpj"));
						
			produtos.add(p);
		}
		return produtos;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;

	}
        
        
        
        
        
        
        // ---------- 
        
        public List<EntidadeDominio> consultarRelatorioInicial(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		Produto produto = (Produto)entidade;
		String sql=null;
		
                sql = "SELECT * FROM tb_produto p JOIN tb_tipodeproduto tp USING(id_tipodeproduto) JOIN tb_fornecedor  f USING(id_fornecedor) WHERE p.quantidade <= tp.qtdeMin OR p.quantidade = 0  ORDER BY p.id_produto";
			
		
	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		
		
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
                        Produto p = new Produto();
			
			p.setId(rs.getInt("id_produto"));
			p.setNome(rs.getString("nome"));
			p.setQuantidade(rs.getInt("quantidade"));
			p.setValor(rs.getDouble("vlr"));
			p.getFornecedor().setId((rs.getInt("id_fornecedor")));
			p.getTipoDeProduto().setId(rs.getInt("id_tipodeproduto"));
			p.getTipoDeProduto().setDescricao(rs.getString("descricao"));
			p.getTipoDeProduto().setQtdeMax(rs.getInt("qtdeMax"));
			p.getTipoDeProduto().setQtdeMin(rs.getInt("qtdeMin"));
			p.getTipoDeProduto().setTipo((rs.getString("tipo")));
			
			p.getFornecedor().setId(rs.getInt("id_fornecedor"));
			p.getFornecedor().setNome(rs.getString("nome"));
			p.getFornecedor().setEmail(rs.getString("email"));
			p.getFornecedor().setNomeFantasia(rs.getString("nome_fantasia"));
			p.getFornecedor().setRazaoSocial(rs.getString("rzsocial"));
			p.getFornecedor().setCNPJ(rs.getString("cnpj"));
						
			produtos.add(p);
		}
		return produtos;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;

	}

	
}


