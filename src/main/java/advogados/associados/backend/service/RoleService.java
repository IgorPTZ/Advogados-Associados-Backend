package advogados.associados.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advogados.associados.backend.model.Role;
import advogados.associados.backend.repository.RoleRepository;


@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> obterPerfis() {
		
		return roleRepository.findAll();
	}
}
