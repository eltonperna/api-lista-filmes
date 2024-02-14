package br.com.filmesapi.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.filmesapi.entity.Filme;
import br.com.filmesapi.entity.ProdutorFilme;

public class FilmeDTO {
	
	private Long id;
	
	private Integer ano;
	
	private String titulo;
	
	private List<String> produtores = new ArrayList<>();
	
	private Boolean vencedor;
	
	public FilmeDTO(Filme filme) {
		this.id = filme.getId();
		this.ano = filme.getAno();
		this.titulo = filme.getTitulo();
		this.vencedor = filme.getVencedor();
		
		for (ProdutorFilme mp : filme.getProdutores()) {
			this.produtores.add(mp.getProdutor().getNome());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<String> produtores) {
		this.produtores = produtores;
	}

	public Boolean getVencedor() {
		return vencedor;
	}

	public void setVencedor(Boolean vencedor) {
		this.vencedor = vencedor;
	}
	
}
