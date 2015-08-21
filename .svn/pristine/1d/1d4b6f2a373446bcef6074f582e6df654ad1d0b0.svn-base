<#setting number_format="#">
<script type="text/javascript">
    scanPageNum = 1;
    currentActivityId = ${currentActivityId};
</script>
<body>
<div data-id="wechatActivityData">
    <div class="search_box" style="margin:0px;width:auto;padding:0px;">
        <form action="" id="search_form" method="post">
            <div>
                <input name="start" id="start" type="text" value=""
                       class="inpt Wdate"
                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\')}'});" value=""
                       placeholder="开始日期" style="width:85px;"/>
                -
                <input name="end" id="end" type="text" value=""
                       class="inpt Wdate"
                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start\')}',maxDate:'%y-%M-%d'});"
                       value="" placeholder="结束日期"
                       style="width:85px;"/>
                <input type="button" id="scanDateSearch" name="scanDateSearch" value="搜索" class="botton_blue"/>
            </div>
        </form>
    </div>
    <div id="wechatActivityScanTb"></div>
    <div id="wechatActivityScanCount" style="display:none;"></div>
</div>
<script type="text/javascript">
    $("#scanDateSearch").click(function(){
        scanPageNum = 1;
        WechatActivityScanLoadData();
    });
    WechatActivityScanLoadData();
</script>