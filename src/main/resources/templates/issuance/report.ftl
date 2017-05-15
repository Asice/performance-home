<html>

<#include "/comm/head.ftl">
<div class="container">
                <div class="ibox float-e-margins">
                    <div class="ibox-content" style="overflow-x:scroll;">
                   		<table class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default"
							data-page-size="15">
							<thead>
								<tr>
									<th class="text-center footable-visible footable-first-column">序号</th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/stock/${sortdest}/1">股票代码</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/name/${sortdest}/1">股票名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/zfsl/${sortdest}/1">增发数量(万股)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/zgb/${sortdest}/1">增发前总股数(万股)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/zfzgb/${sortdest}/1">增发占股比</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/price/${sortdest}/1">发行价</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/mjze/${sortdest}/1">募资总额(万元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/nprice/${sortdest}/1">最新价</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/zyjl/${sortdest}/1">折溢价率</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/pday/${sortdest}/1">发行日期</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/ipday/${sortdest}/1">增发上市日期</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/report/lockt/${sortdest}/1">锁定期</a></th>
								</tr>
							</thead>
							<tbody>
								<#list list as bean>
								<tr class="text-center footable-even"
									style="display: table-row;">
									<td class="footable-visible">${bean_index+1}</td>
									<td class="footable-visible">${bean.stock}</td>
									<td class="footable-visible">${bean.name}</td>
									<td class="footable-visible">${bean.zfsl}</td>
									<td class="footable-visible">${bean.zgb}</td>
									<td class="footable-visible">${bean.zfzgb}</td>
									<td class="footable-visible">${bean.price}</td>
									<td class="footable-visible">${bean.mjze}</td>
									<td class="footable-visible">${bean.nprice}</td>
									<td class="footable-visible">${bean.zyjl}</td>
									<td class="footable-visible">${(bean.pday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${(bean.ipday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${bean.lockt}</td>
								</tr>
								</#list>
							</tbody>
							<#include "/comm/pagination.ftl">
						</table>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
