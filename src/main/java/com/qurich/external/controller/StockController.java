package com.qurich.external.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qurich.external.service.StockInfoService;


@Controller
@RequestMapping(value = "/s")
public class StockController {

	private static Logger log = Logger.getLogger(StockController.class.getClass());
	
	@Autowired
    private StockInfoService stockInfoService;
	
	@RequestMapping(value="/{stock}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String stockInfo(@PathVariable("stock")String stock,
			HttpServletRequest request,
			Model model){
		return stockInfoService.stockInfo(stock,model,request);
	}
	
	
}
