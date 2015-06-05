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


public class TransacaoDAO extends AbstractJdbcDAO implements ITransacao{

	public TransacaoDAO() {
		super("tb_transacao", "id_transacao");		
	}


	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {		
		if(connection == null){
			openConnection();
		}
		PreparedStatement pst = null;
		Transacao transacao = (Transacao)entidade;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_transacao ");
		sql.append("(transacao, dt_transacao, id_acesso, id_produto, quantidade) ");
		sql.append(" VALUES (?, ?, ?, ?, ?)");	
		
		
		try {
			connection.setAutoCommit(false);
			
			//System.out.println("---------------" + transacao.getProduto().getId());		
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, transacao.getTipoDeTransacao());
			pst.setString(2, " sysdate() ");//pst.setString(2, transacao.transacao.getData());
			
			Date data = new Date();		
			Timestamp time = new Timestamp(data.getTime());
			pst.setTimestamp(2, time);
			pst.setInt(3, transacao.getAcesso().getId());
			pst.setInt(4, transacao.getProduto().getId());
			//pst.setInt(5, transacao.getProduto().getQuantidade());
			pst.setInt(5, transacao.getQtdeDoTipo());
				
			
			pst.execute();//pst.executeUpdate(); ????
					
			/*ResultSet rs = pst.getGeneratedKeys();
			
			int idEnd=0;
			if(rs.next())
				idEnd = rs.getInt(1);
			end.setId(idEnd);
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


	public void entrar(EntidadeDominio entidade) throws SQLException
	{
		/*try{
				
			openConnection();
			PreparedStatement pst=null;
			Transacao t = (Transacao)entidade;
			Produto p = t.getProduto();
			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_transacao(transacao, dt_transacao, id_acesso");
			sql.append(", id_produto, quantidade) VALUES (?,?,?,?,?)");		
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, "ENTRADA");
			pst.setString(2, t.getData());
			pst.setInt(3, 1); //ATTENTION : POIS AINDA SERA IMPLEMENTADO
			pst.setInt(4, 1); // INSERIDO APENAS P TESTE
			pst.setInt(5, t.getQtdeDoTipo());
			pst.executeUpdate();
			
			sql = new StringBuilder();
			p.setQuantidade(p.getQuantidade() + t.getQtdeDoTipo());
			sql.append("UPDATE tb_produto SET quantidade = ?");
			sql.append(" WHERE id_produto = ?");
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, t.getProduto().getQuantidade());
			pst.setInt(2, 1); 	// APENAS PARA TESTE
			pst.executeUpdate();
		}
		catch(SQLException ex)
		{
			
		}
			
		*/
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

		
	
	
	public void sair(EntidadeDominio entidade)throws SQLException
	{
		/*try
		{
			openConnection();
			PreparedStatement pst=null;
			Transacao t = (Transacao)entidade;
			Produto p = t.getProduto();
			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_transacao(transacao, dt_transacao, id_acesso");
			sql.append(", id_produto, quantidade) VALUES (?,?,?,?,?)");		
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, "SAIDA");
			pst.setString(2, t.getData());
			pst.setInt(3, 1); //ATTENTION : POIS AINDA SERA IMPLEMENTADO
			pst.setInt(4, 1); // INSERIDO APENAS P TESTE
			pst.setInt(5, t.getQtdeDoTipo());
			pst.executeUpdate();	
	
			
			sql = new StringBuilder();
			p.setQuantidade(t.getQtdeDoTipo() - p.getQuantidade());
			sql.append("UPDATE tb_produto SET quantidade = ?");
			sql.append(" WHERE id_produto = ?");
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, t.getProduto().getQuantidade());
			pst.setInt(2, 1); 	// APENAS PARA TESTE
			pst.executeUpdate();	
		}
		catch(SQLException ex)
		{
		
		}
		*/
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
	
}
