package br.com.filmesapi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="PRODUTOR")
public class Produtor {

	@Id
	@Column(name="ID_PRODUTOR")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="NOME", length=50, nullable=false)
	private String nome;

	@OneToMany(mappedBy="produtor", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<ProdutorFilme> filmes = new ArrayList<>();

	public Produtor() {}

	public Produtor(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProdutorFilme> getFilmes() {
		return filmes;
	}

	public void setMovies(List<ProdutorFilme> filmes) {
		this.filmes = filmes;
	}

	@Override
	public String toString() {
		return "Produtor: "+ getNome();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Produtor other = (Produtor) obj;
		return Objects.equals(id, other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

}
