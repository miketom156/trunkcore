<div id="tree">
	<#if (moduleMap['0'])??>
		<#if treeType == '0'>
			<ul id="treeData" style="display: none;">
				<li class="folder" id="0">顶级模块
					<ul>
						<#import "/sys/lib/module_tree_macro_view.ftl" as moduleTree>
						<@moduleTree.showModuleTreeView list=moduleMap['0'] map=moduleMap />
					</ul>
				</li>
			</ul>
		<#else>
			<ul id="treeData" style="display: none;">
				<#import "/sys/lib/module_tree_macro_view.ftl" as moduleTree>
				<@moduleTree.showModuleTreeView list=moduleMap['0'] map=moduleMap />
			</ul>
		</#if>
	</#if>
</div>
<script type="text/javascript">
$(function(){
  $("#tree").fancytree({
  	minExpandLevel: 0,
  	focus: function(event, data) {
        $("#${parentModuleId}").val(data.node.key);
        $("#${parentModuleName}").val(data.node.title);
        $.jBox.close();
    }
  });
});
</script>