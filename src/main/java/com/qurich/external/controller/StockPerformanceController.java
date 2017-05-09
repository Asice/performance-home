package com.qurich.external.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qurich.external.service.StockNoticeService;
import com.qurich.external.service.StockPerformanceService;


@Controller
@RequestMapping(value = "/performance")
public class StockPerformanceController {

	private static Logger log = Logger.getLogger(StockPerformanceController.class.getClass());
	@Autowired
	private StockPerformanceService stockPerformanceService;
	
	@Autowired
	private StockNoticeService stockNoticeService;
	
	/**
	 * @param date 季度日期 20170331
	 * @param field
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/report/{date}/{field}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list1(@PathVariable("date")String date,
			@PathVariable("field")String field,
			HttpServletRequest request,
			Model model){
		return stockPerformanceService.list(date, field,"desc",1,model,request);
	}
	@RequestMapping(value="/report/{date}/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String list2(@PathVariable("date")String date,
			@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return stockPerformanceService.list(date, field,sort,page,model,request);
	}
	
	
	@RequestMapping(value="/notice/{date}/{field}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String noticeList(@PathVariable("date")String date,
			@PathVariable("field")String field,
			HttpServletRequest request,
			Model model){
		return stockNoticeService.list(date, field,"desc",1,model,request);
	}
	@RequestMapping(value="/notice/{date}/{field}/{sort}/{page}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String noticeList2(@PathVariable("date")String date,
			@PathVariable("field")String field,
			@PathVariable("sort")String sort,
			@PathVariable("page")int page,
			HttpServletRequest request,
			Model model){
		return stockNoticeService.list(date, field,sort,page,model,request);
	}
	
	@RequestMapping(value="/download/notice/{date}",produces = {"application/vnd.ms-excel;charset=UTF-8"})
	public String noticeDownload(@PathVariable("date")String date,
			HttpServletResponse response,
			Model model){
		return stockNoticeService.download(date,model,response);
	}
	
	@RequestMapping(value="/download/report/{date}",produces = {"application/vnd.ms-excel;charset=UTF-8"})
	public String download(@PathVariable("date")String date,
			HttpServletResponse response,
			Model model){
		return stockPerformanceService.download(date,model,response);
	}
	
}
