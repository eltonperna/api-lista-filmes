package br.com.filmesapi.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProdutorMinMaxIntervaloPremiosDTO {
	
	private List<ProdutorPremiosDTO> min = new ArrayList<>();
	
	private List<ProdutorPremiosDTO> max = new ArrayList<>();
	
	public ProdutorMinMaxIntervaloPremiosDTO() {}
	
	public ProdutorMinMaxIntervaloPremiosDTO(LinkedList<ProdutorPremiosDTO> lista) {
		this.min.add(lista.getFirst());
		this.max.add(lista.getLast());
	}

	public List<ProdutorPremiosDTO> getMin() {
		return min;
	}

	public void setMin(List<ProdutorPremiosDTO> min) {
		this.min = min;
	}

	public List<ProdutorPremiosDTO> getMax() {
		return max;
	}

	public void setMax(List<ProdutorPremiosDTO> max) {
		this.max = max;
	}
	
	public void addMin(List<ProdutorPremiosDTO> min) {
		this.getMin().addAll(min);
	}
	
	public void addMax(List<ProdutorPremiosDTO> max) {
		this.getMax().addAll(max);
	}

}
