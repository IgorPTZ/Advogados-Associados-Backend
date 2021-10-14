package advogados.associados.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "processo_reu")
public class ProcessoReu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "processo_id", referencedColumnName = "id")
	private Processo processo;
	
	@ManyToOne
    @JoinColumn(name = "reu_id", referencedColumnName = "id")
	private Reu reu;
	
	
	public ProcessoReu() {
		super();
	}

	public ProcessoReu(Processo processo, Reu reu) {
		super();
		this.processo = processo;
		this.reu = reu;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Reu getReu() {
		return reu;
	}

	public void setReu(Reu reu) {
		this.reu = reu;
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
		ProcessoReu other = (ProcessoReu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
