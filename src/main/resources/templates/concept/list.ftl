<html>

<#include "/comm/head.ftl">
<div class="container">
			<div class="row">
				<form action="${BASE_PATH}/concept/stock" method="post">
					<div class="col-sm-2 condition_type">
						<div class="form-group">
							<label class="control-label">股票</label>
							<input type="text"  name="stock" value="${stock}" class="form-control">
						</div>
					</div>
					<div class="col-sm-2 condition_type">
						<div class="form-group">
							<label class="control-label">概念</label>
							<input type="text"  name="concept" value="${concept}" class="form-control">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="status">&nbsp;</label> <span
								class="input-group-btn">
								<button type="submit" id="bsearch" class="btn btn-primary">搜索</button>
							</span>
						</div>
					</div>
					</form>
				</div>
                <div class="ibox float-e-margins">
                    <div class="ibox-content ">
                   		<table class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default"
							data-page-size="15">
							<thead>
								<tr>
									<th class="text-center footable-visible footable-first-column">序号</th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/concept/stock/${sortdest}/1">股票代码</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/concept/name/${sortdest}/1">股票名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/concept/price/${sortdest}/1">股价</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/concept/islead/${sortdest}/1">是否龙头</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/concept/cname/${sortdest}/1">相关概念</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/concept/relevance/${sortdest}/1">概念解析</a></th>
								</tr>
							</thead>
							<tbody>
								<#list list as bean>
								<tr class="text-center footable-even"
									style="display: table-row;">
									<td class="footable-visible">${bean_index+1}</td>
									<td class="footable-visible">${bean.stock}</td>
									<td class="footable-visible">${bean.name}</td>
									<td class="footable-visible">${bean.price}</td>
									<#if bean.islead=1>
										<td class="footable-visible ">是</td>
									<#else>	
										<td class="footable-visible ">否</td>
									</#if>
									<td class="footable-visible">${bean.cname}</td>
									<td class="footable-visible">${bean.relevance}</td>
								</tr>
								</#list>
							</tbody>
							<#include "/comm/pagination.ftl">
						</table>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
<script type="text/javascript">
	$(function(){
		$("#bsearch").click(function(){
			
		})
		
		
		
		
	});
</script>