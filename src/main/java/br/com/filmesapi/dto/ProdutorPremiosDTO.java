package br.com.filmesapi.dto;

public class ProdutorPremiosDTO {
	
	private String produtor;
	
	private Integer intervalo;
	
	private Integer anoVencAnterior;
	
	private Integer anoVencSeguinte;
	
	public ProdutorPremiosDTO() {}
	
	public ProdutorPremiosDTO(String produtor, Integer intervalo, Integer anoVencAnterior, Integer anoVencSeguinte) {
		this.produtor = produtor;
		this.intervalo = intervalo;
		this.anoVencAnterior = anoVencAnterior;
		this.anoVencSeguinte = anoVencSeguinte;
	}

	public void limpar() {
		this.produtor = "";
		this.intervalo = null;
		this.anoVencSeguinte = null;
		this.anoVencAnterior = null;
	}

	public String getProdutor() {
		return produtor;
	}

	public void setProdutor(String produtor) {
		this.produtor = produtor;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public Integer getAnoVencAnterior() {
		return anoVencAnterior;
	}

	public void setAnoVencAnterior(Integer anoVencAnterior) {
		this.anoVencAnterior = anoVencAnterior;
	}

	public Integer getanoVencSeguinte() {
		return anoVencSeguinte;
	}

	public void setAnoVencSeguinte(Integer anoVencSeguinte) {
		this.anoVencSeguinte = anoVencSeguinte;
	}
	
}
