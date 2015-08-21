<#-- 首页的城市资讯(推荐资讯的位置) 2014 版本 -->
<#if showMap?size gt 0 >
    <#list showMap["index_city_news_2014"] as art>
    <#if art_index < 10 >
    <li><a href="${art.url}" title="${art.title}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
    </#if>
    </#list>
</#if>