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

import com.qurich.external.service.ShareholderService;

@Controller
@RequestMapping(value = "/shareholder")
public class ShareholderController {

private static Logger log = Logger.getLogger(ShareholderController.class.getClass());
	
	@Autowired
    private ShareholderService shareholderService;
	
	@RequestMapping(value="/list/{type}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report(
			@PathVariable("type")int type,
			HttpServletRequest request,
			Model model){
		return shareholderService.list(type,"pday","desc",1,model,request);
	}
	
	@RequestMapping(value="/list/{type}/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report2(@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			@PathVariable("type")int type,
			HttpServletRequest request,
			Model model){
		return shareholderService.list(type,field,sort,page,model,request);
	}
	

	@RequestMapping(value="/stock/{type}", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String stock2(
			@RequestParam(value="stock", required=false, defaultValue="")String stock,
			HttpServletRequest request,
			@PathVariable("type")int type,
			Model model){
		if(StringUtils.isBlank(stock)){
			return "redirect:/shareholder/list/type";
		}
		return shareholderService.stock(type,stock,model,request);
	}
	
	
	@RequestMapping(value="/calculate", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String calculate(
			HttpServletRequest request,
			Model model){
		return shareholderService.calculate("fltsz","desc",1,model,request);
	}
	
	@RequestMapping(value="/calculate/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String calculate2(@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return shareholderService.calculate(field,sort,page,model,request);
	}
	
	@RequestMapping(value="/calculate/stock", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String calculatestock(
			@RequestParam(value="stock", required=false, defaultValue="")String stock,
			HttpServletRequest request,
			Model model){
		if(StringUtils.isBlank(stock)){
			return "redirect:/shareholder/calculate";
		}
		return shareholderService.calculatestock(stock,model,request);
	}
	
	
}
