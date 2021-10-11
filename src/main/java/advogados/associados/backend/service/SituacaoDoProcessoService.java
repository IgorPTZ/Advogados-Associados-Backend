package advogados.associados.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advogados.associados.backend.model.SituacaoDoProcesso;
import advogados.associados.backend.repository.SituacaoDoProcessoRepository;


@Service
public class SituacaoDoProcessoService {
	
	@Autowired
	private SituacaoDoProcessoRepository situacaoDoProcessoRepository;
	
	public List<SituacaoDoProcesso> obterSituacoes() {
		
		return situacaoDoProcessoRepository.findAll();
	}
}
