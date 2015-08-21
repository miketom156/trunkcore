<link rel="stylesheet" href="/static/js/v1/sys/jquery/treetable/vsStyle/jquery.treeTable.css" />
<script type="text/javascript" src="/static/js/v1/sys/jquery/treetable/jquery.treeTable.js"></script>
<style type="text/css">
.treeTable { border-left:solid 1px #ededed; border-top:solid 1px #ededed;  cellpadding:0; cellspacing:0; border-collapse:collapse;}
.treeTable thead th {border-right:solid 1px #ededed; border-bottom:solid 1px #ededed; vertical-align:top; white-space:nowrap; line-height:21px; cursor:default;}
.treeTable tbody td {border-right:solid 1px #ededed; overflow:hidden; border-bottom:solid 1px #ededed; vertical-align:middle;}
.treeTable_hover {background: none repeat scroll 0 0 #F5F5F5; border-color: #DDDDDD;}
.treeTable_click {background: none repeat scroll 0 0 #F5F5F5;}
</style>
<div class="right_box">
	<div class="main_info">
		<div class="box_full">
			<input type="button" value="新增部门" class="botton_gray" onclick="addDepartment();"/>
		</div>
		<div class="box_full">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="departmentTable" class="treeTable">
				<thead>
					<tr>
						<th>部门名称</th>
						<th width="25%">部门描述</th>
						<th width="15%">部门性质</th>
						<th width="10%">部门属性</th>
						<th width="10%">操作</a></th>
					</tr>
				</thead>
				<tbody>
					<#if (departmentMap['0'])??>
						<#import "/sys/lib/department_tree_macro.ftl" as departmentTree>
						<@departmentTree.showDepartmentTreeTable list=departmentMap['0'] map=departmentMap rowTemplate='/sys/system/department_list_row.ftl' />
					</#if>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var option = {
            theme:'vsStyle',
            expandLevel : 1,
            onSelect : function($departmentTable, id) {
                window.console && console.log('onSelect:' + id);
            }
    };
    $('#departmentTable').treeTable(option);
    $('#departmentTable tr').bind('mouseover', function(){
    	$(this).addClass('treeTable_hover');
    }).bind('mouseout', function(){
    	$(this).removeClass('treeTable_hover');
    }).bind('click', function(){
    	$(this).addClass('treeTable_click')
    		   .siblings('tr').removeClass('treeTable_click') ;
    });
});

function addDepartment(){
	$.jBox("get:/sys/system/department/add", {
		id: "add_input",
	    title: "新增部门",
	    width: 450,
	    height: 300,
	    buttons: {'提交' : 1},
	    submit: function (v, h, f) {
	    	if(!jQuery("#department_form").validationEngine("validate")) return false;
	    	ajaxSubmitJson({
	    		url:'/sys/system/department/add/post.json',
				data:$('#department_form').serialize(),
				callback:function(){
					loadPage('/sys/system/department/list');
					$.jBox.close('add_input');
				}
	    	});
			return false;
        }
	});
}

function editDepartment(departId){
	$.jBox("get:/sys/system/department/edit/?departId="+departId, {
		id: "edit_input",
	    title: "修改模块",
	    width: 450,
	    height: 300,
	    buttons: {'提交' : 1, '关闭' : 0},
	    submit: function (v, h, f) {
	    	if(v == 0){
	    		return true;
	    	}else{
		    	if(!jQuery("#department_form").validationEngine("validate")) return false;
		    	ajaxSubmitJson({
		    		url:'/sys/system/department/edit/post.json',
					data:$('#department_form').serialize(),
					callback:function(){
						loadPage('/sys/system/department/list');
						$.jBox.close('edit_input');
					}
		    	});
	    	}
			return false;
        }
	});
}

function deleteDepartment(departId){
	$.jBox.confirm("删除部门会级联删除所有的下级部门，您确定要删除吗？", "提示", function(v, h, f){
		 if (v == 'ok') {
		 	$.jBox.tip("正在删除数据...", 'loading');
		 	ajaxSubmitJson({
	    		url:'/sys/system/department/del.json',
				data:'departId='+departId,
				type:'GET',
				callback:function(){
					loadPage('/sys/system/department/list');
					$.jBox.closeTip();
				}
		    });
		 }
		 return true;
	});
}
</script>