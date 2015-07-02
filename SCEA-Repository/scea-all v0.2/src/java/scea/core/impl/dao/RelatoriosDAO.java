package scea.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.text.html.parser.DTD;
import scea.core.aplicacao.relatorio.EntidadeRelatorio;
import scea.core.aplicacao.relatorio.RelatorioDetalheEstoque;
import scea.core.aplicacao.relatorio.RelatorioDinamico;
import scea.core.aplicacao.relatorio.RelatorioEstoque;

import scea.core.impl.dao.AbstractJdbcDAO;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

public class RelatoriosDAO extends AbstractJdbcDAO {

    public RelatoriosDAO() {
        super("tb_transacao", "id_transacao");
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
        EntidadeRelatorio relatorio = (EntidadeRelatorio) entidade;

        if (relatorio.getNome().toUpperCase().equals("RELATORIOTRANSACOES")) {
            return relatorioTransacoes(entidade);
        } else if (relatorio.getNome().toUpperCase().equals("RELATORIOSITUACAOESTOQUE")) {
            return relatorioSituacaoEstoque(entidade);
        } else if (relatorio.getNome().toUpperCase().equals("RELATORIOINICIAL")) {
            return relatorioInicial();
        } else if (relatorio.getNome().toUpperCase().equals("RELATORIOTRANSACOESPRODUTO")) {
            return relatorioTransacoesProduto(entidade);
        } else if (relatorio.getNome().toUpperCase().equals("RELATORIODETALHEINICIAL")) {
            return relatorioDetalheInicial();
        } else if (relatorio.getNome().toUpperCase().equals("RELATORIODINAMICO")) {
            return relatorioDinamico(entidade);
        }
        return null;

    }

