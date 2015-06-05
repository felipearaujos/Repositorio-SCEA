/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scea.web.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import scea.core.aplicacao.Resultado;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.EntidadeDominio;

@ApplicationScoped
@ManagedBean(name = "loginBean")
public class LoginBean extends EntidadeDominioBean{
    
    private int id;
    private String login;
    private String senha;
    private List<Acesso> todosAcessos = new ArrayList<Acesso>();
    
    public String verificarLogin() 
    {
        Acesso usuario = new Acesso();
        usuario.setLogin(getLogin());
        usuario.setSenha(getSenha());
        Resultado re = fachada.acessar(usuario);
        Acesso UsuarioRetornado = (Acesso)re.getEntidades().get(0);
        if(UsuarioRetornado.getId() != 0)
            setId(UsuarioRetornado.getId());
        else
        {
            UsuarioRetornado.setId(0);
            setId(UsuarioRetornado.getId());
        }
        if(UsuarioRetornado.isLoginCorreto)
        {
            String msg = redirecionar(UsuarioRetornado.isAdmin);
            return msg;
        }
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", re.getMsg());
            context.addMessage(null, mensagem);
            
        return "";
    }

    public String redirecionar(boolean admin)
    {
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true ); 
        session.setAttribute("login_user", this.login);
        session.setAttribute("id_user", this.id);
        if(admin)
            return "Inicial?faces-redirect=true";
        else
            return "indexPartial?faces-redirect=true";
    }

    public void Salvar()
    {
        Acesso usuario = new Acesso();
        usuario.setLogin(getLogin());
        usuario.setSenha(getSenha());
        Resultado re = fachada.salvar(usuario);
        if(re.getMsg() == null)
        {
            re.setMsg("Acesso Salvo com sucesso!");
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", re.getMsg());
        context.addMessage(null, mensagem);
    }
    
    public void Excluir()
    {
        Acesso usuario = new Acesso();
        usuario.setLogin(getLogin());
        usuario.setSenha(getSenha());
        Resultado re = fachada.excluir(usuario);
        if(re.getMsg() == null)
        {
            re.setMsg("Acesso Excluído com sucesso!");
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", re.getMsg());
        context.addMessage(null, mensagem);
    }
    
    public void Alterar()
    {
        Acesso usuario = new Acesso();
        usuario.setLogin(getLogin());
        usuario.setSenha(getSenha());
        Resultado re = fachada.alterar(usuario);
        if(re.getMsg() == null)
        {
            re.setMsg("Acesso Alterado com sucesso!");
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", re.getMsg());
        context.addMessage(null, mensagem);
    }
    
    public List<Acesso> consultar()
    {
        Acesso usuario = new Acesso();
        usuario.setLogin(login);
        Resultado r = fachada.consultar(usuario);
        List<EntidadeDominio> entidades = r.getEntidades();
        List<Acesso> usuariosBuscados = new ArrayList<Acesso>();
        for(EntidadeDominio e:entidades)
        {
            usuario = (Acesso)e;
            usuariosBuscados.add(usuario);
        }
        setTodosAcessos(usuariosBuscados);
        return getTodosAcessos();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Acesso> getTodosAcessos() {
        return todosAcessos;
    }
    
    public void setTodosAcessos(List<Acesso> todosAcessos) {
        this.todosAcessos = todosAcessos;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}