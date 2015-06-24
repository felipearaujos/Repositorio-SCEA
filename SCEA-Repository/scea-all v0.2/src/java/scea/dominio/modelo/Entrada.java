/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.dominio.modelo;

/**
 *
 * @author Felipe
 */
public class Entrada extends Transacao{
   
    
    
    public Entrada(){
            produto = new Produto();
            tipoDeTransacao = "ENTRADA";
            super.tipoDeTransacao = "ENTRADA";
    }

    /**
     * @return the tipoDeTransacao
     */
    
    
}
