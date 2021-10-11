package advogados.associados.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import advogados.associados.backend.model.Cliente;
import advogados.associados.backend.model.Processo;
import advogados.associados.backend.repository.ClienteRepository;
import advogados.associados.backend.repository.ProcessoRepository;
import advogados.associados.backend.util.Utilitarios;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	public List<Cliente> obterClientes() {
		
		List<Cliente> clientes = clienteRepository.findAll();
		
		for(Cliente cliente : clientes) {
			
			cliente.setProcessos(processoRepository.obterProcessosPorCliente(cliente.getId()));
		}
		
		return clientes;
	}
	
	public Page<Cliente> obterClientesPaginados(Integer pagina, Integer tamanho) {

		Page<Cliente> clientes = clienteRepository.obterClientesPaginados(PageRequest.of(pagina, tamanho));
		
		for(Cliente cliente : clientes.getContent()) {
			
			cliente.setProcessos(processoRepository.obterProcessosPorCliente(cliente.getId()));
		}
		
		return clientes;
	}
	
	public Page<Cliente> obterClientesPaginados(Integer pagina, Integer tamanho, String stringDePesquisa) {
		
		Page<Cliente> clientes = null;
		
		boolean pesquisarPorEmail = stringDePesquisa != null && !stringDePesquisa.isEmpty() && stringDePesquisa.contains("@");
		
		boolean pesquisarPorNumeroDoProcesso = stringDePesquisa!= null && !stringDePesquisa.isEmpty() && Utilitarios.isNumerico(stringDePesquisa);
		
		boolean pesquisarPorNome = stringDePesquisa != null && !stringDePesquisa.isEmpty();
		
		if(pesquisarPorEmail) {
			
			clientes =clienteRepository.obterClientesPaginadosPorEmail(PageRequest.of(pagina, tamanho), stringDePesquisa);
		}
		else if(pesquisarPorNumeroDoProcesso) {
			
			clientes = clienteRepository.obterClientesPaginadosPorNumeroDeProcesso(PageRequest.of(pagina, tamanho), stringDePesquisa);
		}
		else if(pesquisarPorNome) {
			
			clientes = clienteRepository.obterClientesPaginadosPorNome(PageRequest.of(pagina, tamanho), stringDePesquisa);
		}
		else {
			
			clientes = clienteRepository.obterClientesPaginados(PageRequest.of(pagina, tamanho));
		}
		
		for(Cliente cliente : clientes.getContent()) {
			
			cliente.setProcessos(processoRepository.obterProcessosPorCliente(cliente.getId()));
		}
		
		return clientes;
	}
	
	public Cliente obterClientePorId(Long id) {
		
		Cliente cliente = clienteRepository.findById(id).get();
		
		String nomeDosReus = "";
		
		cliente.setProcessos(processoRepository.obterProcessosPorCliente(cliente.getId()));
		
		for(Processo processo : cliente.getProcessos()) {
			
			for(int i = 0; i < processo.getReus().size() ; i++) {
				
				nomeDosReus += processo.getReus().get(i).getNome();
				
				if(i != (processo.getReus().size() - 1)) {
					
					nomeDosReus += ",";
				}
			}
			
			processo.setNomeDosReus(nomeDosReus);
			
			nomeDosReus = "";
		}
		
		return cliente;
	}
	
	public Cliente inserirCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
}
