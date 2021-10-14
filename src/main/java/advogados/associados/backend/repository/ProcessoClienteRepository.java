package advogados.associados.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import advogados.associados.backend.model.ProcessoCliente;

@Repository
@Transactional
public interface ProcessoClienteRepository extends JpaRepository<ProcessoCliente, Long>{

}
