package advogados.associados.backend.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(nullable = false, unique = true)
	private String numero;

	@ManyToOne
	private SituacaoDoProcesso situacao;

	@Column(nullable = false)
	private String observacao;
	
	@Transient
	private String nomeDosReus;
	
	@Transient
	private List<Reu> reus;

	@Transient
	private List<Cliente> clientes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public SituacaoDoProcesso getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoDoProcesso situacao) {
		this.situacao = situacao;
	}

	public List<Reu> getReus() {
		return reus;
	}

	public void setReus(List<Reu> reus) {
		this.reus = reus;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNomeDosReus() {
		return nomeDosReus;
	}

	public void setNomeDosReus(String nomeDosReus) {
		this.nomeDosReus = nomeDosReus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
