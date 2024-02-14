package br.com.filmesapi.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutorFilmeId implements Serializable {
	
	private static final long serialVersionUID = -5332423598071950748L;

	private Long idFilme;
	
	private Long idProdutor;
	
	public ProdutorFilmeId() {}
	
	public ProdutorFilmeId(Long idFilme, Long idProdutor) {
		this.idFilme = idFilme;
		this.idProdutor = idProdutor;
	}

	public Long getIdFilme() {
		return idFilme;
	}

	public Long getIdProdutor() {
		return idProdutor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		ProdutorFilmeId other = (ProdutorFilmeId) obj;
		return Objects.equals(idFilme, other.getIdFilme()) &&
				Objects.equals(idProdutor, other.getIdProdutor());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idFilme, idProdutor);
	}

}
