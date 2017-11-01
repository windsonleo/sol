package br.com.tecsoluction.sol.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.tecsoluction.sol.entidade.Role;
import br.com.tecsoluction.sol.entidade.Usuario;
import br.com.tecsoluction.sol.framework.AbstractController;
import br.com.tecsoluction.sol.framework.AbstractEditor;
import br.com.tecsoluction.sol.framework.AbstractEntityService;
import br.com.tecsoluction.sol.servico.imp.RoleServicoImpl;
import br.com.tecsoluction.sol.servico.imp.UsuarioServicoImpl;




/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "usuario/")
public class UsuarioController extends AbstractController<Usuario> {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private  UsuarioServicoImpl userService;


    private
    RoleServicoImpl roleService;
	
	private Usuario usuario;
	
	private List<Role> roles;
	
	@Autowired 
	private ServletContext contexto = null;
	
	private String filenamef = null;
    
    

    @Autowired
    public UsuarioController(UsuarioServicoImpl usu,RoleServicoImpl rd) {
        super("usuario");
        this.userService = usu;
        this.roleService=rd;
        
    }
    
    
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Role.class, new AbstractEditor<Role>(this.roleService) {
        });
     


    }
	
	
    @ModelAttribute
    public void addAttributes(Model model) {

        
         Usuario usuariotemp = new Usuario();
         usuariotemp.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
  		this.usuario = userService.findByUsername(usuariotemp.getUsername()); 
  		
  		
//  		roles = this.usuario.getRoles();
  		
  		roles = roleService.findAll();
  		
  		model.addAttribute("usuario", usuario);
  		model.addAttribute("roles", roles);



    }	
    
    
    
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView  Home(HttpServletRequest request, HttpSession session){
  	

  	
		ModelAndView home = new ModelAndView("/usuario/home");
  	
//  	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  		Usuario usuario =  new Usuario();
		usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		usuario = userService.findByUsername(usuario.getUsername());
		
		if(usuario.getUsername() == null){
			
			usuario.setUsername("Maria");
			System.out.println(" Windson if usuariocontroller Usuario :"+ usuario.getUsername());
		}else{
			
			session.setAttribute("usuario", usuario);
		}
		
		System.out.println(" Windson fora if usuariocontroller Usuario :"+ usuario.getUsername());

	


		home.addObject("usuario", usuario);
		home.addObject("filename2","../audio/"+filenamef);
		home.addObject("filenamef", filenamef);
		
		
  	return home;	
  	
	}

    
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
  	public ModelAndView  profileUsuario(HttpServletRequest request,HttpSession session){
    	
    	
//    	long idf = Long.parseLong(request.getParameter("id"));
    	
    	ModelAndView profileusuario = new ModelAndView("/usuario/profile");
    	
    	
    	  this.usuario = userService.findOne(this.usuario.getId());
    	 

    	 profileusuario.addObject("usuario", session.getAttribute("usuario"));

  		
//  		return "redirect:/usuario/profile?id="+usuario.getId();
    	 
    	 return profileusuario;
  	}
    
    @RequestMapping(value = "/form_upload",method = RequestMethod.POST)
    public ModelAndView UploadAudio(@RequestParam("file") MultipartFile   file,HttpSession session,HttpServletRequest request,Model model) {
    	
//    	this.usuario =  ususervice.findByUsername(usuario.getUsername());
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
    	
    	
    	long id = Long.parseLong(request.getParameter("usuarioid"));
    	
    	Usuario usuario = userService.findOne(id);
    	
    	
    	ModelAndView home = new ModelAndView("home");
    	
    	home.addObject("usuario", usuario);


    	String mensagem = "Sucesso ao salvar foto";
    	String erros = "Falha ao salvar foto";

//    	funfa
//    	String path=session.getServletContext().getRealPath("/resources");

    	String path=session.getServletContext().getRealPath("/WEB-INF/classes/static/audio/");
    	
    	

    	
    	
        System.out.println("path:"+path);  


    	
    	String camfoto = null;
    	
//    	String pathh = "\\static\\audio\\";
    	//string pathh = file.get
         this.filenamef=file.getOriginalFilename();  
          

    	
    	
    	camfoto = "audio\\"+filenamef;
    	String camfoto2 = path+"\\"+filenamef;
    	
    	 System.out.println("camfoto2"+ camfoto2);
    	 System.out.println("camfoto1"+ camfoto);

    	 
    	 String diretorio = null;
    	 
    	 File dir = new File(path);
    	 
    	 
    	

    	 if (!dir.exists()){
             dir.mkdirs();
    	 }
    	 
    	 
        try{ 
        	
        byte barr[]=file.getBytes();  
        
      File serverFile = new File(dir.getAbsolutePath() + File.separator + filenamef);

          
        BufferedOutputStream bout=new BufferedOutputStream(  
                 new FileOutputStream(serverFile));  
        
//        BufferedOutputStream bout=new BufferedOutputStream(  
//                new FileOutputStream(pathh+"/"+filename));
        
//        BufferedOutputStream bout=new BufferedOutputStream(  
//                new FileOutputStream(contexto.getResourceAsStream("/resources/static/audio/")+filename));
        
      
//        String rootPath = System.getProperty("C:\\Users\\teste\\Downloads");

        
        
        bout.write(barr);  
        bout.flush();  
        bout.close();  
        

    	home.addObject("filename", serverFile.getAbsolutePath());
    	home.addObject("filename2", "audio/"+filenamef);


          
        }catch(Exception e){
        	
        	System.out.println(e);
        	
        	home.addObject("filename2", "audio/"+filenamef);

        } 
        
//        return Home(request, session);
        
        return new ModelAndView("redirect:/usuario/home");	    
        
    }


	@Override
	protected AbstractEntityService<Usuario> getservice() {
		// TODO Auto-generated method stub
		return userService;
	}

	
}
