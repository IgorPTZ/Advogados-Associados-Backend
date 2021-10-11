package advogados.associados.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import advogados.associados.backend.model.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

}
