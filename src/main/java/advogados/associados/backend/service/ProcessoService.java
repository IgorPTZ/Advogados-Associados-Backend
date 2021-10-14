package advogados.associados.backend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advogados.associados.backend.model.Cliente;
import advogados.associados.backend.model.Processo;
import advogados.associados.backend.model.ProcessoCliente;
import advogados.associados.backend.model.ProcessoReu;
import advogados.associados.backend.model.Reu;
import advogados.associados.backend.repository.ProcessoClienteRepository;
import advogados.associados.backend.repository.ProcessoRepository;
import advogados.associados.backend.repository.ProcessoReuRepository;


@Service
public class ProcessoService {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	@Autowired
	private ProcessoClienteRepository processoClienteRepository;
	
	@Autowired
	private ProcessoReuRepository processoReuRepository;
	
	public Processo obterProcessoPorId(Long id) {
		
		Processo processo = processoRepository.findById(id).get();
		
		String nomeDosReus = "";
		
		for(int i = 0; i < processo.getReus().size() ; i++) {
			
			nomeDosReus += processo.getReus().get(i).getNome();
			
			if(i != (processo.getReus().size() - 1)) {
				
				nomeDosReus += ",";
			}
		}
		
		processo.setNomeDosReus(nomeDosReus);
		
		return processo;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Processo inserirProcesso(Processo processo) {
		
		Processo processoCriado = processoRepository.save(processo);
		
		for(Reu reu : processo.getReus()) {
			
			processoReuRepository.save(new ProcessoReu(processoCriado, reu));
		}
		
		for(Cliente cliente : processo.getClientes()) {
			
			processoClienteRepository.save(new ProcessoCliente(processoCriado, cliente));
		}
		
		return processoCriado;
	}
	
	public Processo editarProcesso(Processo processo) {
		
		return processoRepository.save(processo);
	}
	
	public void excluirProcessoPorId(Long id) {
		
		processoRepository.deleteById(id);
	}
}
