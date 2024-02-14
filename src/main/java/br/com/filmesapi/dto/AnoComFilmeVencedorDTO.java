package br.com.filmesapi.dto;

public class AnoComFilmeVencedorDTO {
	
	private Integer ano;
	
	private Long qtVencedor;
	
	public AnoComFilmeVencedorDTO(Integer ano, Long qtVencedor) {
		this.ano = ano;
		this.qtVencedor = qtVencedor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Long getQtVencedor() {
		return qtVencedor;
	}

	public void setQtVencedor(Long qtVencedor) {
		this.qtVencedor = qtVencedor;
	}
	
}
