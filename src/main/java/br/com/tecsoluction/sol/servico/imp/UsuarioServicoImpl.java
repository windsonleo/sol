package br.com.tecsoluction.sol.servico.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import br.com.tecsoluction.sol.dao.IUsuarioDao;
import br.com.tecsoluction.sol.entidade.Usuario;
import br.com.tecsoluction.sol.framework.AbstractEntityService;
import br.com.tecsoluction.sol.servico.IUsuarioServico;



@Service("userService")
public class UsuarioServicoImpl extends AbstractEntityService<Usuario> implements IUsuarioServico {
		
		
		
	    private IUsuarioDao dao;
	    


	public UsuarioServicoImpl() {
		
		super(Usuario.class, "usuario");

		
		}
	
	
	@Override
	public Usuario save(Usuario user) {
		
//		user.setRoles(new HashSet<>());
//		userRepository.save(user);
		dao.saveAndFlush(user);
		
		return user;
		
	}

	@Override
	public Usuario findByUsername(String username) {
		
		

		return dao.findByUsername(username);
	}


	@Override
	protected JpaRepository<Usuario, Long> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}



	

}
