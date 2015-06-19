package scea.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import scea.core.interfaces.ITransacao;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
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
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_transacao ");
		sql.append("(transacao, dt_transacao, id_acesso, id_produto, quantidade) ");
		sql.append(" VALUES (?, ?, ?, ?, ?)");	
		
		
		try {
			connection.setAutoCommit(false);
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, transacao.getTipoDeTransacao());
			//pst.setString(2, " sysdate() ");//pst.setString(2, transacao.transacao.getData());
			
			Date data = new Date();		
			Timestamp time = new Timestamp(data.getTime());
			pst.setTimestamp(2, time);
			pst.setInt(3, transacao.getAcesso().getId());
			pst.setInt(4, transacao.getProduto().getId());
			//pst.setInt(5, transacao.getProduto().getQuantidade());
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

/*
	public void entrar(EntidadeDominio entidade) throws SQLException
	{
		
		if(entidade instanceof Transacao)
		{
			TransacaoDAO transacaoDAO = new TransacaoDAO();
			// declaracoes e castings
			Transacao t = (Transacao)entidade;
			transacaoDAO.salvar(t);			
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			//EntidadeDominio entidadeConsultada = new EntidadeDominio();
			
			Produto produtoBanco = new Produto();
			produtoBanco = (Produto)produtoDAO.consultar(t.getProduto()).get(0);
			
			//produtoBanco.setQuantidade(produtoBanco.getQuantidade() +  t.getProduto().getQuantidade()); 
			produtoBanco.setQuantidade(produtoBanco.getQuantidade() +  t.getQtdeDoTipo()); 
					
			produtoDAO.alterar(produtoBanco);
		}
	}
*/
		
	
/*	
	public void sair(EntidadeDominio entidade)throws SQLException
	{
	
		if(entidade instanceof Transacao)
		{
			TransacaoDAO transacaoDAO = new TransacaoDAO();
			// declaracoes e castings
			Transacao t = (Transacao)entidade;
			transacaoDAO.salvar(t);			
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			//EntidadeDominio entidadeConsultada = new EntidadeDominio();
			
			Produto produtoBanco = new Produto();
			produtoBanco = (Produto)produtoDAO.consultar(t.getProduto()).get(0);
			
			//produtoBanco.setQuantidade(produtoBanco.getQuantidade() +  t.getProduto().getQuantidade()); 
			produtoBanco.setQuantidade(produtoBanco.getQuantidade() -  t.getQtdeDoTipo()); 
					
			produtoDAO.alterar(produtoBanco);
			
		}

	}
        */
	
}
