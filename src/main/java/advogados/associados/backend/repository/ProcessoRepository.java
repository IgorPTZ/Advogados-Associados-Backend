package advogados.associados.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import advogados.associados.backend.model.Processo;

@Repository
@Transactional
public interface ProcessoRepository extends JpaRepository<Processo, Long>{
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM processo p "
	        + " JOIN processo_cliente pc ON p.id = pc.processo_id "
			+ " WHERE pc.cliente_id = :clienteId", nativeQuery = true)
	List<Processo> obterProcessosPorCliente(@Param("clienteId") Long clienteId);
}
