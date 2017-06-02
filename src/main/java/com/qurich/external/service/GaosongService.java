package com.qurich.external.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.GaosongMapper;
import com.qurich.external.model.Gaosong;
import com.qurich.external.model.IssuanceNotice;
import com.qurich.external.utils.PageUtil;

@Service
public class GaosongService {
	
	private static Logger log = Logger.getLogger(GaosongService.class.getClass());

	@Autowired
    private GaosongMapper gaosongMapper;


	public String list(String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			List<Gaosong> list = gaosongMapper.getBeanList(field,sort,(page-1)*50, 50);
    		int totalRecord = gaosongMapper.getBeanAllCount();
    		PageUtil pageUtil = new PageUtil(50, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("gaosong/"+field+"/"+sort);
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
    		log.error("GaosongService.list异常",e);
    	}
    	return "gaosong/list";
	}

	public String stock(String stock, Model model, HttpServletRequest request) {
		try{
			List<Gaosong> list = gaosongMapper.getBeanStockList(stock);
    		model.addAttribute("list", list);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest", "desc");
		}catch(Exception e){
    		log.error("IssuanceService.report异常",e);
    	}
    	return "gaosong/list";
	}
	
	
	
}
