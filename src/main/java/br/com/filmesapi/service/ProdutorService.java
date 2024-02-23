package br.com.filmesapi.service;

import java.util.ArrayList;
import java.util.List;

import br.com.filmesapi.dto.ProdutorMinMaxIntervaloPremiosDTO;
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
		
		List<ProdutorPremiosDTO> min = findMenorIntervalo(mpList);
		List<ProdutorPremiosDTO> max = findMaiorIntervalo(mpList);
		
		ProdutorMinMaxIntervaloPremiosDTO dto = new ProdutorMinMaxIntervaloPremiosDTO();
		dto.addMin(min);
		dto.addMax(max);
		
		return dto;
	}

	private List<ProdutorPremiosDTO> findMenorIntervalo(List<ProdutorFilme> mpList) {
		List<ProdutorPremiosDTO> listMin = new ArrayList<>();
		Integer minIntervalo = Integer.MAX_VALUE;

		for ( int i = 0; i < mpList.size() - 1; i++ ) {
			ProdutorPremiosDTO min = new ProdutorPremiosDTO(null, null, null, null);

			for (int j = i + 1; j < mpList.size(); j++) {
				
				ProdutorFilme mpi = mpList.get(i);
				ProdutorFilme mpj = mpList.get(j);
				
				if (mpi.getProdutor().equals(mpj.getProdutor())) {
					Integer intervalo = Math.abs(mpi.getFilme().getAno()-mpj.getFilme().getAno());
					
					if (intervalo < minIntervalo) {
						if (!listMin.isEmpty()) {
							listMin.clear();
						}

						minIntervalo = intervalo;

						min.setIntervalo(intervalo);
						min.setProdutor(mpi.getProdutor().getNome());
						min.setAnoVencAnterior(mpi.getFilme().getAno());
						min.setAnoVencSeguinte(mpj.getFilme().getAno());

						listMin.add(min);

					} else if (intervalo.equals(minIntervalo)) {
						min.setIntervalo(intervalo);
						min.setProdutor(mpi.getProdutor().getNome());
						min.setAnoVencAnterior(mpi.getFilme().getAno());
						min.setAnoVencSeguinte(mpj.getFilme().getAno());

						listMin.add(min);
					}
				}
			}
		}
		
		return listMin;
	}
	
	private List<ProdutorPremiosDTO> findMaiorIntervalo(List<ProdutorFilme> mpList) {
		List<ProdutorPremiosDTO> listMax = new ArrayList<>();
		Integer maxIntervalo = Integer.MIN_VALUE;

		for ( int i = 0; i < mpList.size() - 1; i++ ) {
			ProdutorPremiosDTO max = new ProdutorPremiosDTO(null, Integer.MIN_VALUE, null, null);

			for (int j = i + 1; j < mpList.size(); j++) {
				
				ProdutorFilme mpi = mpList.get(i);
				ProdutorFilme mpj = mpList.get(j);
				
				if (mpi.getProdutor().equals(mpj.getProdutor())) {
					Integer intervalo = Math.abs(mpi.getFilme().getAno()-mpj.getFilme().getAno());
					
					if (intervalo > maxIntervalo) {
						if (!listMax.isEmpty())
							listMax.clear();

						maxIntervalo = intervalo;

						max.setIntervalo(intervalo);
						max.setProdutor(mpi.getProdutor().getNome());
						max.setAnoVencAnterior(mpi.getFilme().getAno());
						max.setAnoVencSeguinte(mpj.getFilme().getAno());

						listMax.add(max);
					} else if (intervalo.equals(maxIntervalo)) {
						max.setIntervalo(intervalo);
						max.setProdutor(mpi.getProdutor().getNome());
						max.setAnoVencAnterior(mpi.getFilme().getAno());
						max.setAnoVencSeguinte(mpj.getFilme().getAno());

						listMax.add(max);
					}
				}
			}
		}
		
		return listMax;
	}
	
}
