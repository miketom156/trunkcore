<div id="tree">
	<#if (departmentMap['0'])??>
		<#if treeType == '0'>
			<ul id="treeData" style="display: none;">
				<li class="folder" id="0">顶级部门
					<ul>
						<#import "/sys/lib/department_tree_macro_view.ftl" as departmentTree>
						<@departmentTree.showDepartmentTreeView list=departmentMap['0'] map=departmentMap />
					</ul>
				</li>
			</ul>
		<#else>
			<ul id="treeData" style="display: none;">
				<#import "/sys/lib/department_tree_macro_view.ftl" as departmentTree>
				<@departmentTree.showDepartmentTreeView list=departmentMap['0'] map=departmentMap />
			</ul>
		</#if>
	</#if>
</div>
<#if (multiFlag > 0)>
<script type="text/javascript">
$(function(){
  var parentDeptId = '${RequestParameters.pid?default("parentDepartmentId")}';
  var parentDeptName = '${RequestParameters.pname?default("parentDepartmentName")}';
  var departmentIds = $("#"+parentDeptId, window.parent.document).val();
  $("#tree").fancytree({
  	  checkbox: true,
      selectMode: 2,
      select: function(event, data) {
          var nodeList = data.tree.getSelectedNodes();
          var nodeId = new Array();
          var nodeStr = new Array();
          $.each(nodeList, function(idx, node){
          		nodeId.push(node.key);
          		nodeStr.push(node.title);
          })
          $("#"+parentDeptId).val(nodeId.join(','));
          $("#"+parentDeptName).val(nodeStr.join(','));
      }
  });
  if(departmentIds != ''){
  	  var idsArr = departmentIds.split(',');
      $("#tree").fancytree("getTree").visit(function(node){
  	  	  if($.inArray(node.key, idsArr) != -1){
  	  	      node.setSelected(true);
  	      }
  	  });
  }
});
</script>
<#else>
<script type="text/javascript">
$(function(){
  var parentDeptId = '${RequestParameters.pid?default("parentDepartmentId")}';
  var parentDeptName = '${RequestParameters.pname?default("parentDepartmentName")}';
  $("#tree").fancytree({
  	minExpandLevel: 0,
  	focus: function(event, data) {
        $("#"+parentDeptId).val(data.node.key);
        $("#"+parentDeptName).val(data.node.title);
        $.jBox.close();
    }
  });
});
</script>
</#if>