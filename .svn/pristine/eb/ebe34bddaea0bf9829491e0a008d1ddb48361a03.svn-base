<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="/static/style/v1/sys/city.css" rel="stylesheet" type="text/css" />
    <script src="/static/js/v1/common/base_common.min.js"></script>
    <script src="/static/js/v1/common/jqPlugs_common.min.js"></script>
    <script src="/static/js/v1/common/jquery.boxSelector.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            //初始化验证控件
            jQuery("#resume_filt_form").validationEngine({"validationEventTrigger":""});
            $("#btn_jobLocation").boxSelector({
                key:"city",
                selectType:"radio",
                selectParent:true
            });
            $("#btn_location").boxSelector({
                key:"city",
                selectType:"radio",
                selectParent:true
            });
            //初始化学历下拉项
            initDegreeSelect("degreeBegin");
            initDegreeSelect("degreeEnd");
            //初始化期望工作职位
            initJobSelect("expectJobBox", "id", true, false, true);
            //添加求职意向
            $("#addJob").click(function(){
                moveOption("expectJobFun", "selectedJob", true, -1);
            });
            //删除求职意向
            $("#delJob").click(function(){
                moveOption("selectedJob", "expectJobFun", false, -1);
            });
            //工作年限在输入完成后失去焦点后触发事件
            $("#workYear").blur(function(){
                $(".workYear").attr("disabled", $("#workYear").val() == '' ? true : false);
            });
            //学历选择完成后失去焦点后触发事件
            $("#degree").blur(function(){
                $(".degree").attr("disabled", $("#degree").val() == '' ? true : false);
            });
            //筛选
            $("#filt").click(function(){
                $("select[id=selectedField] option").each(function () {
                    $(this).attr("selected", true);
                });
                $("select[id=selectedJob] option").each(function () {
                    $(this).attr("selected", true);
                });
                filtResume();
            });
        });
        //通过简历筛选发送对象
        function filtResume() {
            if($("#id_location").val()==''||($("#id_location").val() != "" && $("#id_location").val() == "")) {
                $.jBox.error("请选择求职意向城市！", "错误");
                $("#jobLocation").focus();
                return;
            }
            if(($("#workYearBegin").val() != '' && $("#workYearEnd").val() == '') ||
                    ($("#workYearBegin").val() == '' && $("#workYearEnd").val() != '')) {
                $.jBox.error("工作年限范围不能为空！", "错误");
                return;
            }
            if(($("#degreeBegin").val() != '' && $("#degreeEnd").val() == '') ||
                    ($("#degreeBegin").val() == '' && $("#degreeEnd").val() != '')) {
                $.jBox.error("学历范围不能为空！", "错误");
                return;
            }
            $.jBox.tip("正在筛选中，请稍后...", 'loading');
            var param = $("#resume_filt_form").serialize();
            $.ajax({
                type: "POST",
                data: param,
                url: "/sys/sms/resumeFilter",
                dataType: "html",
                success:function(data) {
                    if(data) {
                        $.jBox.closeTip();
                        $("#htmlContainer").html(data);
                    }
                },
                error:function (XMLHttpRequest, textStatus, errorThrown) {
                    //alert(XMLHttpRequest.responseText);
                    $.jBox.tip("内部服务器错误！");
                }
            });
        }
    </script>
</head>
<div class="right_box">
<div class="main_info">
<div class="box_full">
<form id="resume_filt_form">
<table width="100%" border="0" cellspacing="3" cellpadding="0" align="center">
<tr>
    <td align="right" colspan="3"><font color="red">带＊号的为必填信息</font></td>
</tr>
<tr>
    <td align="right" width="6%"></td>
    <td align="right" width="15%">发送条数：</td>
    <td><input type="text" id="maxResult" name="maxResult" size="4" maxlength="4" value="1000" class="validate[required,min[1],max[9999]]" />&nbsp;<font color="red">可选值［1－9999］</font></td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr id="filedis">
    <td align="right" width="6%"></td>
    <td align="right" width="15%">简历注册时间：</td>
    <td width="83%">
        <input type="text" name="createDateBegin" id="createDateBegin" size="16" onfocus="WdatePicker({onpicked:function(){createDateEnd.focus();},maxDate:'#F{$dp.$D(\'createDateEnd\')}'})" class="Wdate validate[custom[date],past[createDate]]" />&nbsp;至
        <input type="text" name="createDateEnd" id="createDateEnd" size="16" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'createDateBegin\')}'})" class="Wdate validate[custom[date],future[createDate]]" />
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr id="filedis">
    <td align="right" width="6%"></td>
    <td align="right" width="15%">简历刷新时间：</td>
    <td width="83%">
        <input type="text" name="freDateBegin" id="freDateBegin" size="16" onfocus="WdatePicker({onpicked:function(){freDateEnd.focus();},maxDate:'#F{$dp.$D(\'freDateEnd\')}'})" class="Wdate validate[custom[date],past[freDate]]" />&nbsp;至
        <input type="text" name="freDateEnd" id="freDateEnd" size="16" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'freDateBegin\')}'})" class="Wdate validate[custom[date],future[freDate]]" />
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr>
    <td align="right" width="6%"></td>
    <td align="right"><font color="red">＊</font>&nbsp;简历状态：</td>
    <td>
        <select name="pass">
            <option value="1" selected="selected">审核通过</option>
            <option value="0">未审核通过</option>
            <option value="">所有简历</option>
        </select>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr>
    <td align="right"></td>
    <td align="right"><font color="red">＊</font>&nbsp;现所在地：</td>
    <td>
        <div id="locationBox">
            <input id="name_location" name="name_location" type="text" readonly="readonly" size="8"/>
            <input id="id_location" name="location" type="hidden" value="" />
            <input type="button" id="btn_location" value="请选择" />
        </div>
        </div>
    </td>
