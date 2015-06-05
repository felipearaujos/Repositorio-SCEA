
package scea.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import scea.core.aplicacao.Resultado;
import scea.dominio.modelo.Acesso;
import scea.dominio.modelo.Cidade;
import scea.dominio.modelo.Endereco;
import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Estado;
import scea.dominio.modelo.Fornecedor;
import scea.dominio.modelo.Telefone;

@RequestScoped
@ManagedBean(name = "fornecedorBean")
public class FornecedorBean extends EntidadeDominioBean{
    private Acesso usuario;
    private String Nome;
    private String NomeFantasia;
    private String rzSocial;
    private String Email;
    private String CNPJ;
    private String Rua;
    private String Numero;
    private String Complemento;
    private String CEP;
    private String Bairro;
    private String Cidade;
    private String Estado;
    private String telefoneNumero;
    private List<Fornecedor> todosFornecedores;
    
    
    public Fornecedor createFornecedorModel()
    {
        Fornecedor fornecedor = new Fornecedor();
        Telefone telefone = new Telefone();
        Cidade cidade = new Cidade();
        Estado estado = new Estado();
        Endereco endereco = new Endereco();
        if((getCNPJ()== null))
            setCNPJ("");
        fornecedor.setCNPJ(getCNPJ());
        fornecedor.setEmail(getEmail());
        fornecedor.setNome(getNome());
        fornecedor.setNomeFantasia(getNomeFantasia());
        fornecedor.setRazaoSocial(getRzSocial());
        telefone.setNumero(getTelefoneNumero());
        endereco.setBairro(getBairro());
        endereco.setCep(getCEP());
        endereco.setComplemento(getComplemento());
        endereco.setNumero(getNumero());
        endereco.setRua(getRua());
        cidade.setNome(getCidade());
        estado.setNome(getEstado());
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        fornecedor.setEndereco(endereco);
        fornecedor.setTelefone(telefone);
        
        return fornecedor;
    }
    
    public void Salvar()
    {

        Fornecedor fornecedor = this.createFornecedorModel();
        Resultado r = fachada.salvar(fornecedor);
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage mensagem = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "", r.getMsg());
            context.addMessage(null, mensagem);
    }
    
    public List<Fornecedor> consultar()
    {
        Resultado r = new Resultado();
        List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
        r = fachada.consultar(createFornecedorModel());
        entidades = r.getEntidades();
        List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
        for(EntidadeDominio e: entidades)
        {
            Fornecedor f = (Fornecedor)e;
            fornecedores.add(f);
        }
        setTodosFornecedores(fornecedores);
        return getTodosFornecedores();
    }
    
  
    
    
    
    public void Alterar()
    {
        Fornecedor fornecedor = this.createFornecedorModel();
        Resultado re = fachada.alterar(fornecedor);
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", re.getMsg());
        context.addMessage(null, mensagem);
    }
    
    
    public void Excluir()
    {
        Fornecedor fornecedor = this.createFornecedorModel();
        Resultado re = fachada.excluir(fornecedor);
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage mensagem = new FacesMessage(
        FacesMessage.SEVERITY_INFO, "", re.getMsg());
        context.addMessage(null, mensagem);
    }
    
    
    
    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the NomeFantasia
     */
    public String getNomeFantasia() {
        return NomeFantasia;
    }

    /**
     * @param NomeFantasia the NomeFantasia to set
     */
    public void setNomeFantasia(String NomeFantasia) {
        this.NomeFantasia = NomeFantasia;
    }

    /**
     * @return the rzSocial
     */
    public String getRzSocial() {
        return rzSocial;
    }

    /**
     * @param rzSocial the rzSocial to set
     */
    public void setRzSocial(String rzSocial) {
        this.rzSocial = rzSocial;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the CNPJ
     */
    public String getCNPJ() {
        return CNPJ;
    }

    /**
     * @param CNPJ the CNPJ to set
     */
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    /**
     * @return the Rua
     */
    public String getRua() {
        return Rua;
    }

    /**
     * @param Rua the Rua to set
     */
    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    /**
     * @return the Numero
     */
    public String getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    /**
     * @return the Complemento
     */
    public String getComplemento() {
        return Complemento;
    }

    /**
     * @param Complemento the Complemento to set
     */
    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    /**
     * @return the CEP
     */
    public String getCEP() {
        return CEP;
    }

    /**
     * @param CEP the CEP to set
     */
    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    /**
     * @return the Bairro
     */
    public String getBairro() {
        return Bairro;
    }

    /**
     * @param Bairro the Bairro to set
     */
    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    /**
     * @return the Cidade
     */
    public String getCidade() {
        return Cidade;
    }

    /**
     * @param Cidade the Cidade to set
     */
    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the telefoneNumero
     */
    public String getTelefoneNumero() {
        return telefoneNumero;
    }

    /**
     * @param telefoneNumero the telefoneNumero to set
     */
    public void setTelefoneNumero(String telefoneNumero) {
        this.telefoneNumero = telefoneNumero;
    }

    /**
     * @return the usuario
     */
    public Acesso getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Acesso usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the todosFornecedores
     */
    public List<Fornecedor> getTodosFornecedores() {
        return todosFornecedores;
    }

    /**
     * @param todosFornecedores the todosFornecedores to set
     */
    public void setTodosFornecedores(List<Fornecedor> todosFornecedores) {
        this.todosFornecedores = todosFornecedores;
    }
}
