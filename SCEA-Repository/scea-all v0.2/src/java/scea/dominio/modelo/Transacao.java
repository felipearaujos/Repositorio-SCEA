package scea.dominio.modelo;

import java.util.Date;

public class Transacao extends EntidadeDominio{
	protected String tipoDeTransacao;
	protected Acesso acesso;
	protected Date dtCadastro;
	protected Produto produto;
	protected int qtdeDoTipo =0;
        
        
        public Transacao(){
            produto = new Produto();
        }
        
        
	
	public String getTipoDeTransacao() {
		return tipoDeTransacao;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQtdeDoTipo() {
		return qtdeDoTipo;
	}
	public void setQtdeDoTipo(int qtdeDoTipo) {
		this.qtdeDoTipo = qtdeDoTipo;
	}
	public void setTipoDeTransacao(String tipoDeTransacao) {
		this.tipoDeTransacao = tipoDeTransacao;
	}
	public Acesso getAcesso() {
		return acesso;
	}
	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	
}
