<#macro showModuleTreeTable list map operMap userOperIdMap rowTemplate>
	<#list list?sort_by(["orderBy"]) as module>
		<#include rowTemplate/>
		<#if map[module.id?string]??>
			<@showModuleTreeTable list=map[module.id?string] map=map operMap=operMap userOperIdMap=userOperIdMap rowTemplate=rowTemplate/>
		</#if>
		<#if operMap[module.id?string]??>
			<#list operMap[module.id?string]?sort_by(["url"]) as operate>
			<tr id="${module.id }_${operate.id}" pId="${module.id}">
				<td height="20"><span class='file'>${operate.name}</span></td>
				<td align="center">&nbsp;</td>
				<td align="center"><input type="checkbox" name="operateIdList" value="${operate.id}" <#if userOperIdMap[operate.id?string]??>checked="checked"</#if>/></td>
				<td align="center">&nbsp;</td>
			</tr>
			</#list>
		</#if>
	</#list>
</#macro>