    public List<EntidadeDominio> relatorioTransacoes(EntidadeDominio entidade) {
        PreparedStatement pst = null;

        EntidadeRelatorio relTransPeriodo = (EntidadeRelatorio) entidade;
        String sql = null;

        if (relTransPeriodo.getTransacao().getProduto().getFornecedor().getId() == null
                && relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() == null
                && relTransPeriodo.getTransacao().getProduto().getId() == null) {
            // Não tem Tipo, Nem Fornecedor, nem produto
            sql = "SELECT  transacao, "
                    + "sum(quantidade) AS 'quantidade', "
                    + "ADDDATE(LAST_DAY(SUBDATE(dt_transacao, INTERVAL 1 MONTH)),1) AS 'mes' "
                    + "FROM tb_transacao  "
                    + "WHERE dt_transacao BETWEEN ? AND ? "
                    + "GROUP BY transacao, "
                    + "month(dt_transacao) "
                    + "ORDER BY month(dt_transacao)";
        } 
        else if (relTransPeriodo.getTransacao().getProduto().getId() != null) {
            // TEM PRODUTO, 
            sql = "SELECT  t.transacao as 'transacao', "
                    + "sum(t.quantidade) AS 'quantidade',"
                    + "t.dt_transacao AS 'mes', "
                    + "dt_transacao "
                    + "FROM tb_transacao t  "
                    + "JOIN tb_produto p on(p.id_produto = t.id_produto) "
                    + "JOIN tb_tipodeproduto tp ON(p.id_tipodeproduto = tp.id_tipodeproduto) "
                    + "WHERE t.dt_transacao BETWEEN ? AND ?  "
                    + "AND p.id_produto = ? "
                    + "GROUP BY t.transacao, "
                    + "month(t.dt_transacao), "
                    + "tp.id_tipodeproduto "
                    + "ORDER BY month(t.dt_transacao),"
                    + "tp.id_tipodeproduto desc;	";
        }
        else if (relTransPeriodo.getTransacao().getProduto().getFornecedor().getId() != null
                && relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
            // Tem Tipo Tem Fornecedor
            sql = "SELECT  t.transacao as 'transacao', "
                    + "sum(t.quantidade) AS 'quantidade',"
                    + "t.dt_transacao AS 'mes', "
                    + "dt_transacao "
                    + "FROM tb_transacao t  "
                    + "JOIN tb_produto p on(p.id_produto = t.id_produto) "
                    + "JOIN tb_tipodeproduto tp ON(p.id_tipodeproduto = tp.id_tipodeproduto) "
                    + "WHERE t.dt_transacao BETWEEN ? AND ?  "
                    + "AND p.id_fornecedor = ?"
                    + "AND tp.id_tipodeproduto = ? "
                    + "GROUP BY t.transacao, "
                    + "month(t.dt_transacao), "
                    + "tp.id_tipodeproduto "
                    + "ORDER BY month(t.dt_transacao),"
                    + "tp.id_tipodeproduto desc;	";
        } 
        else if (relTransPeriodo.getTransacao().getProduto().getFornecedor().getId() != null
                && relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
            // Não Tem Tipo,  Tem Fornecedor
            sql = "SELECT  t.transacao as 'transacao', "
                    + "sum(t.quantidade) AS 'quantidade',"
                    + "t.dt_transacao AS 'mes', "
                    + "dt_transacao "
                    + "FROM tb_transacao t  "
                    + "JOIN tb_produto p on(p.id_produto = t.id_produto) "
                    + "JOIN tb_tipodeproduto tp ON(p.id_tipodeproduto = tp.id_tipodeproduto) "
                    + "WHERE t.dt_transacao BETWEEN ? AND ?  "
                    + "AND p.id_fornecedor = ? "
                    + "GROUP BY t.transacao, "
                    + "month(t.dt_transacao), "
                    + "tp.id_tipodeproduto "
                    + "ORDER BY month(t.dt_transacao),"
                    + "tp.id_tipodeproduto desc;	";
        }
        
                else if (relTransPeriodo.getTransacao().getProduto().getFornecedor().getId() == null
                && relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
            //  Tem Tipo, Não Tem Fornecedor
            sql = "SELECT  t.transacao as 'transacao', "
                    + "sum(t.quantidade) AS 'quantidade',"
                    + "t.dt_transacao AS 'mes', "
                    + "dt_transacao "
                    + "FROM tb_transacao t  "
                    + "JOIN tb_produto p on(p.id_produto = t.id_produto) "
                    + "JOIN tb_tipodeproduto tp ON(p.id_tipodeproduto = tp.id_tipodeproduto) "
                    + "WHERE t.dt_transacao BETWEEN ? AND ?  "
                    + "AND tp.id_tipodeproduto = ? "
                    + "GROUP BY t.transacao, "
                    + "month(t.dt_transacao), "
                    + "tp.id_tipodeproduto "
                    + "ORDER BY month(t.dt_transacao),"
                    + "tp.id_tipodeproduto desc;	";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            pst.setDate(1, new java.sql.Date(relTransPeriodo.getDtInicial().getTime()));
            pst.setDate(2, new java.sql.Date(relTransPeriodo.getDtFinal().getTime()));
            
            if(relTransPeriodo.getTransacao().getProduto().getId() != null){
            //Tem Produto
                 pst.setInt(3, relTransPeriodo.getTransacao().getProduto().getId());
            }
            
            else if (relTransPeriodo.getTransacao().getProduto().getFornecedor().getId() != null
                && relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                //Tem tipo Tem Fornecedor
                 pst.setInt(3, relTransPeriodo.getTransacao().getProduto().getFornecedor().getId());
                 pst.setInt(4, relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId());
            }
            else if (relTransPeriodo.getTransacao().getProduto().getFornecedor().getId() != null
                && relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
            // Não Tem Tipo,  Tem Fornecedor
                    pst.setInt(3, relTransPeriodo.getTransacao().getProduto().getFornecedor().getId());
            }
             else if (relTransPeriodo.getTransacao().getProduto().getFornecedor().getId() == null
                && relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
            //  Tem Tipo, Não Tem Fornecedor
                  pst.setInt(3, relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId());
             }
            
            
            
            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> relatorio = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                EntidadeRelatorio r = new EntidadeRelatorio();
                r.setTransacao(new Transacao());
                r.getTransacao().setTipoDeTransacao(rs.getString("transacao"));
                r.getTransacao().setQtdeDoTipo(rs.getInt("quantidade"));
                r.setMes(rs.getString("mes"));

                relatorio.add(r);
            }
            return relatorio;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EntidadeDominio> relatorioTransacoesProduto(EntidadeDominio entidade) {
        PreparedStatement pst = null;

        EntidadeRelatorio relTransProd = (EntidadeRelatorio) entidade;
        String sql = null;

        sql = "SELECT  t.id_produto as 'idprod', "
                + "p.nome as 'nomeprod', "
                + "t.transacao as 'transacao', "
                + "sum(t.quantidade) as 'quantidade', "
                + "dt_transacao as 'mes' "
                + "FROM tb_transacao t "
                + "JOIN tb_produto p USING(id_produto) "
                + "WHERE dt_transacao BETWEEN ? AND ? "
                + "GROUP BY t.id_produto, t.transacao, month(t.dt_transacao) "
                + "ORDER BY month(t.dt_transacao)";

        try {
            openConnection();
            pst = connection.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(relTransProd.getDtInicial().getTime()));
            pst.setDate(2, new java.sql.Date(relTransProd.getDtFinal().getTime()));

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> relatorio = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                EntidadeRelatorio r = new EntidadeRelatorio();

                r.setTransacao(new Transacao());
                r.getTransacao().setTipoDeTransacao(rs.getString("transacao"));
                r.getTransacao().setQtdeDoTipo(rs.getInt("quantidade"));
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

    public List<EntidadeDominio> relatorioInicial() {
        PreparedStatement pst = null;

        String sql = null;

        sql = "SELECT * "
                + "FROM tb_produto p "
                + "JOIN tb_tipodeproduto tp USING(id_tipodeproduto) "
                + "JOIN tb_fornecedor  f USING(id_fornecedor) "
                + "WHERE p.quantidade <= tp.qtdeMin OR p.quantidade = 0  "
                + "ORDER BY p.id_produto";

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

    public List<EntidadeDominio> relatorioDetalheInicial() {
        PreparedStatement pst = null;

        String sql = null;

        sql = "SELECT "
                + "(SELECT count(p.id_produto) FROM tb_produto p WHERE  p.quantidade = 0) zerado, "
                + "(SELECT count(p.id_produto) FROM tb_produto p "
                + "JOIN tb_tipodeproduto tp USING(id_tipodeproduto)  "
                + "WHERE  p.quantidade <= tp.qtdeMin  and p.quantidade != 0) critico,"
                + "(SELECT count(p.id_produto) FROM tb_produto p "
                + "JOIN tb_tipodeproduto tp USING(id_tipodeproduto) "
                + "WHERE p.quantidade > tp.qtdeMin  )  demais "
                + "FROM dual";

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
            while (rs.next()) {
                RelatorioDetalheEstoque p = new RelatorioDetalheEstoque();
                p.setQtdeCritico(rs.getInt("critico"));
                p.setQtdeZerado(rs.getInt("zerado"));
                p.setQtdeDiponivel(rs.getInt("demais"));
                produtos.add(p);
            }
            return produtos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<EntidadeDominio> relatorioSituacaoEstoque(EntidadeDominio entidade) {
        PreparedStatement pst = null;

        EntidadeRelatorio relTransPeriodo = (EntidadeRelatorio) entidade;
        String sql = null;

        if (relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() == 0) {
            sql = "SELECT sum(quantidade) as 'qtdeEstoque', "
                    + "sum(qtdeMax)as 'qtdeDiponivel', "
                    + "(sum(quantidade)/sum(qtdeMax))*100 as 'porcentagemOcupada' "
                    + "FROM tb_produto JOIN tb_tipodeproduto using(id_tipodeproduto) ";

        } else if (relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() != 0) {
            sql = "SELECT sum(quantidade) as 'qtdeEstoque', "
                    + "sum(qtdeMax)as 'qtdeDiponivel', "
                    + "(sum(quantidade)/sum(qtdeMax))*100 as 'porcentagemOcupada' "
                    + "FROM tb_produto JOIN tb_tipodeproduto using(id_tipodeproduto) "
                    + "WHERE id_tipodeproduto = ? ";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (relTransPeriodo.getTransacao().getProduto().getTipoDeProduto().getId() != 0) {
                pst.setInt(1, relTransPeriodo.getTransacao().getProduto().getId());
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> relatorio = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                RelatorioEstoque r = new RelatorioEstoque();

                r.setQtdeDiponivel(rs.getInt("qtdeDiponivel"));
                r.setQtdeEstoque(rs.getInt("QtdeEstoque"));
                r.setPorcentagemOcupada(rs.getFloat("PorcentagemOcupada"));

                relatorio.add(r);
            }
            return relatorio;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EntidadeDominio> relatorioDinamico(EntidadeDominio entidade) {
        PreparedStatement pst = null;

        RelatorioDinamico rel = (RelatorioDinamico) entidade;
        String sql = null;

        if (rel.getDtFinal() == null && rel.getDtFinal() == null) {
            // Tem Data
            if (rel.getTransacao().getProduto().getFornecedor().getId() == null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
                // Nao tem Tipo nem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t   "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            } else if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                //  Tem Tipo, Tem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t  JOIN tb_produto p ON(p.id_produto = t.id_produto) "
                        + "WHERE p.id_fornecedor = ? AND "
                        + "p.id_tipodeproduto = ? "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            } else if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
                // Não Tem Tipo, Tem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t  JOIN tb_produto p ON(p.id_produto = t.id_produto) "
                        + "WHERE p.id_fornecedor = ? "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            } else if (rel.getTransacao().getProduto().getFornecedor().getId() == null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                //  Tem Tipo,Não Tem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t  JOIN tb_produto p ON(p.id_produto = t.id_produto) "
                        + "WHERE p.id_tipodeproduto = ? "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            }

        } else if (rel.getDtFinal() != null && rel.getDtFinal() != null) {

            if (rel.getTransacao().getProduto().getFornecedor().getId() == null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
                // Nao tem Tipo nem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t  JOIN tb_produto p ON(p.id_produto = t.id_produto) "
                        + "WHERE dt_transacao BETWEEN ? AND ? "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            } else if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                //  Tem Tipo, Tem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t  JOIN tb_produto p ON(p.id_produto = t.id_produto) "
                        + "WHERE dt_transacao BETWEEN ? AND ? AND "
                        + "p.id_fornecedor = ? AND "
                        + "p.id_tipodeproduto = ? "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            } else if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
                // Não Tem Tipo, Tem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t  JOIN tb_produto p ON(p.id_produto = t.id_produto) "
                        + "WHERE dt_transacao BETWEEN ? AND ? AND "
                        + "p.id_fornecedor = ? "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            } else if (rel.getTransacao().getProduto().getFornecedor().getId() == null
                    && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                //  Tem Tipo,Não Tem Fornecedor
                sql = "SELECT  t.transacao,"
                        + "min(t.quantidade) AS 'minQuantidade',"
                        + "max(t.quantidade) AS 'maxQuantidade', "
                        + "sum(t.quantidade) AS 'Quantidade', "
                        + "avg(t.quantidade) AS 'avgQuantidade', "
                        + "t.dt_transacao AS 'Mes', "
                        + "t.dt_transacao "
                        + "FROM tb_transacao t  JOIN tb_produto p ON(p.id_produto = t.id_produto) "
                        + "WHERE dt_transacao BETWEEN ? AND ? AND "
                        + "p.id_tipodeproduto = ? "
                        + "GROUP BY t.transacao "
                        + "ORDER BY month(t.dt_transacao)desc";
            }

        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);
            if (rel.getDtFinal() == null && rel.getDtFinal() == null) {

                if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                        && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                    //Tem tipo, Tem fornecedor
                    pst.setInt(1, rel.getTransacao().getProduto().getFornecedor().getId());
                    pst.setInt(2, rel.getTransacao().getProduto().getTipoDeProduto().getId());
                } else if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                        && rel.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
                    //Nao tem Tipo, Tem fornecedor
                    pst.setInt(1, rel.getTransacao().getProduto().getFornecedor().getId());

                } else if (rel.getTransacao().getProduto().getFornecedor().getId() == null
                        && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                    //Tem tem Tipo,Não Tem fornecedor
                    pst.setInt(1, rel.getTransacao().getProduto().getTipoDeProduto().getId());

                }
            } else if (rel.getDtFinal() != null && rel.getDtFinal() != null) {
                if (rel.getTransacao().getProduto().getFornecedor().getId() == null
                        && rel.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
                    //Tem tipo, Tem fornecedor
                    pst.setDate(1, new java.sql.Date(rel.getDtInicial().getTime()));
                    pst.setDate(2, new java.sql.Date(rel.getDtFinal().getTime()));

                } else if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                        && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                    //Tem tipo, Tem fornecedor
                    pst.setDate(1, new java.sql.Date(rel.getDtInicial().getTime()));
                    pst.setDate(2, new java.sql.Date(rel.getDtFinal().getTime()));
                    pst.setInt(3, rel.getTransacao().getProduto().getFornecedor().getId());
                    pst.setInt(4, rel.getTransacao().getProduto().getTipoDeProduto().getId());
                } else if (rel.getTransacao().getProduto().getFornecedor().getId() != null
                        && rel.getTransacao().getProduto().getTipoDeProduto().getId() == null) {
                    //Nao tem Tipo, Tem fornecedor
                    pst.setDate(1, new java.sql.Date(rel.getDtInicial().getTime()));
                    pst.setDate(2, new java.sql.Date(rel.getDtFinal().getTime()));
                    pst.setInt(3, rel.getTransacao().getProduto().getFornecedor().getId());

                } else if (rel.getTransacao().getProduto().getFornecedor().getId() == null
                        && rel.getTransacao().getProduto().getTipoDeProduto().getId() != null) {
                    //Tem tem Tipo,Não Tem fornecedor
                    pst.setDate(1, new java.sql.Date(rel.getDtInicial().getTime()));
                    pst.setDate(2, new java.sql.Date(rel.getDtFinal().getTime()));
                    pst.setInt(3, rel.getTransacao().getProduto().getTipoDeProduto().getId());

                }
            }

            ResultSet rs = pst.executeQuery();
            List<EntidadeDominio> relatorio = new ArrayList<EntidadeDominio>();

            while (rs.next()) {
                RelatorioDinamico r = new RelatorioDinamico();

                r.getMinTransacao().setQtdeDoTipo(rs.getInt("minQuantidade"));
                r.getMaxTransacao().setQtdeDoTipo(rs.getInt("maxQuantidade"));
                r.getTransacao().setQtdeDoTipo(rs.getInt("Quantidade"));
                r.getAvgTransacao().setQtdeDoTipo(rs.getInt("avgQuantidade"));
                r.getTransacao().setTipoDeTransacao(rs.getString("Transacao"));
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

}
