<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd">
<script type="text/javascript">
    CountPvCitySiteList =${itemsJsonStr};//清空全局变量用于存储
    totalDataMap =${itemJsonStr};//清空全局变量用于存储
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="count_pv_city_site_tb_data" class="box_table">
</table>
<script type="text/javascript">
    fillTotalData();
    fillTbDateCountPvCitySite(1);//填充表格数据
    fillColumnChartData();//填充图表数据
    pageBarCountPvCitySite();//更新分页工具条
</script>

