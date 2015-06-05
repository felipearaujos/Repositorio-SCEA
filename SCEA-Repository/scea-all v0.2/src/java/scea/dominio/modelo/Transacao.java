package scea.dominio.modelo;

import java.util.Date;

public class Transacao extends EntidadeDominio{
	private String tipoDeTransacao;
	private Acesso acesso;
	private Date dtCadastro;
	private Produto produto;
	private int qtdeDoTipo =0;
	
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
