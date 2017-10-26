package br.com.tecsoluction.sol.framework;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public abstract class AbstractController<Entity> {


    private final String entityAlias;
    
     protected abstract AbstractEntityService<Entity> getservice();
     
    

    public AbstractController(String entityAlias) {
        this.entityAlias = entityAlias;
       
    }



    @RequestMapping(value = "cadastro", method = RequestMethod.GET)
    public String cadastrarEntity() {



        return entityAlias + "/" + "cadastro";

    }

    @Transactional
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String AdicionarEntity( @ModelAttribute @Valid Entity entity,BindingResult result
			, RedirectAttributes attributes) {


    	if (result.hasErrors()) {
			
        
        System.out.println("erro ao add:"+ entityAlias+"erro:"+result.getObjectName());
//        attributes.addFlashAttribute("erros", "Erro ao Salvar."+result.getFieldError());
//        attributes.a
        
    	}else{
    		
    		getservice().save(entity);
            System.out.println("add:"+ entityAlias);
            attributes.addFlashAttribute("mensagem", "Sucesso ao Salvar.");
            attributes.addFlashAttribute("entity", entity.toString());




    	}
   
        return entityAlias + "/" + "cadastro" ;  //cadastroEntity;
       
    }


    @RequestMapping(value = "movimentacao", method = RequestMethod.GET)
    public String movimentacaoEntity() {

        ModelAndView movimentacao = new ModelAndView("movimentacao" + entityAlias);

        List<Entity> entityList = getservice().findAll();
        movimentacao.addObject(entityAlias + "List", entityList);

        return entityAlias+"/"+ "movimentacao";
    }

    @Transactional
    @RequestMapping(value = "editar", method = RequestMethod.GET)
    public String editarEntityForm(HttpServletRequest request) {

        Entity entity;
        long idf = Long.parseLong(request.getParameter("id"));
        entity = getservice().findOne(idf);



        return entityAlias+"/"+"editar";
    }

    @Transactional
    @RequestMapping(value = "edicao", method = RequestMethod.POST)
    public ModelAndView editarEntity(@ModelAttribute @Valid Entity entity,BindingResult result
	, RedirectAttributes attributes,HttpServletRequest request,HttpSession session){
    	
    	ModelAndView mv = new ModelAndView("home");
       
    	
    	
//    	Long idf = Long.parseLong(.;

    	if (result.hasErrors()) {
			
            
            System.out.println("erro ao Editar:"+ entityAlias+"erro:"+result.getObjectName());
            attributes.addFlashAttribute("mensagem", "Erro ao Salvar."+result.getFieldError());
//            attributes.a
            
            mv.addObject("mensagem", result.getFieldError());
            
            
        	}else{
        		
        		getservice().save(entity);
                System.out.println("add:"+ entityAlias);
                attributes.addFlashAttribute("mensagem", "Sucesso ao Editar.");
//                attributes.addFlashAttribute("entity", entity.toString());

                mv.addObject("mensagem", "Sucesso ao Editar");



        	}

//    	EntityService.save(entity);


//        return "redirect:/" + entityAlias + "/" + "home";
    	
    	return mv;
    }

    @Transactional
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deletarEntity(HttpServletRequest request ,RedirectAttributes attributes) {


        long idf = Long.parseLong(request.getParameter("id"));
//        Entity entity;
//        entity = EntityService.findOne(idf);
//		ModelAndView movimentacaocategoria = new ModelAndView("movimentacaocategoria");
        getservice().delete(idf);
        attributes.addFlashAttribute("mensagem", "Sucesso ao Deletar.");
//        attributes.addFlashAttribute("entity", entity.toString());



        return "redirect:/" + entityAlias + "/movimentacao";
    }


}
