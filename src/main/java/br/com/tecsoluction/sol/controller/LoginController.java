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
	
//    @Autowired
//    private ServicoNotificacaoImpl notificacaoService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private  UsuarioServicoImpl ususervice;

	
	private Usuario usuario;
	
	@Autowired 
	private ServletContext contexto = null;
	
	private String filenamef = null;
	
//	private HttpSession session;
	 
	    private static final String LOCATION = "C:/temp/"; // Temporary location where files will be stored
	 
	    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
	                                                        // Beyond that size spring will throw exception.
	    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
	     
	    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
	
	
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
//    	login.addObject("usuario", new Usuario());
    	
//    	login.addObject("sesao", session.getAttribute("NOTIFY_MSG_SESSION_KEY"));
    	

  	return new ModelAndView("redirect:login");	
  	
	}
    
    @RequestMapping(value ={"/login"}, method = RequestMethod.GET)
	public ModelAndView  LoginForm2(HttpServletRequest request){
  	
    	ModelAndView login = new ModelAndView("login");
//    	login.addObject("usuario", new Usuario());
    	
//    	login.addObject("sesao", session.getAttribute("NOTIFY_MSG_SESSION_KEY"));
    	

  	return login;	
  	
	}
    
    
    @RequestMapping(value ={"/accessdenied"}, method = RequestMethod.GET)
	public ModelAndView  AcessDenied(HttpServletRequest request){
  	
    	ModelAndView accessdenied = new ModelAndView("/login/accessdenied");
//    	login.addObject("usuario", new Usuario());
    	
//    	login.addObject("sesao", session.getAttribute("NOTIFY_MSG_SESSION_KEY"));
    	

  	return accessdenied;	
  	
	}
    
    

//    @RequestMapping(value = "/form_upload",method = RequestMethod.POST)
//    public String UploadAudio(@RequestParam("file") MultipartFile   file,HttpSession session,HttpServletRequest request,Model model) {
//    	
////    	this.usuario =  ususervice.findByUsername(usuario.getUsername());
//
//        if (this.usuario.getUsername() != usuario.getUsername()) {
//            notificacaoService.addErrorMessage(
//                    "nome não bate !");
//            return "login";
//        }
//
//        if ( this.usuario == null) {
//            notificacaoService.addErrorMessage("Login Invalido");
//            return "login";
//        }
//
//        // Login successful
//        notificacaoService.addInfoMessage("sucesso!");
    	
//    	
//    	long id = Long.parseLong(request.getParameter("usuarioid"));
//    	
//    	Usuario usuario = ususervice.findOne(id);
//    	
//    	
//    	ModelAndView home = new ModelAndView("home");
//    	
//    	home.addObject("usuario", usuario);
//
//
//    	String mensagem = "Sucesso ao salvar foto";
//    	String erros = "Falha ao salvar foto";
//
////    	funfa
////    	String path=session.getServletContext().getRealPath("/resources");
//
//    	String path=session.getServletContext().getRealPath("/WEB-INF/classes/static/audio/");
//    	
//    	
//
//    	
//    	
//        System.out.println("path:"+path);  
//
//
//    	
//    	String camfoto = null;
//    	
////    	String pathh = "\\static\\audio\\";
//    	//string pathh = file.get
//         this.filenamef=file.getOriginalFilename();  
//          
//
//    	
//    	
//    	camfoto = "audio\\"+filenamef;
//    	String camfoto2 = path+"\\"+filenamef;
//    	
//    	 System.out.println("camfoto2"+ camfoto2);
//    	 System.out.println("camfoto1"+ camfoto);
//
//    	 
//    	 String diretorio = null;
//    	 
//    	 File dir = new File(path);
//    	 
//    	 
//    	
//
//    	 if (!dir.exists()){
//             dir.mkdirs();
//    	 }
//    	 
//    	 
//        try{ 
//        	
//        byte barr[]=file.getBytes();  
//        
//      File serverFile = new File(dir.getAbsolutePath() + File.separator + filenamef);
//
//          
//        BufferedOutputStream bout=new BufferedOutputStream(  
//                 new FileOutputStream(serverFile));  
//        
////        BufferedOutputStream bout=new BufferedOutputStream(  
////                new FileOutputStream(pathh+"/"+filename));
//        
////        BufferedOutputStream bout=new BufferedOutputStream(  
////                new FileOutputStream(contexto.getResourceAsStream("/resources/static/audio/")+filename));
//        
//      
////        String rootPath = System.getProperty("C:\\Users\\teste\\Downloads");
//
//        
//        
//        bout.write(barr);  
//        bout.flush();  
//        bout.close();  
//        
//
//    	home.addObject("filename", serverFile.getAbsolutePath());
//    	home.addObject("filename2", "audio/"+filenamef);
//
//
//          
//        }catch(Exception e){
//        	
//        	System.out.println(e);
//        	
//        	home.addObject("filename2", "audio/"+filenamef);
//
//        } 
//        
//        return "redirect:/home" ;
//    }
//    
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ModelAndView  LoginEnvio(HttpServletRequest request){
//  	
//
//  	
//  	ModelAndView home = new ModelAndView("home");
//  	
//  	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	Usuario usuario = ususervice.findByUsername(auth.getName());
//	
//	
//	home.addObject("usuario", usuario);
//	
//		
//  	return home;	
//  	
//	}
    
    
    
//    @RequestMapping(value = "erros/erro", method = RequestMethod.GET)
//	public ModelAndView  Error(){
//  	
//    	ModelAndView error = new ModelAndView("erro");
//    	
//		
//  	return error;	
//  	
//	}
    
    
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
//	@SuppressWarnings("null")
//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	public ModelAndView  Home(HttpServletRequest request, HttpSession session){
//  	

  	
//  	ModelAndView home = new ModelAndView("home");
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
//			System.out.println(" Windson if logincontroller Usuario :"+ usuario.getUsername());
//		}else{
//			
//			session.setAttribute("usuario", usuario);
//		}
//		
//		System.out.println(" Windson fora if logincontroller Usuario :"+ usuario.getUsername());
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
//	home.addObject("filename2","audio/"+filenamef);
//	home.addObject("filenamef", filenamef);
//
//	
//		
//  	return home;	
//  	
//	}
	
	
}
