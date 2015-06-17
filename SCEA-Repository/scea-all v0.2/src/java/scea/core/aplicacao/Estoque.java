package scea.core.aplicacao;

import scea.dominio.modelo.EntidadeDominio;
import scea.dominio.modelo.Produto;

public class Estoque extends EntidadeDominio{
	
	private Produto produto;
	boolean flgValida;// TRUE qtdeRestante
	private int qtdeFutura,
				qtdeTentativa,
				qtdeDisponivel,
                                qtdeAtual;
        private String status;
	private String obs;
	
	
	public int getQtdeTentativa() {
		return qtdeTentativa;
	}
	public void setQtdeTentativa(int qtdeSimulacao) {
		this.qtdeTentativa = qtdeSimulacao;
	}


	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}	
	
	public int getQtdeFutura() {
		return qtdeFutura;
	}
	
	public void setQtdeFutura(int qtdeFutura) {
		this.qtdeFutura = qtdeFutura;
	}

	
	public boolean isFlgValida() {
		return flgValida;
	}
	
	public void setFlgValida(boolean flgValida) {
		this.flgValida = flgValida;
	}
	public int getQtdeDisponivel() {
		return qtdeDisponivel;
	}
	public void setQtdeDisponivel(int qtdeDisponivel) {
		this.qtdeDisponivel = qtdeDisponivel;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the qtdeAtual
     */
    public int getQtdeAtual() {
        return qtdeAtual;
    }

    /**
     * @param qtdeAtual the qtdeAtual to set
     */
    public void setQtdeAtual(int qtdeAtual) {
        this.qtdeAtual = qtdeAtual;
    }



}
