package advogados.associados.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import advogados.associados.backend.model.Usuario;



@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "SELECT * FROM usuario WHERE login = :login", nativeQuery = true)
	Usuario obterUsuarioPeloLogin(@Param("login") String login);
	
	@Transactional(readOnly=true)
	@Query(value =  "SELECT * FROM usuario ORDER BY nome", nativeQuery = true)
	Page<Usuario> obterUsuariosPaginados(Pageable pageable);
	
	/* Metodo para atualizar o token presente na tabela de usuario durante um novo login */
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update usuario set token = :token where login = :login")
	void atualizarToken(@Param("token") String token, @Param("login") String login);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value="INSERT INTO usuario_role (usuario_id, role_id) VALUES (:usuarioId, (SELECT id FROM role WHERE nome = 'ROLE_ADVOGADO'))")
	void inserirRolePadrao(@Param("usuarioId") Long usuarioId);
	
	@Query(nativeQuery = true, value = "SELECT constraint_name FROM information_schema.constraint_column_usage " + 
	" WHERE table_name = 'usuario_role' AND column_name = 'role_id' AND constraint_name <> 'unique_usuario_role';")
	String obterNomeDaConstraint();
}
