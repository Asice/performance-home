package com.qurich.external.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.MessageMapper;
import com.qurich.external.model.Message;
import com.qurich.external.utils.PageUtil;

@Service
public class MessageService {
	
	private static Logger log = Logger.getLogger(MessageService.class.getClass());

	@Autowired
    private MessageMapper messageMapper;
	
	//首页资讯
	public String list(int page,Model model){
    	try{
    		List<Message> list = messageMapper.getBeanList((page-1)*20, 20);
    		int totalRecord = messageMapper.getBeanAllCount(0);
    		//添加查看数
    		messageMapper.updateViewList(list);
    		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("message/list");
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    	}catch(Exception e){
    		log.error("MessageService.list异常",e);
    	}
    	return "message/list";
    }
	
	//公司利好
	public String listType(int status,String bull,int page,Model model){
    	try{
    		List<String> bulls=new ArrayList<String>();
    		if("null".equals(bull.toLowerCase())){
				bull="";
			}
    		if(StringUtils.isNotBlank(bull)){
    			String[] arr=bull.split(",");
    			String bullArray="";
    			for(String s:arr){
    				bullArray+=",'"+s+"'";
    				bulls.add(s);
    			}
    			model.addAttribute("bullArray", bullArray.replaceFirst(",", ""));
    		}else{
    			model.addAttribute("bullArray", "");
    		}
    		
    		List<Message> list = messageMapper.getBeanListType(bulls,status,3,(page-1)*30, 30);
    		int totalRecord = messageMapper.getBeanAllCountType(bulls,status,3);
    		if(list.size()>0)
    			messageMapper.updateViewList(list);
    		PageUtil pageUtil = new PageUtil(30, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("message/bull");
    		model.addAttribute("list", list);
    		model.addAttribute("status", status);
    		model.addAttribute("bulllist", bull);
    		model.addAttribute("showPage", pageUtil);
    	}catch(Exception e){
    		log.error("MessageService.listType异常",e);
    	}
    	return "message/bull";
    }

	public String pageOne(int id, Model model) {
		try{
			Message message=messageMapper.getBeanById(id);
    		model.addAttribute("message", message);
    	}catch(Exception e){
    		log.error("MessageService.pageOne异常",e);
    	}
    	return "message/one";
	}
	
}
