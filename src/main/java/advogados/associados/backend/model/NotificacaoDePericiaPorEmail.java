package advogados.associados.backend.model;

import java.io.Serializable;

public class NotificacaoDePericiaPorEmail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long flagTipoDeNotificacao;
	
	private Long clienteId;
	
	private Long processoId;
	
	private Long periciaId;
	
	private String observacao;

	public Long getFlagTipoDeNotificacao() {
		return flagTipoDeNotificacao;
	}

	public void setFlagTipoDeNotificacao(Long flagTipoDeNotificacao) {
		this.flagTipoDeNotificacao = flagTipoDeNotificacao;
	}
	
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getProcessoId() {
		return processoId;
	}

	public void setProcessoId(Long processoId) {
		this.processoId = processoId;
	}

	public Long getPericiaId() {
		return periciaId;
	}

	public void setPericiaId(Long periciaId) {
		this.periciaId = periciaId;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
