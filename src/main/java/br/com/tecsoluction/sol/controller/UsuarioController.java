package br.com.tecsoluction.sol.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.tecsoluction.sol.entidade.Usuario;
import br.com.tecsoluction.sol.framework.AbstractController;
import br.com.tecsoluction.sol.servico.imp.RoleServicoImpl;
import br.com.tecsoluction.sol.servico.imp.UsuarioServicoImpl;




/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "usuario/")
public class UsuarioController extends AbstractController<Usuario> {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
    private  UsuarioServicoImpl ususervice;

	@Autowired
    private
    RoleServicoImpl rdao;
	
	
    
    

    @Autowired
    public UsuarioController() {
        super("usuario");
//        this.ususervice = udao;
//        this.rdao=rdao;
        
    }
	
	
    @ModelAttribute
    public void addAttributes(Model model) {

        
        Usuario usuario = new Usuario();
  		usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
  		usuario = ususervice.findByUsername(usuario.getUsername()); 
  		model.addAttribute("usuarioAtt", usuario);



    }	
    
    
    
//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	public ModelAndView  Home(HttpServletRequest request){
//  	
//
//  	
//		ModelAndView home = new ModelAndView("home");
//  	
////  	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//  		Usuario usuario =  new Usuario();
//		usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//		
//		usuario = ususervice.findByUsername(usuario.getUsername());
//		
//		if(usuario.getUsername() == null){
//			
//			usuario.setUsername("Maria");
//			System.out.println(" Windson if usuariocontroller Usuario :"+ usuario.getUsername());
//		}
//		
//		System.out.println(" Windson fora if usuariocontroller Usuario :"+ usuario.getUsername());
//
//	
////	List<String> tesste = null ;
////	
////	String teste ="teste1";
////	String teste2 ="teste2";
////	String teste3 ="teste3";
////	
////	tesste.add(teste);
////	tesste.add(teste2);
////	tesste.add(teste3);
//
//
//	
//	home.addObject("usuario", usuario);
////	home.addObject("teste",tesste);
//	
//		
//  	return home;	
//  	
//	}

	
}
