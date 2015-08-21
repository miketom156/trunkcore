<#--简历语言类型判断宏-->
<#macro language en cn>
	<#if languageType = 0>${cn}<#else>${en}</#if>
</#macro>
<#--结束时间-->
<#macro dateEnd end>
	<#if end !="">
		～ ${end}
	<#else>
		- <@language en="Present" cn="至今" />
	</#if>
</#macro>
<#-- 手机，身体证等号码 -->
<#macro number num>
    <#if num !="">
    ${num}
    <#else>
        <@language en="none" cn="无" />
    </#if>
</#macro>
<#if (perUserVo.userName)?exists && perUserVo.userName !="">
<div class="titBar">
	<h2><@language cn="基本资料" en="Basic Infomation"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
	<div class="userInfo">
		<div class="text">
			<strong class="userName"><@language cn="${perUserVo.userName}" en="${perUserVo.userNameEn}"/></strong>
			<span style="color:#b6b6b6;">(No.${resumeNo})</span>
			<p>${basicDataVo.gender} <#if (perUserVo.nation)?exists && perUserVo.nation!="">|  <@language cn="${perUserVo.nation}" en="${perUserVo.nationEn}"/></#if> |  ${basicDataVo.age} <@language cn="岁" en="years"/> <#if (basicDataVo.marriage)?exists && basicDataVo.marriage !="" && basicDataVo.marriage !="保密" && basicDataVo.marriage !="Secret"> | ${basicDataVo.marriage} </#if>  |  ${basicDataVo.hometown}</p>
			<#if (perUserVo.mobile)?exists>
			<dl class="row">
				<dt class="row-title"><@language cn="手机" en="Mobile"/></dt>
				<dd class="row-content">${perUserVo.mobile}</dd>
			</dl>
			</#if>
			<#if (perUserVo.phone)?exists && perUserVo.phone!="">
			<dl class="row">
				<dt class="row-title"><@language cn="电话" en="Telephone"/></dt>
				<dd class="row-content">${perUserVo.phone}</dd>
			</dl>
			</#if>
			<dl class="row">
				<dt class="row-title"><@language cn="邮箱" en="E-mail"/></dt>
				<dd class="row-content">${perUserVo.email}</dd>
			</dl>
			<dl class="row">
				<dt class="row-title"><@language cn="现居住地" en="Residency"/></dt>
				<dd class="row-content">${basicDataVo.location}</dd>
			</dl>
			<#if (perUserVo.cardType)?exists && perUserVo.cardType !="" && perUserVo.cardType gt 0 >
			<dl class="row">
				<dt class="row-title"><@language cn="${basicDataVo.cardType}" en="${basicDataVo.cardType}"/></dt>
				<dd class="row-content">${perUserVo.cardNum}</dd>
			</dl>
  			</#if>
  			<#if (basicDataVo.highDegree)?exists && basicDataVo.highDegree !="">
	      	<dl class="row">
				<dt class="row-title"><@language cn="学历" en="Degree"/></dt>
				<dd class="row-content">${basicDataVo.highDegree} </dd>
			</dl>
			</#if>
  			<dl class="row">
				<dt class="row-title"><@language cn="工作经验" en="Yrs.of Experience"/></dt>
				<dd class="row-content">${basicDataVo.jobyearType}</dd>
			</dl>
			<dl class="row">
				<dt class="row-title"><@language cn="求职状态" en="Current Situation"/></dt>
				<dd class="row-content">${basicDataVo.jobState}</dd>
			</dl>
			<#if (basicDataVo.nowSalary)?exists && basicDataVo.nowSalary !="">
			<dl class="row">
				<dt class="row-title"><@language cn="目前月薪" en="Current Salary"/></dt>
				<dd class="row-content">${basicDataVo.nowSalary}(<@language cn="元/月" en="Yuan/month"/>)</dd>
			</dl>
			</#if>
  			<#if (intentInfoVo.jobCode)?exists && intentInfoVo.jobCode !="">
  			<dl class="row">
				<dt class="row-title"><@language cn="期望职位" en="Desired Position"/></dt>
				<dd class="row-content">${intentInfoVo.jobCode}</dd>
			</dl>
  			</#if>
		</div>
		<div class="avatar">
		<#if (perUserVo.filePath)?exists && (perUserVo.fileName)?exists>
			<img src="${avatarUrl}" width="90" />
		<#else>
			<img src="${imageUrl}/static/img/app/resume/photoBlank.gif" width="90" />
		</#if>
		</div>
	</div>
