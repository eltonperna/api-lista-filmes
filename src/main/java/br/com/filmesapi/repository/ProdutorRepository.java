package br.com.filmesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.filmesapi.entity.Produtor;

public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
	
	Produtor findByNome(String nome);
	
}
