package com.qurich.external.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qurich.external.service.EquityService;

@Controller
@RequestMapping(value = "/equity")
public class EquityController {
	
	private Logger logger = Logger.getLogger(EquityController.class);
	
	@Autowired
	private EquityService equityService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list(
			HttpServletRequest request,
			Model model){
		return equityService.list("pday","desc",1,model,request);
	}
	
	@RequestMapping(value="/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list2(@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return equityService.list(field,sort,page,model,request);
	}
	
	@RequestMapping(value="/ipo", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report(
			HttpServletRequest request,
			Model model){
		return equityService.ipoList("percent","desc",1,model,request);
	}
	
	@RequestMapping(value="/ipo/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report2(@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return equityService.ipoList(field,sort,page,model,request);
	}
	 
}
