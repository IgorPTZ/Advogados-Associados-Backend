package advogados.associados.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import advogados.associados.backend.model.Cliente;
import advogados.associados.backend.service.ClienteService;


@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/obter", method=RequestMethod.GET) 
	public ResponseEntity<?> obterClientePorId(@RequestParam(value="id") Long id) { // ok
		
		try {
			
			return new ResponseEntity<Cliente>(clienteService.obterClientePorId(id), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/obter-clientes", method=RequestMethod.GET)
	public ResponseEntity<?> obterClientes(@RequestParam(name = "pagina") Integer pagina, @RequestParam(name = "tamanho") Integer tamanho) { // ok
		
		try {
			
			return new ResponseEntity<Page<Cliente>>(clienteService.obterClientesPaginados(pagina, tamanho), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="/obter-clientes-filtrados", method=RequestMethod.GET) 
	public ResponseEntity<?> obterClientesFiltrados(@RequestParam(name = "pagina") Integer pagina, 
			                                        @RequestParam(name = "tamanho") Integer tamanho,
			                                        @RequestParam(name = "string-pesquisa") String stringDePesquisa) { // ok
		
		try {
			
			return new ResponseEntity<Page<Cliente>>(clienteService.obterClientesPaginados(pagina, tamanho, stringDePesquisa), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping(value="/inserir", method=RequestMethod.POST)
	public ResponseEntity<?> inserirCliente(@RequestBody Cliente cliente) { // ok
		
		try {
			
			boolean cpfECnpjNaoPreenchidos = (cliente.getCpf() == null && cliente.getCnpj() == null) || (cliente.getCpf().isEmpty() && cliente.getCnpj().isEmpty());
			
			if(cpfECnpjNaoPreenchidos) {
					
			   return new ResponseEntity<String>("CPF ou CNPJ devem ser preenchidos", HttpStatus.BAD_REQUEST);
			}
			
			if(cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
				
				cliente.setCpf(null);
			}
			
			if(cliente.getCnpj() == null || cliente.getCnpj().isEmpty()) {
				
				cliente.setCnpj(null);
			}
			
			return new ResponseEntity<Cliente>(clienteService.inserirCliente(cliente),HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar", method=RequestMethod.PUT)
	public ResponseEntity<?> editarCliente(@RequestBody Cliente cliente) { // ok
		
		try {
			
			if(cliente.getId() == null || cliente.getId() < 1) {
				
				return new ResponseEntity<String>("O id e obrigatorio", HttpStatus.BAD_REQUEST);
			}
			
			boolean cpfECnpjNaoPreenchidos = (cliente.getCpf() == null && cliente.getCnpj() == null) || (cliente.getCpf().isEmpty() && cliente.getCnpj().isEmpty());
			
			if(cpfECnpjNaoPreenchidos) {
					
			   return new ResponseEntity<String>("CPF ou CNPJ devem ser preenchidos", HttpStatus.BAD_REQUEST);
			}
			
			if(cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
				
				cliente.setCpf(null);
			}
			
			if(cliente.getCnpj() == null || cliente.getCnpj().isEmpty()) {
				
				cliente.setCnpj(null);
			}
			
			return new ResponseEntity<Cliente>(clienteService.editarCliente(cliente),HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
