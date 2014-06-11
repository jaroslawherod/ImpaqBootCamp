package boot.camp.springjsf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import boot.camp.springjsf.config.ConfigServiceException;
import boot.camp.springjsf.service.PeopleServiceException;

@Controller
public class ExceptionController {
	
	@ExceptionHandler(ConfigServiceException.class)
	public String configServiceError() {
		return null;
	}
	
	@ExceptionHandler(PeopleServiceException.class)
	public String peopleServiceError() {
		return null;
	}

}
