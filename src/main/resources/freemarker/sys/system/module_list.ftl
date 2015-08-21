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
			<input type="button" value="新增模块" class="botton_gray" onclick="addModule();"/>
		</div>
		<div class="box_full">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="moudleTable" class="treeTable">
				<thead>
					<tr>
						<th>模块名称</th>
						<th width="10%">模块状态</th>
						<th width="10%">菜单显示</th>
						<th width="10%">菜单名称</th>
						<th width="20%">操作URL</a></th>
						<th width="15%">操作</a></th>
					</tr>
				</thead>
				<tbody>
					<#if (moduleMap['0'])??>
						<#import "/sys/lib/module_tree_macro.ftl" as moduleTree>
						<@moduleTree.showModuleTreeTable list=moduleMap['0'] map=moduleMap operMap=operateMap rowTemplate='/sys/system/module_list_row.ftl' />
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

function addModule(){
	$.jBox("get:/sys/system/module/add", {
		id: "add_input",
	    title: "新增模块",
	    width: 450,
	    height: 300,
	    buttons: {'提交' : 1},
	    submit: function (v, h, f) {
	    	if(!jQuery("#module_form").validationEngine("validate")) return false;
	    	ajaxSubmitJson({
	    		url:'/sys/system/module/add/post.json',
				data:$('#module_form').serialize(),
				callback:function(){
					loadPage('/sys/system/module/list');
					$.jBox.close('add_input');
				}
	    	});
			return false;
        }
	});
}

function editModule(mid){
	$.jBox("get:/sys/system/module/edit/?mid="+mid, {
		id: "edit_input",
	    title: "修改模块",
	    width: 450,
	    height: 300,
	    buttons: {'提交' : 1, '关闭' : 0},
	    submit: function (v, h, f) {
	    	if(v == 0){
	    		return true;
	    	}else{
		    	if(!jQuery("#module_form").validationEngine("validate")) return false;
		    	ajaxSubmitJson({
		    		url:'/sys/system/module/edit/post.json',
					data:$('#module_form').serialize(),
					callback:function(){
						loadPage('/sys/system/module/list');
						$.jBox.close('edit_input');
					}
		    	});
	    	}
			return false;
        }
	});
}

function deleteModule(mid){
	$.jBox.confirm("删除模块会级联删除所有的子模块，您确定要删除吗？", "提示", function(v, h, f){
		 if (v == 'ok') {
		 	$.jBox.tip("正在删除数据...", 'loading');
		 	ajaxSubmitJson({
	    		url:'/sys/system/module/del.json',
				data:'mid='+mid,
				type:'GET',
				callback:function(){
					loadPage('/sys/system/module/list');
					$.jBox.closeTip();
				}
		    });
		 }
		 return true;
	});
}

function bindOperate(mid){
	loadPage('/sys/system/module/bind/?mid='+mid);
}

function setDisplayMenu(oid){
	$.jBox("get:/sys/system/module/menu/?oid="+oid, {
		id: "menu_display_input",
	    title: "菜单显示设置",
	    width: 300,
	    height: 200,
	    buttons: {'提交' : 1, '关闭' : 0},
	    submit: function (v, h, f) {
	    	if(v == 0){
	    		return true;
	    	}else{
		    	if(!jQuery("#module_menu_form").validationEngine("validate")) return false;
		    	ajaxSubmitJson({
		    		url:'/sys/system/module/menu/post.json',
					data:$('#module_menu_form').serialize(),
					callback:function(){
						loadPage('/sys/system/module/list');
						$.jBox.close('menu_display_input');
					}
		    	});
	    	}
			return false;
        }
	});
}
</script>