<#setting number_format="#">
<script type="text/javascript">
    followerPageNum=1;
</script>
<body>
<div data-id="wechatFollowerData">
    <div class="search_box" style="margin:0px;width:auto;padding:0px;">
        <form action="" id="search_form_follower" method="post">
            <div>
                <input name="start_follower" id="start_follower" type="text" value=""
                       class="inpt Wdate"
                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end_follower\')}'});" value=""
                       placeholder="开始日期" style="width:85px;"/>
                -
                <input name="end_follower" id="end_follower" type="text" value=""
                       class="inpt Wdate"
                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start_follower\')}',maxDate:'%y-%M-{%d-1}'});"
                       value="" placeholder="结束日期"
                       style="width:85px;"/>
                <input type="button" id="followerDateSearch" name="followerDateSearch" value="搜索" class="botton_blue"/>
            </div>
        </form>
    </div>
    <div id="wechatFollowerTb"></div>
    <div id="wechatFollowerCount" style="display:none;"></div>
</div>
<script type="text/javascript">
    $("#followerDateSearch").click(function(){
        followerPageNum = 1;
        WechatFollowerLoadData();
    });
    WechatFollowerLoadData();
</script>