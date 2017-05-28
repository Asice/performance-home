package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.Issuance;

@Mapper
public interface IssuanceMapper {

	@Select({"<script>",
        "SELECT * from issuance", 
        " order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	List<Issuance> getBeanList(@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select("SELECT count(id) from issuance")
	int getBeanAllCount();
	
	@Select({"<script>",
        "SELECT * from issuance where stock=#{stock} or name=#{stock}", 
        "</script>"})
	List<Issuance> getBeanStockList(@Param("stock") String stock);
	

}
