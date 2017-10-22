package br.com.tecsoluction.sol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecsoluction.sol.entidade.Role;


@Repository("roleRepository")
public interface IRoleDao extends JpaRepository<Role, Long> {

//	Role findByRole(String role);

}
