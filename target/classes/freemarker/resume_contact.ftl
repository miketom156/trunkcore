<#--简历语言类型判断宏-->
<#macro language en cn>
	<#if languageType = 0>${cn}<#else>${en}</#if>
</#macro>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="70" align="right"><@language cn="联系方式：" en="Contact:"/></td>
		<td width="350">
			${perUserVo.mobile} ${(perUserVo.mobilePlace)!} <@language cn="（手机）" en="(Mobile)"/>
			<#if (perUserVo.phone)?exists && perUserVo.phone !="">
				${perUserVo.phone} <@language cn="（电话）" en="(tel)"/>
			</#if>
		</td>
		<#if (perUserVo.homepage)?exists && perUserVo.homepage !="">
		<td width="70" align="right"><@language cn="主页：" en="Homepage:"/></td>
		<td><a href="${perUserVo.homepage}">${perUserVo.homepage}</a></td>
		<#else>
		<td width="70" align="right">&nbsp;</td>
		<td>&nbsp;</td>
		</#if>
	</tr>
	<tr>
		<td align="right"><@language cn="邮箱：" en="E-mail:"/></td>
		<td>
		<#if !buyFlag || !loginFlag>
			${perUserVo.email}
		<#else>
			<a href="mailTo:${perUserVo.email}">${perUserVo.email}</a>
		</#if>
		</td>
		<#if (perUserVo.zipcode)?exists && perUserVo.zipcode !="">
		<td align="right">
		<@language cn="邮编：" en="Zip:"/>
		</td>
		<td>${perUserVo.zipcode}</td>
		<#else>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		</#if>
	</tr>
	<#if (perUserVo.qq)?exists && perUserVo.qq !="">
	<tr>
 		<td align="right"><@language cn="QQ：" en="QQ:"/></td>
 		<td>${perUserVo.qq}</td>
 		<td>&nbsp;</td>
 		<td>&nbsp;</td>
	</tr>
	</#if>
	<#if (perUserVo.address)?exists && perUserVo.address !="">
	<tr>
		<td align="right"><@language cn="地址：" en="Address:"/></td>
		<td colspan="4"><@language cn="${ perUserVo.address}" en="${ perUserVo.addressEn}"/></td>
	</tr>
	</#if>
</table>