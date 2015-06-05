/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.core.impl.negocio;

import java.sql.SQLException;
import java.util.List;
import scea.core.aplicacao.Resultado;

import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;
import scea.core.impl.dao.AcessoDAO;
import scea.core.interfaces.IStrategy;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import scea.core.aplicacao.EmailAplicacao;
public class EnviarEmail implements IStrategy{

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        
       EmailAplicacao emailEnviado = (EmailAplicacao) entidade;
       Resultado r = new Resultado();
       SimpleEmail email = new SimpleEmail();
       try{
            email.setDebug(true);  
            email.setHostName("smtp.gmail.com");  
            email.setAuthentication("sceaweb1","rodrigo123!");  
            email.setSSL(true);  
            email.addTo(emailEnviado.getDestinatario()); //pode ser qualquer um email  
            email.setFrom("sceaweb1@gmail.com"); //aqui necessita ser o email que voce fara a autenticacao  
            email.setSubject(emailEnviado.getAssunto());  
            email.setMsg(emailEnviado.getMensagem());  
            email.send();  
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
            r.setMsg("Email não enviado!");
        }

        return r;
    }
    
}
