<html>

<#include "/comm/head.ftl">
<div class="container">
				<div class="row">
				<form action="${BASE_PATH}/shareholder/stock/${type}" method="post">
					<div class="col-sm-2 condition_type">
						<div class="form-group">
							<label class="control-label">股票</label>
							<input type="text"  name="stock" value="${stock}" class="form-control">
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="status">&nbsp;</label> <span
								class="input-group-btn">
								<button type="submit"  class="btn btn-primary">搜索</button>
							</span>
						</div>
					</div>
					</form>
				</div>
                <div class="ibox float-e-margins">
                    <div class="ibox-content" style="overflow-x:scroll;">
                   		<table class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default"
							data-page-size="15">
							<thead>
								<tr>
									<th class="text-center footable-visible footable-first-column">序号</th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/stock/${sortdest}/1">股票代码</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/name/${sortdest}/1">股票名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/holder/${sortdest}/1">股东名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/stype/${sortdest}/1">股东类型</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/rank/${sortdest}/1">股东排名</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/mun/${sortdest}/1">持股数量(万股)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/per/${sortdest}/1">持股占流通股比例(%)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/bdsum/${sortdest}/1">数量变化(万股)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/bdbl/${sortdest}/1">数量变化比例</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/bdtype/${sortdest}/1">持股变动</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/ltsz/${sortdest}/1">流通市值</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/rday/${sortdest}/1">报告期</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/list/${type}/pday/${sortdest}/1">公告日</a></th>
								</tr>
							</thead>
							<tbody>
								<#list list as bean>
								<tr class="text-center footable-even"
									style="display: table-row;">
									<td class="footable-visible">${bean_index+1}</td>
									<td class="footable-visible">${bean.stock}</td>
									<td class="footable-visible">${bean.name}</td>
									<td class="footable-visible">${bean.holder}</td>
									<td class="footable-visible">${bean.stype}</td>
									<td class="footable-visible">${bean.rank}</td>
									<td class="footable-visible">${bean.mun}</td>
									<td class="footable-visible">${bean.per}</td>
									<td class="footable-visible">${bean.bdsum}</td>
									<td class="footable-visible">${bean.bdbl}</td>
									<td class="footable-visible">${bean.bdtype}</td>
									<td class="footable-visible">${bean.ltsz}</td>
									<td class="footable-visible">${(bean.rday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${(bean.pday?string("yyyy-MM-dd"))!}</td>
								</tr>
								</#list>
							</tbody>
							<#include "/comm/pagination.ftl">
						</table>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
