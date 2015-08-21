<#--城市资讯-->
<#if showMap[city]??>
	<#list showMap[city] as art>
        <#if art_index gt 3 >
        	<#-- 前4条城市资讯在最新资讯栏目显示 -->
         	<a href="${art.url}" target="_blank" title="${art.title}">${art.title}</a>
        </#if>
    </#list>
<#else>
	    <a href="#" target="_blank" title="智通人才校园服务季·2013年佛科院&amp;纺织学院大型校园招聘会">智通人才校园服务季·2013年佛科院&amp;纺.</a>
        <a href="#" target="_blank" title="“德国智库”助力佛山制造">“德国智库”助力佛山制造</a>
        <a href="#" target="_blank" title="海外侨领看好中德工业服务区">海外侨领看好中德工业服务区</a>
        <a href="#" target="_blank" title="佛山新城将亮相2013中欧城市博览会">佛山新城将亮相2013中欧城市博览会</a>
        <a href="#" target="_blank" title="一场“家”盛宴 诚邀你品鉴">一场“家”盛宴 诚邀你品鉴</a>
        <a href="#" target="_blank" title="佛山揽11奖项">佛山揽11奖项</a>
        <a href="#" target="_blank" title="基层岗位少人问津">基层岗位少人问津</a>
        <a href="#" target="_blank" title="军队招文职报名很火爆">军队招文职报名很火爆</a>
        <a href="#" target="_blank" title="传统秋色唤回集体记忆">传统秋色唤回集体记忆</a>
        <a href="#" target="_blank" title="年薪两百万招揽高端人才">年薪两百万招揽高端人才</a>
</#if>