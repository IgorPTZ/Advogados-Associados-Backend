package advogados.associados.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advogados.associados.backend.model.Reu;
import advogados.associados.backend.repository.ReuRepository;


@Service
public class ReuService {

	@Autowired
	private ReuRepository reuRepository;
	
	public List<Reu> obterReus() {
		
		return reuRepository.findAll();
	}
}