</div>
</#if>
<#if (intentInfoVo.jobCode)?exists && intentInfoVo.jobCode !="">
<div class="titBar">
	<h2><@language cn="求职意向" en="Career Will"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
	<dl class="row">
		<dt class="row-title"><@language cn="到岗时间" en="I can start"/></dt>
		<dd class="row-content">${intentInfoVo.checkindate}</dd>
	</dl>
	<dl class="row">
		<dt class="row-title"><@language cn="工作类型" en="Type of Employment"/></dt>
		<dd class="row-content">${intentInfoVo.jobType}</dd>
	</dl>
	<dl class="row">
		<dt class="row-title"><@language cn="期望地区" en="Desired Location"/></dt>
		<dd class="row-content">${intentInfoVo.jobLocation}</dd>
	</dl>
	<dl class="row">
		<dt class="row-title"><@language cn="期望职位" en="Desired Position"/></dt>
		<dd class="row-content">${intentInfoVo.jobCode}</dd>
	</dl>
	<#if (intentInfoVo.salary)?exists || intentInfoVo.negotiable || (intentInfoVo.treatment)?exists >
	<dl class="row">
		<dt class="row-title"><@language cn="期望待遇" en="Desired salary"/></dt>
		<dd class="row-content">
		<#if (intentInfoVo.salary)?exists && basicDataVo.nowSalary !="面议" && basicDataVo.nowSalary !="Negotiable">${intentInfoVo.salary}<@language cn="元/月" en="Yuan/month"/></#if>
        <#if intentInfoVo.negotiable && (intentInfoVo.salary)?exists>(<@language cn="可面议" en="May be negotiable"/>)</#if>
        <#if (intentInfoVo.treatment)?exists>${intentInfoVo.treatment}</#if>
		</dd>
	</dl>
	</#if>
	<#if (intentInfoVo.otherMandate)?length gt 1>
	<dl class="row">
		<dt><@language cn="其他要求" en="Other Mandate"/></dt>
		<dd>${intentInfoVo.otherMandate}</dd>
	</dl>
	</#if>
	<#if (intentInfoVo.keywords)?exists && intentInfoVo.keywords !="">
	<dl class="row">
		<dt class="row-title"><@language cn="关键字" en="key"/></dt>
		<dd class="row-content">${intentInfoVo.keywords}</dd>
	</dl>
	</#if>
	<#if (intentInfoVo.professionSkill)?exists>
	<dl class="row">
		<dt class="row-title"><@language cn="职业技能<br />自我评价" en="Technical Expertise/Self Assessment"/></dt>
		<dd class="row-content"><pre>${intentInfoVo.professionSkill}</pre></dd>
	</dl>
	</#if>
</div>
</#if>
<#if (workInfoVoList)?exists && (workInfoVoList?size>0)>
<div class="titBar">
	<h2><@language cn="工作经验/社会实践" en="Work Experience"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
<#list workInfoVoList as workInfoVo>
	<#if workInfoVo_index != 0><hr /></#if>
	<p>${workInfoVo.begin} <@dateEnd end="${workInfoVo.end}" /><#if (workInfoVo.jobName)?exists && workInfoVo.jobName !="">(${workInfoVo.jobName})</#if></p>
	<p>
	${workInfoVo.comName}
	<#if (workInfoVo.section)?exists && workInfoVo.section !="">(${workInfoVo.section})</#if>
	</p>
	<#if (workInfoVo.comType)?exists>
	<dl class="row">
		<dt class="row-title"><@language cn="公司性质" en="Company Type"/></dt>
		<dd class="row-content">${workInfoVo.comType}</dd>
	</dl>
	</#if>
	<#if (workInfoVo.comCalling)?exists>
	<dl class="row">
		<dt class="row-title"><@language cn="行业类别" en="Industry"/></dt>
		<dd class="row-content">${workInfoVo.comCalling}</dd>
	</dl>
	</#if>
	<#if (workInfoVo.comScale)?exists>
	<dl class="row">
		<dt class="row-title"><@language cn="公司规模" en="Company Size"/></dt>
		<dd class="row-content">${workInfoVo.comScale}<@language cn="人" en="people"/></dd>
	</dl>
	</#if>
	<#if (workInfoVo.description)?exists && workInfoVo.description !="">
	<dl class="row">
		<dt class="row-title"><@language cn="工作描述" en="Work description"/></dt>
		<dd class="row-content"><pre>${workInfoVo.description}</pre></dd>
	</dl>
	</#if>
	<#if (workInfoVo.leftreason)?exists && workInfoVo.leftreason !="">
	<dl class="row">
		<dt class="row-title"><@language cn="离职原因" en="Reason for Quit"/></dt>
		<dd class="row-content">${workInfoVo.leftreason}</dd>
	</dl>
	</#if>
