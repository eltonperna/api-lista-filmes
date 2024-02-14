package br.com.filmesapi.service;

import java.util.List;

import br.com.filmesapi.entity.Filme;
import br.com.filmesapi.entity.ProdutorFilme;
import br.com.filmesapi.entity.Produtor;
import br.com.filmesapi.repository.ProdutorFilmeRepository;
import br.com.filmesapi.repository.ProdutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.filmesapi.dto.ProdutorMinMaxIntervaloPremiosDTO;
import br.com.filmesapi.dto.ProdutorPremiosDTO;


@Service
public class ProdutorService {
	
	Logger logger = LoggerFactory.getLogger(ProdutorService.class);
	
	@Autowired
	private ProdutorRepository produtorRepository;
	
	@Autowired
	private ProdutorFilmeRepository produtorFilmeRepository;
	
	public void saveProdutores(Filme filme, String produtores) {
		for (String strProducer : produtores.split(",|\\ and ")) {
			Produtor produtor = new Produtor(strProducer.trim());
			
			Example<Produtor> example = Example.of(produtor);
			
			if (produtorRepository.exists(example)) {
				produtor = produtorRepository.findByNome(strProducer.trim());
			} else {
				produtor = produtorRepository.save(produtor);
			}
			
			produtorFilmeRepository.save(new ProdutorFilme(filme, produtor));
		}
	}
	
	public ProdutorMinMaxIntervaloPremiosDTO getMaiorMenorIntervalor() {
		List<ProdutorFilme> mpList = produtorFilmeRepository.findByFilmeVencedorOrderByProdutorId(true);
		
		ProdutorPremiosDTO min = findMenorIntervalo(mpList);
		ProdutorPremiosDTO max = findMaiorIntervalo(mpList);
		
		ProdutorMinMaxIntervaloPremiosDTO dto = new ProdutorMinMaxIntervaloPremiosDTO();
		dto.addMin(min);
		dto.addMax(max);
		
		return dto;
	}

	private ProdutorPremiosDTO findMenorIntervalo(List<ProdutorFilme> mpList) {
		ProdutorPremiosDTO min = new ProdutorPremiosDTO(null, Integer.MAX_VALUE, null, null);
		
		for ( int i = 0; i < mpList.size() - 1; i++ ) {
			
			for (int j = i + 1; j < mpList.size(); j++) {
				
				ProdutorFilme mpi = mpList.get(i);
				ProdutorFilme mpj = mpList.get(j);
				
				if (mpi.getProdutor().equals(mpj.getProdutor())) {
					Integer intervalo = Math.abs(mpi.getFilme().getAno()-mpj.getFilme().getAno());
					
					if (intervalo < min.getIntervalo()) {
						min.setIntervalo(intervalo);
						min.setProdutor(mpi.getProdutor().getNome());
						min.setAnoVencAnterior(mpi.getFilme().getAno());
						min.setAnoVencSeguinte(mpj.getFilme().getAno());
						
						break;
					}
				}
			}
		}
		
		return min;
	}
	
	private ProdutorPremiosDTO findMaiorIntervalo(List<ProdutorFilme> mpList) {
		ProdutorPremiosDTO max = new ProdutorPremiosDTO(null, Integer.MIN_VALUE, null, null);
		
		for ( int i = 0; i < mpList.size() - 1; i++ ) {
			
			for (int j = i + 1; j < mpList.size(); j++) {
				
				ProdutorFilme mpi = mpList.get(i);
				ProdutorFilme mpj = mpList.get(j);
				
				if (mpi.getProdutor().equals(mpj.getProdutor())) {
					Integer intervalo = Math.abs(mpi.getFilme().getAno()-mpj.getFilme().getAno());
					
					if (intervalo > max.getIntervalo()) {
						max.setIntervalo(intervalo);
						max.setProdutor(mpi.getProdutor().getNome());
						max.setAnoVencAnterior(mpi.getFilme().getAno());
						max.setAnoVencSeguinte(mpj.getFilme().getAno());
						
						break;
					}
				}
			}
		}
		
		return max;
	}
	
}
