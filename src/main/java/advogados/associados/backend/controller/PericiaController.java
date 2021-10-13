package advogados.associados.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import advogados.associados.backend.model.Pericia;
import advogados.associados.backend.service.PericiaService;
import advogados.associados.backend.service.ProcessoService;


@RestController
@RequestMapping(value = "/pericia")
public class PericiaController {
	
	@Autowired
	private PericiaService periciaService;
	
	@Autowired
	private ProcessoService processoService;
	
	@RequestMapping(value="/obter", method=RequestMethod.GET) 
	public ResponseEntity<?> carregarNovaPericia(@RequestParam("id") Long id) {  // ok
		
		try {
			
			return new ResponseEntity<Pericia>(periciaService.obterPericiaPorId(id), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@RequestMapping(value="/inserir", method=RequestMethod.POST) 
	public ResponseEntity<?> inserirPericia(@RequestBody Pericia pericia) { // ok
		
		try {
			
			return new ResponseEntity<Pericia>(periciaService.inserirPericia(pericia), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar", method=RequestMethod.PUT) 
	public ResponseEntity<?> editarPericia(@RequestBody Pericia pericia) { // ok
		
		try {
			
			return new ResponseEntity<Pericia>(periciaService.editarPericia(pericia), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="excluir", method=RequestMethod.DELETE) 
	public ResponseEntity<?> excluirPericia(@RequestParam("id") Long id) { // ok
		
		try {
			
			periciaService.excluirPericia(id);
			
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
