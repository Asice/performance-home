<html>

<#include "/comm/head.ftl">
<div class="container">
				<div class="row">
				<form action="${BASE_PATH}/shareholder/calculate/stock" method="post">
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
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/calculate/stock/${sortdest}/1">股票代码</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/calculate/name/${sortdest}/1">股票名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/calculate/ltsz/${sortdest}/1">总流通市值(万元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/calculate/fltsz/${sortdest}/1">扣除第一(万元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/calculate/tltsz/${sortdest}/1">扣除前十(万元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/shareholder/calculate/per/${sortdest}/1">10大股东占比(%)</a></th>
								</tr>
							</thead>
							<tbody>
								<#list list as bean>
								<tr class="text-center footable-even"
									style="display: table-row;">
									<td class="footable-visible">${bean_index+1}</td>
									<td class="footable-visible">${bean.stock}</td>
									<td class="footable-visible">${bean.name}</td>
									<td class="footable-visible">${bean.ltsz}</td>
									<td class="footable-visible">${bean.fltsz}</td>
									<td class="footable-visible">${bean.tltsz}</td>
									<td class="footable-visible">${bean.per}</td>
								</tr>
								</#list>
							</tbody>
							<#include "/comm/pagination.ftl">
						</table>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
