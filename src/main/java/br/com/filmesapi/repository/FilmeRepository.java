package br.com.filmesapi.repository;

import java.util.List;

import br.com.filmesapi.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.filmesapi.dto.AnoComFilmeVencedorDTO;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
	

	@Query(value="select new br.com.filmesapi.dto.AnoComFilmeVencedorDTO(filme.ano, count(filme.vencedor)) "
			+ "from Filme as filme where filme.vencedor=true group by filme.ano having count(filme.vencedor) > 1")
	List<AnoComFilmeVencedorDTO> findAnosComFilmeVencedor();
	
}
