/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scea.core.impl.negocio;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import scea.core.aplicacao.Resultado;
import scea.core.impl.dao.ProdutoDAO;
import scea.core.impl.dao.TransacaoDAO;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.dominio.modelo.Transacao;

/**
 *
 * @author Felipe
 */
public class RealizarSaida implements IStrategy {

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        Resultado resultado = new Resultado();

        // declaracoes e castings
        Transacao t = (Transacao) entidade;

        ProdutoDAO produtoDAO = new ProdutoDAO();
			

        Produto produtoBanco =  (Produto) produtoDAO.consultar(t.getProduto()).get(0);
        

        
        produtoBanco.setQuantidade(produtoBanco.getQuantidade() - t.getQtdeDoTipo());

        produtoDAO.alterar(produtoBanco);
        
        resultado.setMsg(null);
        return resultado;

    }

}