<#macro showModuleTreeTable list map operMap rowTemplate>
	<#list list?sort_by(["orderBy"]) as module>
		<#include rowTemplate/>
		<#if map[module.id?string]??>
			<@showModuleTreeTable list=map[module.id?string] map=map operMap=operMap rowTemplate=rowTemplate/>
		</#if>
		<#if operMap[module.id?string]??>
			<#list operMap[module.id?string]?sort_by(["url"]) as operate>
			<tr id="${module.id }_${operate.id}" pId="${module.id}">
				<td height="20"><span class='file'>${operate.name}</span></td>
				<td align="center">&nbsp;</td>
				<td align="center">
					<a href="javascript:setDisplayMenu(${operate.id});">
					<#if operate.displayFlag == 1>
						显示
					<#else>
						隐藏
					</#if>
					</a>
				</td>
				<td align="center"><span style="color:blue;">${operate.displayName}</span></td>
                <#if operate.displayFlag ==1>
                    <td align="center">${operate.url!""}&nbsp;</td>
                <#else>
                    <td align="center">&nbsp;</td>
                </#if>
				<td align="center">&nbsp;</td>
			</tr>
			</#list>
		</#if>
	</#list>
</#macro>