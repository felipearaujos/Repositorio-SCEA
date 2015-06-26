package scea.core.impl.negocio.validadores;

import java.util.ArrayList;
import java.util.List;

import scea.core.aplicacao.Estoque;
import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.ProdutoDAO;

import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

public class ValidarLimiteEntrada implements IStrategy {

    public Resultado processar(EntidadeDominio entidade) {

        Resultado resultado = new Resultado();
        //Estoque entEntrada = new Estoque();
        Transacao transacao = (Transacao) entidade;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean flgExiste = false;

        Produto produtoBuscado = new Produto();
        resultado.setEntidades(produtoDAO.consultar(transacao.getProduto()));
        for (EntidadeDominio e : resultado.getEntidades()) {
            Produto produto = (Produto) e;
            if (produto.getId().equals(transacao.getProduto().getId())) {
                produtoBuscado = produto;
                flgExiste = true;
                break;
            }
        }
        if (!flgExiste) {
            resultado.setMsg("PRODUTO NÃO CADASTRADO");
            return resultado;
        }
        //Verifica os valores da quantidade
        if (transacao.getQtdeDoTipo() <= 0) {
            resultado.setMsg("TRANSACAO NÃO RESPEITA OS VALORES PERMITIDOS");

        } else if ((transacao.getQtdeDoTipo() + produtoBuscado.getQuantidade()) > produtoBuscado.getTipoDeProduto().getQtdeMax()) {
            resultado.setMsg("TENTATIVA DE ENTRADA NÃO RESPEITA OS LIMITES PERMITIDOS");
        } else {

            //RealizarEntrada rel = new RealizarEntrada();
            //resultado = rel.processar(transacao);
            Produto produtoBanco = (Produto) produtoDAO.consultar(transacao.getProduto()).get(0);
            produtoBanco.setQuantidade(produtoBanco.getQuantidade() + transacao.getQtdeDoTipo());
            
            resultado.setMsg(null);
        }

        return resultado;
    }

}
