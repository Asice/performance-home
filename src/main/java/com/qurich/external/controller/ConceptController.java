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

import com.qurich.external.service.ConceptService;


@Controller
@RequestMapping(value = "/concept")
public class ConceptController {

	private static Logger log = Logger.getLogger(ConceptController.class.getClass());
	
	@Autowired
    private ConceptService conceptService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list1(
			HttpServletRequest request,
			Model model){
		return conceptService.list("stock","asc",1,model,request);
	}
	
	@RequestMapping(value="/stock", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String search(
			HttpServletRequest request,
			@RequestParam(value="concept", required=false, defaultValue="")String concept,
			@RequestParam(value="stock", required=false, defaultValue="")String stock,
			Model model){
		if(StringUtils.isBlank(concept)&&StringUtils.isBlank(stock)){
			return "redirect:/concept/list";
		}
		return conceptService.stock(concept,stock,model);
	}
	
	
	@RequestMapping(value="/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list2(
			@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return conceptService.list(field,sort,page,model,request);
	}
	
}
