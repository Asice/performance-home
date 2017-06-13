package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.Shareholder;
import com.qurich.external.model.ShareholderPer;

@Mapper
public interface ShareholderMapper {
	

	@Select({"<script>",
        "SELECT * from shareholder where type=#{type} ", 
        " order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	List<Shareholder> getBeanList(@Param("type")int type,@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select("SELECT count(id) from shareholder where type=#{type}")
	int getBeanAllCount(@Param("type")int type);
	
	@Select({"<script>",
        "SELECT * from shareholder where type=#{type} and (stock=#{stock} or name=#{stock}) order by rank", 
        "</script>"})
	List<Shareholder> getBeanStockList(@Param("type")int type,@Param("stock") String stock);
	
	
	
	@Select({"<script>",
        "SELECT * from shareholder_per ", 
        " order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	List<ShareholderPer> getBeanPerList(@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select("SELECT count(id) from shareholder_per")
	int getBeanPerAllCount();
	
	@Select({"<script>",
        "SELECT * from shareholder_per where stock=#{stock} or name=#{stock}", 
        "</script>"})
	List<ShareholderPer> getBeanPerStockList(@Param("stock") String stock);
	
	
}
