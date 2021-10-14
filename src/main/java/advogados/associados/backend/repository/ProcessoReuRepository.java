package advogados.associados.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import advogados.associados.backend.model.ProcessoReu;

@Repository
@Transactional
public interface ProcessoReuRepository extends JpaRepository<ProcessoReu, Long>{

}
