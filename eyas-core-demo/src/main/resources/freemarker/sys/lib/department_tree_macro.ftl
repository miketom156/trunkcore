<#macro showDepartmentTreeTable list map rowTemplate>
	<#list list as department>
		<#include rowTemplate/>
		<#if map[department.id?string]??>
			<@showDepartmentTreeTable list=map[department.id?string] map=map rowTemplate=rowTemplate/>
		</#if>
	</#list>
</#macro>