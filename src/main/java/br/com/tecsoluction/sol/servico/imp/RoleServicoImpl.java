package br.com.tecsoluction.sol.servico.imp;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecsoluction.sol.dao.IRoleDao;
import br.com.tecsoluction.sol.entidade.Role;
import br.com.tecsoluction.sol.entidade.Usuario;
import br.com.tecsoluction.sol.framework.AbstractEntityService;
import br.com.tecsoluction.sol.servico.IRoleServico;



@Service
@Transactional
public class RoleServicoImpl extends AbstractEntityService<Role>implements IRoleServico {

	private 
	IRoleDao roledao;
	
//	private Entity entityClass;
	

	public RoleServicoImpl() {
		
		super(Role.class,"role");
	

		
		}

	@Override
	protected JpaRepository<Role, Long> getDao() {
		// TODO Auto-generated method stub
		return roledao;
	}



}
