<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd">
<script type="text/javascript">
    var countPvCityAllDataTemp = ${items};//查询得到的结果数据
    if(showType == 1){
        countPvCityAllDataChart = countPvCityAllDataTemp;
    }else{
        countPvCityAllData = countPvCityAllDataTemp;
    }

    var allDataTemp = ${item};//查询得到的结果数据
    if(showType == 1){
        allDataChart = allDataTemp;
    }else{
        allData = allDataTemp;
    }

    if(showType == 1){
    showCountPvCityAll($('[data-id="count_pv_city_all_chart_panel"]'));
    }else{
        totalPageCountPvCityAll = allData["totalPage"];
        countTbTotal();
        fillTbDateCountPvCityAll(pageNumCountPvCityAll);
        pageBarCountPvCityAll();
    }
</script>

