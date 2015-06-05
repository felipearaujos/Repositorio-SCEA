/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;import java.util.Date;
import java.util.List;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Simulacao;


public class SimulacaoDAO extends AbstractJdbcDAO{

    public SimulacaoDAO() {
	super("tb_simulacao", "id_simulacao");		
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException {
PreparedStatement pst = null;
		
		Simulacao simulacao = (Simulacao)entidade;
		
		String sql=null;
		
		sql = "INSERT INTO tb_simulacao (transacao, quantidade, id_produto)"
			+ " VALUES(?,?,?);";
						
	
	try {
		openConnection();
		
		//Inserindo Telefone	
		pst = connection.prepareStatement(sql);
                pst.setString(1, simulacao.getTipoDeTransacao());
		pst.setInt(2, simulacao.getQtdeItens());
                pst.setInt(3, simulacao.getProduto().getId());
		pst.executeUpdate();
		
		
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}

    }

    @Override
    public void alterar(EntidadeDominio entidade) throws SQLException {
    
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException {
    		PreparedStatement pst = null;
		String sql = null;
		
		Simulacao simulacao = (Simulacao)entidade;
		
		
		sql = "SELECT id_simulacao, dt_futura FROM tb_simulacao WHERE id_simulacao = ?;";
		
		if(simulacao.getId() == null && (!simulacao.getDtTransacaoFutura().equals(""))){
			sql = "SELECT id_simulacao, dt_futura FROM tb_simulacao WHERE dt_futura = ?;";
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			Timestamp time = new Timestamp(simulacao.getDtTransacaoFutura().getTime());
                        if(simulacao.getId() != null){
				pst.setInt(1, simulacao.getId());
			}else if(simulacao.getId() == null && (!simulacao.getDtTransacaoFutura().equals(""))){
				pst.setTimestamp(1, time);	
			}
			

			ResultSet rs = pst.executeQuery();

				Simulacao simulacaoBuscada = new Simulacao();
				
					while(rs.next()){
					simulacaoBuscada.setId(rs.getInt("id_simulacao"));
                                        
					sql = "DELETE FROM tb_simulacao WHERE id_simulacao = ?";
					pst = connection.prepareStatement(sql);
					pst.setInt(1, simulacaoBuscada.getId());
	
					pst.executeUpdate();
					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}

    
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

		PreparedStatement pst = null;
		
		Simulacao simulacao = (Simulacao)entidade;
		String sql=null;
		if(simulacao.getProduto().getNome() == "")
                    sql = "SELECT * FROM tb_simulacao";
                else
                    sql = "SELECT * FROM tb_simulacao WHERE id_produto LIKE ?";
	
	
	try {
		openConnection();
		pst = connection.prepareStatement(sql);
                if(simulacao.getProduto().getId()!=null)
                    pst.setInt(1, simulacao.getProduto().getId());
		ResultSet rs = pst.executeQuery();
		
		
		List<EntidadeDominio> simulacoes = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
			Produto p = new Produto();
                        Simulacao simulacaoCurrent = new Simulacao();
                        simulacaoCurrent.setId(rs.getInt("id_simulacao"));
			simulacaoCurrent.setQtdeItens(rs.getInt("quantidade"));
                        simulacaoCurrent.setTipoDeTransacao(rs.getString("transacao"));
                        p.setId(rs.getInt("id_produto"));
                        simulacaoCurrent.setProduto(p);
			simulacoes.add(simulacaoCurrent);
		}
		return simulacoes;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
		
	}
}
