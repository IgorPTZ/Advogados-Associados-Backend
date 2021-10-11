package advogados.associados.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import advogados.associados.backend.model.Audiencia;
import advogados.associados.backend.service.AudienciaService;
//import advogados.associados.backend.service.ProcessoService;


@RestController
@RequestMapping(value = "/audiencia")
public class AudienciaController {
	
	//@Autowired
	//private ProcessoService processoService;
	
	@Autowired
	private AudienciaService audienciaService;
	
	@RequestMapping(value="/obter", method=RequestMethod.GET) 
	public ResponseEntity<?> obter(@RequestParam(name = "id") Long id) { // ok
		
		try {

			return new ResponseEntity<Audiencia>(audienciaService.obterAudienciaPorId(id), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/obter-por-processo", method=RequestMethod.GET) 
	public ResponseEntity<?> obterPorProcessoId(@RequestParam(name = "processoId") Long processoId) { // ok
		
		try {

			return new ResponseEntity<List<Audiencia>>(audienciaService.obterAudienciasPorProcessoId(processoId), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir", method=RequestMethod.POST) 
	public ResponseEntity<?> inserir(@RequestBody Audiencia audiencia) { // ok
		
		try {

			return new ResponseEntity<Audiencia>(audienciaService.inserirAudiencia(audiencia), HttpStatus.OK); 
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	@RequestMapping(value="/editar", method=RequestMethod.PUT) 
	public ResponseEntity<?> editar(@RequestBody Audiencia audiencia) { // ok
		
		try {
			
			return new ResponseEntity<Audiencia>(audienciaService.editarAudiencia(audiencia), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/excluir", method=RequestMethod.DELETE) 
	public ResponseEntity<?> excluir(@RequestParam(name = "id") Long id) { // ok
		
		try {
			
			audienciaService.excluirAudiencia(id);

			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
