package br.com.tecsoluction.sol.servico;

import br.com.tecsoluction.sol.entidade.Usuario;

public interface IUsuarioServico {
	
	
	Usuario findByUsername(String username);

}
