package advogados.associados.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import advogados.associados.backend.model.Usuario;
import advogados.associados.backend.repository.UsuarioRepository;



@Service
public class ImplementacaoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/* Consulta no banco de dados o usuario */
		
		Usuario usuario = usuarioRepository.obterUsuarioPeloLogin(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}
		
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
	}
	
	public void inserirAcessoPadrao(Long usuarioId) {
		
		/* 1) DESCROBRE O NOME DA CONSTRAINT QUE SERÁ REMOVIDA */
		String nomeDaConstraint = usuarioRepository.obterNomeDaConstraint();
		
		if(nomeDaConstraint != null) {
			
			/* 2) EXCLUI A CONSTRAINT */
			jdbcTemplate.execute("ALTER TABLE usuarios_role DROP CONSTRAINT " + nomeDaConstraint);
		}

		/* 3) INSERE O ACESSO PADRAO (ROLE_USER) PARA O CADASTRO DO NOVO USUARIO */
		usuarioRepository.inserirRolePadrao(usuarioId);
	}
}
