package scea.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import scea.core.interfaces.ITransacao;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Entrada;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Saida;
import scea.dominio.modelo.Transacao;


public class TransacaoDAO extends AbstractJdbcDAO /*implements ITransacao*/{

	public TransacaoDAO() {
		super("tb_transacao", "id_transacao");		
	}


	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {		
		
		openConnection();
		
		PreparedStatement pst = null;
		Transacao transacao = (Transacao)entidade;
                
               /* if(transacao instanceof Entrada){
                    transacao = (Entrada)entidade;
                }
                else if(transacao instanceof Saida){
                    transacao = (Saida)entidade;
                }*/
                
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_transacao ");
		sql.append("(transacao, dt_transacao, id_acesso, id_produto, quantidade) ");
		sql.append(" VALUES (?, ?, ?, ?, ?)");	
		
		
		try {
			connection.setAutoCommit(false);
					
                        ProdutoDAO prodDAO = new ProdutoDAO();
			prodDAO.connection = connection;
			prodDAO.ctrlTransaction = false;
			prodDAO.alterar(transacao.getProduto());		
			
                        pst = connection.prepareStatement(sql.toString());
                        
			pst.setString(1, transacao.getTipoDeTransacao());
			pst.setTimestamp(2, new Timestamp(transacao.getDtCadastro().getTime()));
			pst.setInt(3, transacao.getAcesso().getId());
			pst.setInt(4, transacao.getProduto().getId());
			pst.setInt(5, transacao.getQtdeDoTipo());
				
			
			pst.execute();
					
			
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

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
        
}
