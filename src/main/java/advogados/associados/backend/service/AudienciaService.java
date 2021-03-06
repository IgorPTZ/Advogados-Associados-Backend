package advogados.associados.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advogados.associados.backend.model.Audiencia;
import advogados.associados.backend.repository.AudienciaRepository;


@Service
public class AudienciaService {
	
	@Autowired
	private AudienciaRepository audienciaRepository;
	
	public Audiencia obterAudienciaPorId(Long id) {
		
		return audienciaRepository.findById(id).get();
	}
	
	public List<Audiencia> obterAudienciasPorProcessoId(Long id) {
		
		return audienciaRepository.obterAudienciasPorProcessoId(id);
	}
	
	public Audiencia inserirAudiencia(Audiencia audiencia) {
		
		return audienciaRepository.save(audiencia);
	}
	
	public Audiencia editarAudiencia(Audiencia audiencia) {
		
		if(audiencia.getFlagPrecatoria() == false) {
			
			audiencia.setNumeroDoProcessoGerado(null);
			
			audiencia.setTestemunhas(null);
		}
		
		return audienciaRepository.save(audiencia);
	}
	
	public void excluirAudiencia(Long id) {
		
		audienciaRepository.deleteById(id);
	}
}
