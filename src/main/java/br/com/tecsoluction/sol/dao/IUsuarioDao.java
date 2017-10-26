package br.com.tecsoluction.sol.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tecsoluction.sol.entidade.Usuario;

@Repository("userRepository")
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	
//	@Query("SELECT p FROM Usuario p where p.username=")
    Usuario findByUsername(String username);
    
    

}
