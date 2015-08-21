<#--最新动态-->
<#if showMap[city]??>
<#--	<#list showMap["index"] as art>
	    <li><a href="${art.url}" target="_blank" title="${art.title}">${art.title}</a></li>
	</#list>-->
	<#list showMap[city] as art>
	    <li><a href="${art.url}" target="_blank" title="${art.title}">${art.title}</a></li>
	</#list>
<#else>
        <li><a href="#">李克强视察智通人才总部李克强视察智通人才总部</a></li>
        <li><a href="#">湘潭人才网：如何治疗你的公.</a></li>
        <li><a href="#">湘潭人才网：客户最需要</a></li>
        <li><a href="#">湘潭人才网：为什么中国员</a></li>
</#if>