</tr>
<tr>
    <td align="right"></td>
    <td align="right">&nbsp;工作地点（求职意向）：</td>
    <td>
        <div id="jobLocationBox">
            <input id="name_jobLocation" name="name_jobLocation" type="text" readonly="readonly" size="8"/>
            <input id="id_jobLocation" name="jobLocation" type="hidden" value="" />
            <input type="button" id="btn_jobLocation" value="请选择" />
        </div>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr>
    <td align="right" width="6%"></td>
    <td align="right">工作年限：</td>
    <td>
        从&nbsp;<input maxlength="4" size="4" id="workYearBegin" name="workYearBegin" class="validate[custom[integer]]" value="" />&nbsp;到&nbsp;
        <input maxlength="4" size="4" id="workYearEnd" name="workYearEnd" class="validate[custom[integer]]" value="" />&nbsp;年
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr>
    <td align="right" width="6%"></td>
    <td align="right">学历：</td>
    <td>
        从&nbsp;<select id="degreeBegin" name="degreeBegin">
        <option value="" selected="selected">不限</option>
    </select>&nbsp;到&nbsp;
        <select id="degreeEnd" name="degreeEnd">
            <option value="" selected="selected">不限</option>
        </select>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr>
    <td align="right"></td>
    <td align="right">年龄：</td>
    <td>
        从&nbsp;<select name="age1">
        <option value="">不限</option>
        <option value=15>15</option>
        <option value=16>16</option>
        <option value=17>17</option>
        <option value=18>18</option>
        <option value=19>19</option>
        <option value=20>20</option>
        <option value=21>21</option>
        <option value=22>22</option>
        <option value=23>23</option>
        <option value=24>24</option>
        <option value=25>25</option>
        <option value=26>26</option>
        <option value=27>27</option>
        <option value=28>28</option>
        <option value=29>29</option>
        <option value=30>30</option>
        <option value=31>31</option>
        <option value=32>32</option>
        <option value=33>33</option>
        <option value=34>34</option>
        <option value=35>35</option>
        <option value=36>36</option>
        <option value=37>37</option>
        <option value=38>38</option>
        <option value=39>39</option>
        <option value=40>40</option>
        <option value=41>41</option>
        <option value=42>42</option>
        <option value=43>43</option>
        <option value=44>44</option>
        <option value=45>45</option>
        <option value=46>46</option>
        <option value=47>47</option>
        <option value=48>48</option>
        <option value=49>49</option>
        <option value=50>50</option>
    </select>&nbsp;到&nbsp;
        <select name="age2">
            <option value="">不限</option>
            <option value=15>15</option>
            <option value=16>16</option>
            <option value=17>17</option>
            <option value=18>18</option>
            <option value=19>19</option>
            <option value=20>20</option>
            <option value=21>21</option>
            <option value=22>22</option>
            <option value=23>23</option>
            <option value=24>24</option>
            <option value=25>25</option>
            <option value=26>26</option>
            <option value=27>27</option>
            <option value=28>28</option>
            <option value=29>29</option>
            <option value=30>30</option>
            <option value=31>31</option>
            <option value=32>32</option>
            <option value=33>33</option>
            <option value=34>34</option>
            <option value=35>35</option>
            <option value=36>36</option>
            <option value=37>37</option>
            <option value=38>38</option>
            <option value=39>39</option>
            <option value=40>40</option>
            <option value=41>41</option>
            <option value=42>42</option>
            <option value=43>43</option>
            <option value=44>44</option>
            <option value=45>45</option>
            <option value=46>46</option>
            <option value=47>47</option>
            <option value=48>48</option>
            <option value=49>49</option>
            <option value=50>50</option>
        </select>&nbsp;岁
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr>
    <td align="right"></td>
    <td align="right">姓别：</td>
    <td height="25">
        <select name="gender">
            <option value="">不限</option>
            <option value="1">男</option>
            <option value="2">女</option>
        </select>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<tr>
    <td align="right"></td>
    <td align="right">求职意向：</td>
    <td height="25">
        <div id="expectJobBox">
            <table width="100%"  border="0" cellspacing="10" cellpadding="0">
                <tr>
                    <td height="20" colspan="3">
                        <select id="expectJobTitle" name="lfldpositionname" class="jobTtile">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="38%" align="left">
                        <select id="expectJobFun" name="lfldposition" style="width:186px;height:160px;" multiple="multiple" size=4 class="jobFun">
                        </select>
                    </td>
                    <td width="17%">
                        <input type="button" id="addJob" value="添加&gt;&gt;" class="botton_blue" />
                        <br/><br/>
                        <input type="button" id="delJob" value="&lt;&lt;删除" class="botton_blue" />
                    </td>
                    <td width="45%">
                        <select id="selectedJob" name="selectedJob" style="width:186px;height:160px;" multiple="multiple" size="4">
                        </select>
                    </td>
                </tr>
            </table>
        </div>
    </td>
</tr>
</table>
</form>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="1">
    <tr>
        <td align="center">
            <br />
            <input type="button" id="filt" class="botton_blue_large" value="筛选" />&nbsp;
        </td>
    </tr>
</table>
</div>
</div>
</div>
</html>