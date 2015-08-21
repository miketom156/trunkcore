<#--专题文章 2014 -->
<#-- 描述截取长度 -->
<#macro desc str>
    <#if str?length gt 48>
      ${str[0..48]}
    <#else>
      ${str}
    </#if>
</#macro>
 <#if showMap?size gt 0 >
 <dl class="ontop">
     <#list showMap["智通才富综合新闻"] as art >
         <dt><a href="${art.url}" target="_blank" >${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></dt>
         <dd><@desc str="${art.desc}" /><a href="${art.url}" target="_blank">[全文]</a></dd>
     </#list>
 </dl>
 <ul class="fl">
     <#list showMap["智通才富专题新闻"] as art>
         <#if art_index == 0>
         <li class="spNews">
         <a target="_blank" href="${art.url}">
                 <img src="${art.thumbnail}" />
                 <span><i>${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</i></span>
             </a>
         </li>
         </#if>
     </#list>
     <#list showMap["智通才富晋升加薪"] as art>
         <#if art_index == 0>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
         </#if>
     </#list>
     <#list showMap["智通才富谋职攻略"] as art>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
     </#list>
     <#list showMap["智通才富简历技巧"] as art>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
     </#list>
     <#list showMap["智通才富面试技巧"] as art>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
     </#list>
 </ul>
 <ul class="fr">
     <#list showMap["智通才富专题新闻"] as art>
         <#if art_index == 1>
         <li class="spNews">
         <a target="_blank" href="${art.url}">
                 <img src="${art.thumbnail}" />
                 <span><i>${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</i></span>
             </a>
         </li>
         </#if>
     </#list>
     <#list showMap["智通才富晋升加薪"] as art>
         <#if art_index == 1>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
         </#if>
     </#list>
     <#list showMap["智通才富职场生存"] as art>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
     </#list>
     <#list showMap["智通才富职业规划"] as art>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
     </#list>
     <#list showMap["智通才富跳槽攻略"] as art>
         <li><a href="${art.url}" target="_blank">${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</a></li>
     </#list>


 </ul>
</#if>