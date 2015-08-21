<tr id="${module.id }" pId="${module.parentId}">
	<td height="20"><span class='folder'>${module.name}</span></td>
	<td align="center">
		<#if module.state == 1>
			<font color="blue">正常状态</font>
		<#elseif module.state == 0>
			<font color="red">禁用状态</font>
		</#if>
	</td>
	<td align="center">&nbsp;</td>
	<td align="center">
		<a title="设置数据权限" href='javascript:void(0);' onclick="setDataRight(${module.id});">设置</a>
	</td>
</tr>