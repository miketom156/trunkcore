<#macro showDepartmentTreeView list map>
	<#list list as department>
		<li class="folder" id="${department.id}">${department.name}
			<#if map[department.id?string]??>
				<ul>
				<@showDepartmentTreeView list=map[department.id?string] map=map/>
				</ul>
			</#if>
		</li>
	</#list>
</#macro>