<tr id="${department.id }" pId="${department.parentId}">
	<td height="20"><span class='folder'>${department.name}</span></td>
	<td align="left">
		<#if (department.descript?length > 20)>
		${department.descript[0..20]?default("")}...
		<#else>
		${department.descript?default("")}
		</#if>
    </td>
	<td align="center">
		<#if department.deptType == 0>
		行政部门
		<#elseif department.deptType == 1>
		业务部门
		</#if>
	</td>
	<td align="center">
		<#if department.deptProperty == 10>
		大区
		<#elseif department.deptProperty == 20>
		公司
		<#elseif department.deptProperty == 30>
		部门
		<#elseif department.deptProperty == 40>
		小组
		</#if>
	</td>
	<td align="center">
		<a title="修改" href='javascript:void(0);' onclick="editDepartment(${department.id})">修改</a>
		<a title="删除" href='javascript:void(0);' onclick="deleteDepartment(${department.id})">删除</a>
	</td>
</tr>