package com.qurich.external.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.ConceptStockMapper;
import com.qurich.external.model.Concept_stock;
import com.qurich.external.utils.PageUtil;

@Service
public class ConceptService {
	
	private static Logger log = Logger.getLogger(ConceptService.class.getClass());

	@Autowired
    private ConceptStockMapper conceptStockMapper;
	

	public String list(String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			
			List<Concept_stock> list = conceptStockMapper.getBeanList(field,sort,(page-1)*50, 50);
    		int totalRecord = conceptStockMapper.getBeanAllCount();
			
    		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("concept/"+field+"/"+sort);
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
    		log.error("ConceptService.list异常"+e);
    	}
    	return "concept/list";
	}


	public String stock(String concept, String stock,Model model) {
		try{
			
			List<Concept_stock> list = conceptStockMapper.getBeanStockList(concept,stock);
			
    		model.addAttribute("list", list);
    		model.addAttribute("concept", concept);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest","desc");
		}catch(Exception e){
    		log.error("ConceptService.stock异常"+e);
    	}
    	return "concept/list";
	}
	
}