</#list>
</div>
</#if>
<#if (projectInfoVoList)? exists && (projectInfoVoList?size>0)>
<div class="titBar">
	<h2><@language cn="项目经验" en="Project"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
<#list projectInfoVoList as projectInfoVo>
	<#if projectInfoVo_index != 0><hr /></#if>
	<p>${projectInfoVo.begin} <@dateEnd end="${projectInfoVo.end}" />(${projectInfoVo.projectName})</p>
	<dl class="row">
		<dt class="row-title"><@language cn="项目描述" en="Project Description"/></dt>
		<dd class="row-content"><pre>${projectInfoVo.proDescribe}</pre></dd>
	</dl>
	<dl class="row">
		<dt class="row-title"><@language cn="责任描述" en="Responsibility"/></dt>
		<dd class="row-content"><pre>${projectInfoVo.dutyDescribe}</pre></dd>
	</dl>
</#list>
</div>
</#if>
<#if (educationInfoVoList)?exists && (educationInfoVoList?size>0)>
<div class="titBar">
	<h2><@language cn="教育经历" en="Education"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
<#list educationInfoVoList as educationInfoVo>
	<#if educationInfoVo_index != 0><hr /></#if>
	<p>${educationInfoVo.begin} <@dateEnd end="${educationInfoVo.end}" />(${educationInfoVo.schoolName})</p>
	<#if (educationInfoVo.speciality)?exists && educationInfoVo.speciality !="">
	<dl class="row">
		<dt class="row-title"><@language cn="专业" en="Speciality"/></dt>
		<dd>${educationInfoVo.speciality}</dd>
	</dl>
	</#if>
	<dl class="row">
		<dt class="row-title"><@language cn="学历" en="Degree"/></dt>
		<dd class="row-content">${educationInfoVo.degree}</dd>
	</dl>
	<dl class="row">
		<dt class="row-title"><@language cn="专业描述" en="Description"/></dt>
		<dd class="row-content"><pre>${educationInfoVo.specialityDescription}</pre></dd>
	</dl>
</#list>
</div>
</#if>
<#if (trainInfoVoList)?exists && (trainInfoVoList?size>0)>
<div class="titBar">
	<h2><@language cn="培训经历" en="Training"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
<#list trainInfoVoList as trainInfoVo>
	<#if trainInfoVo_index != 0><hr /></#if>
	<p>${trainInfoVo.begin} <@dateEnd end="${trainInfoVo.end}" /></p>
	<p>
	${trainInfoVo.trainSchoolName} | ${trainInfoVo.trainCourse}   
	<#if (trainInfoVo.place)?exists && trainInfoVo.place !=""> | ${trainInfoVo.place}</#if>
	<#if (trainInfoVo.certificate)?exists && trainInfoVo.certificate !=""> | ${trainInfoVo.certificate}</#if>
	</p>
	<#if (trainInfoVo.courseDescription)?exists && trainInfoVo.courseDescription !="">
	<dl class="row">
		<dt class="row-title"><@language cn="详细描述" en="Description"/></dt>
		<dd class="row-content"><pre>${trainInfoVo.courseDescription}</pre></dd>
	</dl>
	</#if>
</#list>
</div>
</#if>
<#if (certificateInfoVoList)?exists && (certificateInfoVoList?size>0)>
<div class="titBar">
	<h2><@language cn="证书" en="Certificate"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
