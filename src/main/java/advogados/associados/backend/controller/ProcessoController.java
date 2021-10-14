package advogados.associados.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import advogados.associados.backend.model.Cliente;
import advogados.associados.backend.model.NotificacaoDeAudienciaPorEmail;
import advogados.associados.backend.model.NotificacaoDePericiaPorEmail;
import advogados.associados.backend.model.Processo;
import advogados.associados.backend.service.AudienciaService;
import advogados.associados.backend.service.ClienteService;
import advogados.associados.backend.service.PericiaService;
import advogados.associados.backend.service.ProcessoService;
import advogados.associados.backend.service.ReuService;
import advogados.associados.backend.service.SituacaoDoProcessoService;


@RestController
@RequestMapping(value = "/processo")
public class ProcessoController {
	
	private Long clienteId;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ReuService reuService;
	
	@Autowired
	private AudienciaService audienciaService;
	
	@Autowired
	private PericiaService periciaService;
		
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private SituacaoDoProcessoService situacaoDoProcessoService;
	
	@RequestMapping(value="/obter", method=RequestMethod.GET) 
	public ResponseEntity<?> obterProcesso(@RequestParam("id") Long id) { // ok
		
		try {
			
			return new ResponseEntity<Processo>(processoService.obterProcessoPorId(id), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/inserir", method=RequestMethod.POST) 
	public ResponseEntity<?> inserirProcesso(@RequestBody Processo processo) {
		
		try {
			
			return new ResponseEntity<Processo>(processoService.inserirProcesso(processo), HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-processo-por-id", method=RequestMethod.GET) 
	public ModelAndView editarProcessoPorId(ModelAndView model,
											@RequestParam("id") Long id,
											@RequestParam("clienteId") Long clienteId,
										    @RequestParam("page") Long page,
							                @RequestParam("size") Long size) { 
		
		try {
			
			this.clienteId = clienteId;
			
			Processo processo = processoService.obterProcessoPorId(id); 
			
			model.addObject("page", 0L);
			
			model.addObject("size", 20L);
			
			model.addObject("clienteId", clienteId);
			
			model.addObject("processo", processo);
			
			model.addObject("listaDeReus", reuService.obterReus());
			
			model.addObject("listaDeSituacoesDoProcesso", situacaoDoProcessoService.obterSituacoes());
			
			model.addObject("listaDeClientes", clienteService.obterClientes());
			
			model.setViewName("processo/editar-processo");
			
			return model;
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
		
	
	@RequestMapping(value="/excluir-processo-por-id", method=RequestMethod.GET) 
	public ModelAndView excluirProcessoPorId(ModelMap model,
											@RequestParam("id") Long id,
											@RequestParam("clienteId") Long clienteId,
										    @RequestParam("page") Long page,
							                @RequestParam("size") Long size) { 
		
		try {
			
			this.clienteId = clienteId;
			
			processoService.excluirProcessoPorId(id); 
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			model.addAttribute("id", clienteId);
			
			return new ModelAndView("redirect:/detalhar-cliente-por-id", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/editar-processo", method=RequestMethod.POST) 
	public ModelAndView editarProcesso(ModelMap model, @ModelAttribute Processo processo) {
		
		try {
						
			if(processo.getReus().isEmpty() || processo.getClientes().isEmpty() || processo.getSituacao().getId() == null) {
				
				model.addAttribute("clienteId", this.clienteId);
				
				model.addAttribute("mensagem", "Outra parte, clientes e situação são campos obrigatórios");
				
				model.addAttribute("page", 0L);
				
				model.addAttribute("size", 20L);
				
				model.addAttribute("processo", processo);
				
				model.addAttribute("listaDeReus", reuService.obterReus());
				
				model.addAttribute("listaDeSituacoesDoProcesso", situacaoDoProcessoService.obterSituacoes());
				
				model.addAttribute("listaDeClientes", clienteService.obterClientes());
				
				return new ModelAndView("processo/editar-processo", model);
			}

			Processo processoEditado = processoService.editarProcesso(processo);
			
			model.addAttribute("id", processoEditado.getId());
			
			model.addAttribute("clienteId", this.clienteId);
			
			model.addAttribute("page", 0L);
			
			model.addAttribute("size", 20L);
			
			return new ModelAndView("redirect:/detalhar-processo-por-id", model);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/carregar-aba-de-audiencia", method=RequestMethod.GET)
  	public ModelAndView carregarAbaDeAudiencia(ModelAndView model, 
  			                                   @RequestParam("processoId") Long processoId,
  			                                   @RequestParam("clienteId") Long clienteId) {
		
		Processo processo = processoService.obterProcessoPorId(processoId); 
		
		Cliente cliente = clienteService.obterClientePorId(clienteId);
		
		model.addObject("page", 0L);
		
		model.addObject("size", 20L);
		
		model.addObject("cliente", cliente);
		
		model.addObject("processo", processo);
		
		model.addObject("notificacaoDeAudiencia", new NotificacaoDeAudienciaPorEmail());
		
		model.addObject("listaDeAudiencias", audienciaService.obterAudienciasPorProcessoId(processoId));
 		
		model.setViewName("processo/fragmento-aba-audiencia :: aba-de-audiencias");
		
		return model;
  	}
	
	@RequestMapping(value="/carregar-aba-de-pericia", method=RequestMethod.GET)
  	public ModelAndView carregarAbaDePericia(ModelAndView model, 
  			                                   @RequestParam("processoId") Long processoId,
  			                                   @RequestParam("clienteId") Long clienteId) {
		
		Processo processo = processoService.obterProcessoPorId(processoId); 
		
		Cliente cliente = clienteService.obterClientePorId(clienteId);
		
		model.addObject("page", 0L);
		
		model.addObject("size", 20L);
		
		model.addObject("cliente", cliente);
		
		model.addObject("processo", processo);
		
		model.addObject("notificacaoDePericia", new NotificacaoDePericiaPorEmail());
		
		model.addObject("listaDePericias", periciaService.obterPericiasPorProcessoId(processo.getId()));
 		
		model.setViewName("processo/fragmento-aba-pericia :: aba-de-pericias");
		
		return model;
  	}
}
