package br.com.filmesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.filmesapi.entity.ProdutorFilme;
import br.com.filmesapi.entity.ProdutorFilmeId;

public interface ProdutorFilmeRepository extends JpaRepository<ProdutorFilme, ProdutorFilmeId> {
	
	@Query(value="select mp from ProdutorFilme as mp join mp.filme as filme join mp.produtor as produtor "
			+ "where filme.vencedor = true order by produtor.id, filme.ano")
	List<ProdutorFilme> findByFilmeVencedorOrderByProdutorId(Boolean isVencedor);
	
}
