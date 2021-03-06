package advogados.associados.backend.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(unique = true, nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	private String token = "";
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_role",
			  joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario"),
			  inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role"))			  
	private List<Role> roles;
	
	@Transient
	private String novaSenha;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public Collection<Role> getAuthorities() {
		
		return roles;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		
		return this.senha;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		
		return this.login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {

		return true;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
