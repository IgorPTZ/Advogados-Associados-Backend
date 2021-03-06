package advogados.associados.backend.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import advogados.associados.backend.model.Audiencia;
import advogados.associados.backend.model.Cliente;
import advogados.associados.backend.model.NotificacaoDeAudienciaPorEmail;
import advogados.associados.backend.model.NotificacaoDePericiaPorEmail;
import advogados.associados.backend.model.Pericia;
import advogados.associados.backend.model.Processo;
import advogados.associados.backend.service.AudienciaService;
import advogados.associados.backend.service.ClienteService;
import advogados.associados.backend.service.EmailDeNotificacaoService;
import advogados.associados.backend.service.PericiaService;
import advogados.associados.backend.service.ProcessoService;

@RestController
@RequestMapping(value = "/email")
public class EmailController {
	
	@Autowired
	private EmailDeNotificacaoService emailDeNotificacaoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private PericiaService periciaService;
	
	@Autowired
	private AudienciaService audienciaService;
	
	
	@RequestMapping(value="/enviar-notificacao-de-audiencia", method=RequestMethod.POST) 
	public ResponseEntity<?> enviarNotificacaoDeAudiencia(@RequestBody NotificacaoDeAudienciaPorEmail notificacao) { // ok
		
		try {
			
			Cliente cliente = clienteService.obterClientePorId(notificacao.getClienteId());
			
			Processo processo = processoService.obterProcessoPorId(notificacao.getProcessoId());
			
			Audiencia audiencia = audienciaService.obterAudienciaPorId(notificacao.getAudienciaId());
			
			String dataDaAudiencia = audiencia.getDataDaAudiencia().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
			
			String conteudo = "Prezado(a)," + cliente.getNome() + ",\nsegue os dados para a sua audi??ncia:\n\n" + 
					          "Processo:" + processo.getNumero() + "\n" + 
			                  "Data:" + dataDaAudiencia + "\n" +
					          "Hor??rio:" + audiencia.getHorario() + "\n" +
					          "Local:" + audiencia.getEndereco() + "\n";
			                  
			if(notificacao.getObservacao() != null && !notificacao.getObservacao().isEmpty()) {
				conteudo += "Observa????o:" + notificacao.getObservacao() + "\n";
			}
			
			if(notificacao.getFlagTipoDeNotificacao() != null && notificacao.getFlagTipoDeNotificacao().compareTo(2L) == 0) {
				conteudo += "Favor comparecer acompanhado de 3 testemunhas \n";
			}
			                  
			conteudo += "Qualquer d??vida entrar em contato no n??mero:3435345345334" + "\n\n" +
					    "Atenciosamente," + "\nCosta Pinto & Martinelli - Advogados Associados.";
			
			emailDeNotificacaoService.enviarEmailDeRecuperacaoDeAcesso("Aviso de Audi??ncia", cliente.getEmail(), conteudo);
				
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("Falha no envio do email de notifica????o da audi??ncia. Entre em contato com o administrador do sistema", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/enviar-notificacao-de-pericia", method=RequestMethod.POST) 
	public ResponseEntity<?> enviarNotificacaoDePericia(@RequestBody NotificacaoDePericiaPorEmail notificacao) { // ok
		
		try {
			
			Cliente cliente = clienteService.obterClientePorId(notificacao.getClienteId());
			
			Processo processo = processoService.obterProcessoPorId(notificacao.getProcessoId());
			
			Pericia pericia = periciaService.obterPericiaPorId(notificacao.getPericiaId());
			
			String dataDaPericia = pericia.getDataDaPericia().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
			
			String conteudo = "Prezado(a)," + cliente.getNome() + ",\nsegue os dados para a sua per??cia:\n\n" + 
					          "Processo:" + processo.getNumero() + "\n" + 
			                  "Data:" + dataDaPericia + "\n" +
					          "Hor??rio:" + pericia.getHorario() + "\n" +
			                  "Per??to:" + pericia.getNomePerito() + "\n" +
					          "Endere??o:" + pericia.getEndereco() + "\n";
			                  
			if(notificacao.getObservacao() != null && !notificacao.getObservacao().isEmpty()) {
				conteudo += "Observa????o:" + notificacao.getObservacao() + "\n";
			}
			                  
			conteudo += "Qualquer d??vida entrar em contato no n??mero:" + pericia.getTelefone() + "\n\n" +
					    "Atenciosamente," + "\nCosta Pinto & Martinelli - Advogados Associados.";
			
			emailDeNotificacaoService.enviarEmailDeRecuperacaoDeAcesso("Aviso de Per??cia", cliente.getEmail(), conteudo);
				
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("Falha no envio do email de notifica????o da per??cia. Entre em contato com o administrador do sistema", HttpStatus.OK);
		}
	}
}
