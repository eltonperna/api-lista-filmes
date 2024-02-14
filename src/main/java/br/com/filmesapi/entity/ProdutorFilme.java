package br.com.filmesapi.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="PRODUTOR_FILME")
public class ProdutorFilme {
	
	@EmbeddedId
	private ProdutorFilmeId id;
	
	@ManyToOne
	@MapsId("idFilme")
	private Filme filme;
	
	@ManyToOne
	@MapsId("idProdutor")
	private Produtor produtor;
	
	public ProdutorFilme() {}
	
	public ProdutorFilme(Filme filme, Produtor produtor) {
		this.filme = filme;
		this.produtor = produtor;
		this.id = new ProdutorFilmeId(filme.getId(), produtor.getId());
	}

	public ProdutorFilmeId getId() {
		return id;
	}

	public void setId(ProdutorFilmeId id) {
		this.id = id;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setMovie(Filme movie) {
		this.filme = filme;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		ProdutorFilme other = (ProdutorFilme) obj;
		return Objects.equals(filme, other.getFilme()) &&
				Objects.equals(produtor, other.getProdutor());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(filme, produtor);
	}

}
