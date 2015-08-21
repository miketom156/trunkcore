<table width="80%" border="0" cellspacing="0" cellpadding="0" id="moudleTable" class="treeTable">
	<thead>
		<tr>
			<th>模块名称</th>
			<th width="15%">模块状态</th>
			<th width="10%">选择</th>
			<th width="20%">数据权限</th>
		</tr>
	</thead>
	<tbody>
		<#if (moduleMap['0'])??>
			<#import "/sys/lib/role_module_tree_macro.ftl" as moduleTree>
			<@moduleTree.showModuleTreeTable list=moduleMap['0'] map=moduleMap operMap=operateMap userOperIdMap=operateIdMap rowTemplate='/sys/system/role_module_tree_list_row.ftl' />
		</#if>
	</tbody>
</table>
<script type="text/javascript">
$(document).ready(function(){
	var option = {
            theme:'vsStyle',
            expandLevel : 3,
            onSelect : function($moudleTable, id) {
                window.console && console.log('onSelect:' + id);
            }
    };
    $('#moudleTable').treeTable(option);
    $('#moudleTable tr').bind('mouseover', function(){
    	$(this).addClass('treeTable_hover');
    }).bind('mouseout', function(){
    	$(this).removeClass('treeTable_hover');
    }).bind('click', function(){
    	$(this).addClass('treeTable_click')
    		   .siblings('tr').removeClass('treeTable_click') ;
    });
});
</script>