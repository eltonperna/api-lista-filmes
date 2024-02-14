package br.com.filmesapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="FILME")
public class Filme {
	
	@Id
	@Getter @Setter
	@Column(name="ID_FILME")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Getter @Setter
	@Column(name="ANO", nullable=false)
	private Integer ano;

	@Getter @Setter
	@Column(name="TITULO", nullable=false)
	private String titulo;

	@Getter @Setter
	@Column(name="FG_VENCEDOR", nullable=false)
	private Boolean vencedor;

	@OneToMany(mappedBy="filme", cascade=CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private Set<ProdutorFilme> produtores = new HashSet<>();
	
	public Filme() {}
	
	public Filme(Integer ano, String titulo, String vencedor) {
		this.ano = ano;
		this.titulo = titulo;
		this.vencedor = (vencedor != null && "yes".equalsIgnoreCase(vencedor)) ;
	}

	public Set<ProdutorFilme> getProdutores() {
		return produtores;
	}

	public void setProdutores(Set<ProdutorFilme> producers) {
		this.produtores = producers;
	}
	
	@Override
	public String toString() {
		return "Ano: "+ getAno() + " - Titulo: "+ getTitulo() + " - Vencedor: "+ getVencedor();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Filme other = (Filme) obj;
		return Objects.equals(id, other.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, ano);
	}
}
