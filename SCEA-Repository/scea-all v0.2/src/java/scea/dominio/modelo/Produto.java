package scea.dominio.modelo;

public class Produto extends EntidadeDominio{
	int quantidade;
	double valor;
        String nome;
        Fornecedor fornecedor;
	TipoDeProduto tipoDeProduto;
	
	
	public Produto() {
		tipoDeProduto = new TipoDeProduto();
                fornecedor = new Fornecedor();
		tipoDeProduto.setTipo("");
                //this.nome = "";
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public TipoDeProduto getTipoDeProduto() {
		return tipoDeProduto;
	}
	public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}
}
