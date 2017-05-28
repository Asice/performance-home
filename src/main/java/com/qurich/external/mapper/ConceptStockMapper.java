package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.Concept_stock;

@Mapper
public interface ConceptStockMapper {

	
	
	@Select({"select si.stock,si.`name`,si.`price`,c.`name` as cname ,cs.`relevance`,cs.`islead`",
        " from `stock_info`  si LEFT JOIN `concept_stock` cs on cs.`sid` =si.`id`", 
        " left JOIN `concept` c on c.`id` =cs.`cid`  order by ${field} ${sort} limit #{offset},#{rows}"})
	List<Concept_stock> getBeanList(@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);

	@Select({"<script>select si.stock,si.`name`,si.`price`,c.`name` as cname ,cs.`relevance`,cs.`islead`",
        " from `stock_info`  si LEFT JOIN `concept_stock` cs on cs.`sid` =si.`id`", 
        " left JOIN `concept` c on c.`id` =cs.`cid` where 1=1 "
        + "<if test=\"concept!=null and concept!=''\">and c.name = #{concept}</if>"
        + "<if test=\"stock!=null and stock!=''\">and (si.name = #{stock} or si.stock=#{stock})</if></script>"})
	List<Concept_stock> getBeanStockList(@Param("concept") String concept,@Param("stock") String stock);

	
	
	@Select({"select count(1)",
        " from `stock_info`  si LEFT JOIN `concept_stock` cs on cs.`sid` =si.`id`", 
        " left JOIN `concept` c on c.`id` =cs.`cid`"})
	int getBeanAllCount();
	
	
	
}