<#list certificateInfoVoList as certificateInfoVo>
	<#if certificateInfoVo_index != 0><hr /></#if>
	<p>${certificateInfoVo.acquireDate?string("yyyy/MM")} 
	${certificateInfoVo.certificateName}
	<#if (certificateInfoVo.grade)?exists && certificateInfoVo.grade !="">
	[${certificateInfoVo.grade}]
	</#if>
	</p>
	<#if (certificateInfoVo.certificateFileName)?exists && certificateInfoVo.certificateFileName !="">
		<p class="wordBreak"><a href="${certificateInfoVo.uploadImageUrl}" target="_blank">${certificateInfoVo.uploadImageUrl}</a></p>
	</#if>
</#list>
</div>
</#if>

<#if (languageInfoVoList)?exists && (languageInfoVoList?size>0)>
<div class="titBar">
	<h2><@language cn="语言能力" en="Language"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
	<#list languageInfoVoList as languageInfoVo>
		<#if languageInfoVo_index != 0><hr /></#if>
		<p>${languageInfoVo.skill}<@language cn="：" en=":"/>${languageInfoVo.level}</p>
	</#list>
</div>
</#if>
<#if (accessoryInfoVoList)?exists && (accessoryInfoVoList?size>0)>
<div class="titBar">
	<h2><@language cn="附件" en="Accessory"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
	<#list accessoryInfoVoList as accessoryInfoVo>
	<#if accessoryInfoVo_index != 0><hr /></#if>
	<p><a href="${accessoryInfoVo.uploadImageUrl}" target="_blank">${ accessoryInfoVo.uploadName}</a></p>
	<#if (accessoryInfoVo.opusPath)?exists && accessoryInfoVo.opusPath !="">
	<dl class="row">
		<dt class="row-title"><@language cn="作品链接地址" en="URL"/></dt>
		<dd class="row-content"><a href="${accessoryInfoVo.opusPath}" target="_blank">${accessoryInfoVo.opusPath}</a></dd>
	</dl>
	</#if>
	<#if (accessoryInfoVo.description)?exists && accessoryInfoVo.description !="">
	<dl class="row">
		<dt class="row-title"><@language cn="附件描述" en="Description"/></dt>
		<dd class="row-content"><pre>${accessoryInfoVo.description}</pre></dd>
	</dl>
	</#if>
	</#list>
</div>
</#if>

<#if (perUserVo.userName)?exists && perUserVo.userName !="">
<div class="titBar">
	<h2><@language cn="联系方式" en="Contact"/></h2>
	<span class="icon-arrow up"></span>
</div>
<div class="box-info">
	<dl class="row">
		<dt class="row-title"><@language cn="手机" en="Mobile"/></dt>
		<dd class="row-content"><@number num="${perUserVo.mobile}"/></dd>
	</dl>
	<#if (perUserVo.phone)?exists && perUserVo.phone !="">
	<dl class="row">
		<dt class="row-title"><@language cn="电话" en="Tel"/></dt>
		<dd class="row-content"><@number num="${perUserVo.phone}"/></dd>
	</dl>
	</#if>
	<#if (perUserVo.homepage)?exists && perUserVo.homepage !="">
	<dl class="row">
		<dt class="row-title"><@language cn="主页" en="Homepage"/></dt>
		<dd class="row-content wordBreak"><a href="${perUserVo.homepage}">${perUserVo.homepage}</a></dd>
	</dl>
	</#if>
	<dl class="row">
		<dt class="row-title"><@language cn="邮箱" en="E-mail"/></dt>
		<dd class="row-content">${perUserVo.email}</dd>
	</dl>
	<#if (perUserVo.zipcode)?exists && perUserVo.zipcode !="">
	<dl class="row">
		<dt class="row-title"><@language cn="邮编" en="Zip"/></dt>
		<dd class="row-content"><@number num="${perUserVo.zipcode}"/></dd>
	</dl>
	</#if>
	<#if (perUserVo.qq)?exists && perUserVo.qq !="">
	<dl class="row">
		<dt class="row-title"><@language cn="QQ" en="QQ"/></dt>
		<dd class="row-content">${perUserVo.qq}</dd>
	</dl>
	</#if>
	<#if (perUserVo.address)?exists && perUserVo.address !="">
	<dl class="row">
		<dt class="row-title"><@language cn="地址" en="Address"/></dt>
		<dd class="row-content"><@language cn="${perUserVo.address}" en="${perUserVo.addressEn}"/></dd>
	</dl>
	</#if>
</div>
</#if>

