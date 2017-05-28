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

import com.qurich.external.service.IssuanceService;

@Controller
@RequestMapping(value = "/issuance")
public class IssuanceController {
	
	private Logger logger = Logger.getLogger(IssuanceController.class);
	
	@Autowired
	private IssuanceService issuanceService;
	
	
	@RequestMapping(value="/report", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report(
			HttpServletRequest request,
			Model model){
		return issuanceService.report("pday","desc",1,model,request);
	}
	
	@RequestMapping(value="/report/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String report2(@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return issuanceService.report(field,sort,page,model,request);
	}
	
	
	@RequestMapping(value="/notice", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String notice(
			HttpServletRequest request,
			Model model){
		return issuanceService.notice("dshday","desc",1,model,request);
	}
	
	@RequestMapping(value="/notice/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String notice2(@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return issuanceService.notice(field,sort,page,model,request);
	}
	
	@RequestMapping(value="/report/stock", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String stock1(
			@RequestParam(value="stock", required=false, defaultValue="")String stock,
			HttpServletRequest request,
			Model model){
		if(StringUtils.isBlank(stock)){
			return "redirect:/issuance/report";
		}
		return issuanceService.stock(stock,model,request);
	}
	@RequestMapping(value="/notice/stock", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String stock2(
			@RequestParam(value="stock", required=false, defaultValue="")String stock,
			HttpServletRequest request,
			Model model){
		if(StringUtils.isBlank(stock)){
			return "redirect:/issuance/notice";
		}
		return issuanceService.stock2(stock,model,request);
	}
	
	 
}
