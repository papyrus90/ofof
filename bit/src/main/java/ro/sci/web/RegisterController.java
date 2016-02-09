package ro.sci.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.domain.User;
import ro.sci.domain.UserCreateForm;
import ro.sci.email.EmailSender;
import ro.sci.service.SqlService;
import scala.collection.mutable.StringBuilder;

@Controller
public class RegisterController {
	
	/*@Autowired
	private UserCreateFormValidator validator;*/
	
	@Autowired
	private SqlService service;
	
	@Autowired
	private EmailSender emailSender;
	
	/*public void initBinder(WebDataBinder binder){
		binder.addValidators(validator);
	}
	*/
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getUserCreatePage(){
		return new ModelAndView("bitwire_register","form",new UserCreateForm());
	}
	
	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public String handleUserCreatePage( @ModelAttribute("form") @Valid UserCreateForm form  ,BindingResult bindingResult){
		
		if (bindingResult.hasErrors()){
			return "bitwire_register";
		}
		try{
			
			service.create(form);
			//emailSender.send(form.getEmail(), "bitwire account registration", "activate account : " );
			return "redirect:/login";
		}catch (Exception e){
			bindingResult.addError(new ObjectError("form",e.getMessage()));
			return "bitwire_register";
		}
			
	}
		/*@RequestMapping("/activateUser/id = {id}")
	public String activateAccount(@RequestParam long id){
			User user = service.getUserById(id);
			
	}*/
}
