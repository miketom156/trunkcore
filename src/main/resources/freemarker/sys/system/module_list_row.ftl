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
	<td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
	<td align="center">
		<a title="修改" href='javascript:void(0);' onclick="editModule(${module.id})">修改</a>
		<a title="删除" href='javascript:void(0);' onclick="deleteModule(${module.id})">删除</a>
		<a title="绑定操作" href='javascript:void(0);' onclick="bindOperate(${module.id})">绑定操作</a>
	</td>
</tr>