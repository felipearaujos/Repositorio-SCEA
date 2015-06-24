package scea.core.impl.negocio.validadores;

import java.util.ArrayList;
import java.util.List;

import scea.core.aplicacao.Estoque;
import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.ProdutoDAO;
import scea.core.impl.negocio.RealizarSaida;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.TipoDeProduto;
import scea.dominio.modelo.Transacao;

public class ValidarLimiteSaida implements IStrategy {

    public Resultado processar(EntidadeDominio entidade) {
        Resultado resultado = new Resultado();
        Transacao transacao = (Transacao) entidade;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        boolean flgExiste = false;
        Produto produtoBuscado = new Produto();

        resultado.setEntidades(produtoDAO.consultar(transacao.getProduto()));

        //Verifica se produto existe
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
        } else if (produtoBuscado.getQuantidade() < transacao.getQtdeDoTipo()) {//Se qtd Solicitada for menor que a quantidade Existente
            resultado.setMsg("QUANTIDADE SOLICITADA MAIOR QUE QUANTIDADE EXISTENTE! ");
            //entEntrada.setObs(null);
            resultado.setRetorno(null);

        } else {

            resultado.setMsg(null);
            RealizarSaida rel = new RealizarSaida();
            resultado = rel.processar(transacao);
        }

        return resultado;
    }//Processar

}
