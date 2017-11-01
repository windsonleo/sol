package br.com.tecsoluction.sol.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.tecsoluction.sol.entidade.Usuario;
import br.com.tecsoluction.sol.servico.imp.ServicoNotificacaoImpl;
import br.com.tecsoluction.sol.servico.imp.UsuarioServicoImpl;




/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	

	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	

	
	    private Usuario usuario;
	
	 

    @Autowired
    public LoginController() {

   
    }
	
	
    @ModelAttribute
    public void addAttributes(Model model) {

        
//    	model.addAttribute("sesao",session.getAttribute("NOTIFY_MSG_SESSION_KEY"));
//        Usuario usuario = new Usuario();
//  		usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//  		usuario = ususervice.findByUsername(usuario.getUsername()); 
  		model.addAttribute("usuario", new Usuario());



    }	
    
    @RequestMapping(value ={"/"}, method = RequestMethod.GET)
	public ModelAndView  LoginForm(HttpServletRequest request){
  	
//    	ModelAndView login = new ModelAndView("login");
//   	    login.addObject("usuario", new Usuario());
    	
//    	login.addObject("sesao", session.getAttribute("NOTIFY_MSG_SESSION_KEY"));
    	

  	return new ModelAndView("redirect:/login");	
  	
	}
    
    @RequestMapping(value ={"/login"}, method = RequestMethod.GET)
	public ModelAndView  LoginForm2(HttpServletRequest request){
  	
    	ModelAndView login = new ModelAndView("login");

  	return login;	
  	
	}
    
    
    @RequestMapping(value ={"/accessdenied"}, method = RequestMethod.GET)
	public ModelAndView  AcessDenied(HttpServletRequest request){
  	
    	ModelAndView accessdenied = new ModelAndView("/login/accessdenied");

  	return accessdenied;	
  	
	}
    
    
    
    @RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView  ErrorEx(){
  	
    	ModelAndView error = new ModelAndView("/login/error");
    	
		
  	return error;	
  	
	}
    
    
    @RequestMapping(value = "/erro", method = RequestMethod.GET)
	public ModelAndView  ErrorE(){
  	
    	ModelAndView error = new ModelAndView("/login/erro");
    	
		
  	return error;	
  	
	}

	
	
}
