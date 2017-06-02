package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.Gaosong;

@Mapper
public interface GaosongMapper {

	
	@Select({"<script>",
        "SELECT * from gaosong", 
        " order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	List<Gaosong> getBeanList(@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select("SELECT count(id) from gaosong")
	int getBeanAllCount();
	
	@Select({"<script>",
        "SELECT * from gaosong where stock=#{stock} or name=#{stock}", 
        "</script>"})
	List<Gaosong> getBeanStockList(@Param("stock") String stock);
	
	
}
