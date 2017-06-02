package com.qurich.external.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.EquityIpoMapper;
import com.qurich.external.mapper.EquityMapper;
import com.qurich.external.model.Equity;
import com.qurich.external.model.EquityIpo;
import com.qurich.external.utils.PageUtil;

@Service
public class EquityService {
	
	private static Logger log = Logger.getLogger(EquityService.class.getClass());

	@Autowired
    private EquityIpoMapper equityIpoMapper;
	@Autowired
    private EquityMapper equityMapper;
	
	public String list(String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			List<Equity> list = equityMapper.getBeanList(field,sort,(page-1)*50, 50);
    		int totalRecord = equityMapper.getBeanAllCount();
    		PageUtil pageUtil = new PageUtil(50, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("equity/"+field+"/"+sort);
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    		model.addAttribute("field", field);
    		model.addAttribute("sort", sort);
    		if("desc".equals(sort)){
    			model.addAttribute("sortdest", "asc");
    		}else{
    			model.addAttribute("sortdest", "desc");
    		}
    		
		}catch(Exception e){
    		log.error("EquityService.list异常",e);
    	}
    	return "equity/list";
	}
	public String stock(String stock, Model model, HttpServletRequest request) {
		try{
			List<Equity> list = equityMapper.getBeanStockList(stock);
    		model.addAttribute("list", list);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest", "desc");
		}catch(Exception e){
    		log.error("EquityService.stock异常",e);
    	}
    	return "equity/list";
	}
	
	
	public String ipoList(String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			List<EquityIpo> list = equityIpoMapper.getBeanList(field,sort,(page-1)*50, 50);
    		int totalRecord = equityIpoMapper.getBeanAllCount();
    		PageUtil pageUtil = new PageUtil(50, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("equity/ipo/"+field+"/"+sort);
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    		model.addAttribute("field", field);
    		model.addAttribute("sort", sort);
    		if("desc".equals(sort)){
    			model.addAttribute("sortdest", "asc");
    		}else{
    			model.addAttribute("sortdest", "desc");
    		}
    		
		}catch(Exception e){
    		log.error("EquityService.ipoList异常",e);
    	}
    	return "equity/ipo";
	}
	public String ipoStock(String stock, Model model, HttpServletRequest request) {
		try{
			List<EquityIpo> list = equityIpoMapper.getBeanStockList(stock);
    		model.addAttribute("list", list);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest", "desc");
		}catch(Exception e){
    		log.error("EquityService.ipoStock异常",e);
    	}
    	return "equity/ipo";
	}
	
	
	
}
