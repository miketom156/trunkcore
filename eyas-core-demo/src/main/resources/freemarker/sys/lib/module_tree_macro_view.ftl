<#macro showModuleTreeView list map>
	<#list list?sort_by(["orderBy"]) as module>
		<li class="folder" id="${module.id}">${module.name}
			<#if map[module.id?string]??>
				<ul>
				<@showModuleTreeView list=map[module.id?string] map=map/>
				</ul>
			</#if>
		</li>
	</#list>
</#macro>