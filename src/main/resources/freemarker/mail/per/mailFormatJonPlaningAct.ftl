<meta charset="utf-8" />
<div  align="center">
	<div style="color:#333;font-family:宋体;font-size:12px;text-align:left; line-height:24px; padding:20px 0; width:716px; margin:0 auto;">
	<#if emailFormat == 0>
	主题：${userName} 报名参与 ${actName} <br>
	内容：<br>
				尊敬的活动主办方领导：<br>
			我有意参与贵公司的 ${actName} ，以下是本人的详细简历，请惠阅！
    </#if>
	<br>
	<#if remark != null >${remark?replace("\n","<br />")}</#if>
	<#if emailFormat == 1>
	主題：${userName} 報名參與 ${actName} <br>
	内容：<br>
				尊敬的活動主辦方領導：<br>
			我有意參與貴公司的 ${actName} ，以下是本人的詳細簡曆，請惠閱！
	</#if>
	<br>
	<#if remark != null >${remark?replace("\n","<br />")}</#if>
	</div>
	${resumeContentCn}
	${resumeContentEn}
</div>