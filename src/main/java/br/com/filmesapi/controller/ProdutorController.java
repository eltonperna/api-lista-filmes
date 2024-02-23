package br.com.filmesapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.filmesapi.dto.ProdutorMinMaxIntervaloPremiosDTO;
import br.com.filmesapi.service.ProdutorService;

@RestController
@RequestMapping("producer")
@Tag(name = "Produtores", description = "Endpoints de produtores dos filmes")
public class ProdutorController {
	
	Logger logger = LoggerFactory.getLogger(ProdutorController.class);
	
	@Autowired
	private ProdutorService produtorService;
	
	@GetMapping("intervalo-premios")
	public ResponseEntity<ProdutorMinMaxIntervaloPremiosDTO> getMaiorMenorIntervalo() {
		ProdutorMinMaxIntervaloPremiosDTO dto = new ProdutorMinMaxIntervaloPremiosDTO();

		dto = produtorService.getMaiorMenorIntervalor();

		HttpStatus status = HttpStatus.OK;
		if ( dto.getMax().isEmpty() && dto.getMin().isEmpty() ) {
			status = HttpStatus.NO_CONTENT;
		}
		
		return new ResponseEntity<ProdutorMinMaxIntervaloPremiosDTO>(dto, status);
	}

}
