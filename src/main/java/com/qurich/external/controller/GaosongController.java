package com.qurich.external.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qurich.external.service.GaosongService;

@Controller
@RequestMapping(value = "/gaosong")
public class GaosongController {
	
	private Logger logger = Logger.getLogger(GaosongController.class);
	
	@Autowired
	private GaosongService gaosongService;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report(
			HttpServletRequest request,
			Model model){
		return gaosongService.list("planday","desc",1,model,request);
	}
	
	@RequestMapping(value="/list/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report2(@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return gaosongService.list(field,sort,page,model,request);
	}
	

	@RequestMapping(value="/stock", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String stock2(
			@RequestParam(value="stock", required=false, defaultValue="")String stock,
			HttpServletRequest request,
			Model model){
		if(StringUtils.isBlank(stock)){
			return "redirect:/gaosong/list";
		}
		return gaosongService.stock(stock,model,request);
	}
	
	 
}
