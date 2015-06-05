package scea.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.aplicacao.relatorio.RelatorioEstoque;


import scea.core.impl.dao.AbstractJdbcDAO;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

public class RelatoriosDAO extends AbstractJdbcDAO{
	public RelatoriosDAO() {
		super("tb_transacao", "id_transacao");		
	}
	
	
	public List<EntidadeDominio> consultarRelTransacoesPeriodo(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		EntidadeRelatorio relTransPeriodo = (EntidadeRelatorio)entidade;
		String sql=null;
		
                if(entidade instanceof EntidadeRelatorio){
		//sql = "SELECT  transacao, sum(quantidade) AS 'quantidade', monthname(dt_transacao) AS 'mes' FROM tb_transacao  WHERE dt_transacao BETWEEN " + relTransPeriodo.getDtInicial() + " AND " + relTransPeriodo.getDtFinal() + " GROUP BY transacao, month(dt_transacao) ORDER BY month(dt_transacao)"; 
                  sql = "SELECT  transacao, sum(quantidade) AS 'quantidade', dt_transacao AS 'mes' FROM tb_transacao  WHERE dt_transacao BETWEEN ? AND ? GROUP BY transacao, month(dt_transacao) ORDER BY month(dt_transacao)"; 
                }
                
	
		
	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		//new java.sql.Date(funcionario.getDataAdmissao().getTime())
		pst.setDate(1, new java.sql.Date(relTransPeriodo.getDtInicial().getTime()));
                pst.setDate(2, new java.sql.Date(relTransPeriodo.getDtFinal().getTime()));
		//pst.setString(1, relTransPeriodo.getDtInicial());
                //pst.setString(2, relTransPeriodo.getDtFinal());
                
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> relatorio = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
                EntidadeRelatorio r = new EntidadeRelatorio();
			r.setTransacao(new Transacao());
                        r.getTransacao().setTipoDeTransacao(rs.getString("transacao"));
                        r.getTransacao().setQtdeDoTipo(rs.getInt("quantidade"));
                        
                        //r.setNmTransacao(rs.getString("transacao"));
                        //r.setQuantidade(rs.getInt("quantidade"));
                        r.setMes(rs.getString("mes"));
                        
			relatorio.add(r);
		}
		return relatorio;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;

	}
        
        
        
        
        public List<EntidadeDominio> consultarTransacoesProdPeriodo(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
                //EntidadeRelatorio relTransProd = (EntidadeRelatorio)entidade;
		//TransacoesProd relTransProd = (TransacoesProd)relTransPeriodo;
		//TransacoesProd relTransProd = (TransacoesProd) entidade;
                EntidadeRelatorio relTransProd = (EntidadeRelatorio)entidade;
                String sql=null;
		
                
                sql ="SELECT  t.id_produto as 'idprod', p.nome as 'nomeprod', t.transacao as 'transacao', sum(t.quantidade) as 'quantidade', monthname(dt_transacao) as 'mes' FROM tb_transacao t JOIN tb_produto p USING(id_produto) WHERE dt_transacao BETWEEN ? AND ? GROUP BY t.id_produto, t.transacao, month(t.dt_transacao) ORDER BY month(t.dt_transacao)";	
                
		
	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		pst.setDate(1, new java.sql.Date(relTransProd.getDtInicial().getTime()));
                pst.setDate(2, new java.sql.Date(relTransProd.getDtFinal().getTime()));
		
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> relatorio = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
                EntidadeRelatorio r = new EntidadeRelatorio();
		    //r.setProduto(new Produto());
                    //r.getProduto().setId(rs.getInt("idprod"));
                    //r.getProduto().setNome(rs.getString("nomeprod"));
          
                    r.setTransacao(new Transacao());
                    r.getTransacao().setTipoDeTransacao(rs.getString("transacao"));
                    r.getTransacao().setQtdeDoTipo(rs.getInt("quantidade"));
                    //r.setNmTransacao(rs.getString("transacao"));
                    //r.setQuantidade(rs.getInt("quantidade"));
                    r.getTransacao().setProduto(new Produto());
                    r.getTransacao().getProduto().setId(rs.getInt("idprod"));
                    r.getTransacao().getProduto().setNome(rs.getString("nomeprod"));
                    
                    r.setMes(rs.getString("mes"));
			relatorio.add(r);
		}
		return relatorio;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;

	}
        
        
        
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
        
        
        public List<EntidadeDominio> consultarRelArmazenamentoEstoque(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		
		EntidadeRelatorio relTransPeriodo = (EntidadeRelatorio)entidade;
		String sql=null;
		
                //if(entidade instanceof EntidadeRelatorio){
		//sql = "SELECT  transacao, sum(quantidade) AS 'quantidade', monthname(dt_transacao) AS 'mes' FROM tb_transacao  WHERE dt_transacao BETWEEN " + relTransPeriodo.getDtInicial() + " AND " + relTransPeriodo.getDtFinal() + " GROUP BY transacao, month(dt_transacao) ORDER BY month(dt_transacao)"; 
                  //sql = "SELECT  transacao, sum(quantidade) AS 'quantidade', monthname(dt_transacao) AS 'mes' FROM tb_transacao  WHERE dt_transacao BETWEEN ? AND ? GROUP BY transacao, month(dt_transacao) ORDER BY month(dt_transacao)"; 
                  sql = "SELECT sum(quantidade) as 'qtdeEstoque', sum(qtdeMax)as 'qtdeDiponivel', (sum(quantidade)/sum(qtdeMax))*100 as 'porcentagemOcupada' FROM tb_produto JOIN tb_tipodeproduto using(id_tipodeproduto) ";
                //}
                
	
		
	try {
		openConnection();
		pst = connection.prepareStatement(sql);
		//new java.sql.Date(funcionario.getDataAdmissao().getTime())
		//pst.setDate(1, new java.sql.Date(relTransPeriodo.getDtInicial().getTime()));
                //pst.setDate(2, new java.sql.Date(relTransPeriodo.getDtFinal().getTime()));
		//pst.setString(1, relTransPeriodo.getDtInicial());
                //pst.setString(2, relTransPeriodo.getDtFinal());
                
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> relatorio = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
                RelatorioEstoque r = new RelatorioEstoque();
			
                       // r.setTransacao(new Transacao());
                       // r.getTransacao().setTipoDeTransacao(rs.getString("transacao"));
                       // r.getTransacao().setQtdeDoTipo(rs.getInt("quantidade"));
                    r.setQtdeDiponivel(rs.getInt("qtdeDiponivel"));
                    r.setQtdeEstoque(rs.getInt("QtdeEstoque"));
                    r.setPorcentagemOcupada(rs.getFloat("PorcentagemOcupada"));
                    //r.setMes(rs.getString("mes"));
                        			
			relatorio.add(r);
		}
		return relatorio;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;

	}


        
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
}


