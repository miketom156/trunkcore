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
<#--已屏蔽-->
<#macro hidden>
     <@language en="(Has shielded)" cn="(已屏蔽)" />
</#macro>
<#--公司名称已屏蔽-->
<#macro comNameHidden>
     <@language en="(the company name has been screened)" cn="(公司名称已屏蔽)" />
</#macro>
<#--购买简历-->
<#macro  tobuy >
     <@language en="To see person contact" cn="查看联系方式" />
</#macro>
<#-- 手机，身体证等号码 -->
<#macro number num>
    <#if num !="">
      ${num}
    <#else>
      <@language en="none" cn="无" />
    </#if>
</#macro>

<div class="head">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td style="padding:20px 0 0 20px;">
				<div class="logo">
					<a href="${siteUrl}" target="_blank" title="智通人才">
						<img src="${imageUrl}/static/style/v1/images/logo.jpg" alt="" name="" width="265" height="40" />
					</a>
				</div>
			</td>
			<td class="btnTool" ></td>
		</tr>
	</table>
	<div class="keyWordBar">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td style="background:#efefef;font-size:14px; padding:5px 10px;">
			<#if (intentInfoVo.keywords)?exists && intentInfoVo.keywords !="">
			<@language cn="简历关键字：" en="key:"/>${intentInfoVo.keywords}
			</#if>
			</td>
		</tr>
	</table>
	</div>
</div>
<div class="infoBlock">
<#if (perUserVo.userName)?exists && perUserVo.userName !="">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
	    	<td style="padding:10px 20px;">
				<h2>
					<@language cn="${perUserVo.userName}" en="${perUserVo.userNameEn}"/>
					<span>(<@language cn="编号：" en="No."/>${resumeNo})</span>
				</h2>
				<#if posName?exists && (modType=="" || modType=="receive") >
				   <p><strong>应聘职位:</strong> ${posName}</p>
				<#elseif posName?exists && (modType=="" || modType=="recommand") >
				   <p><strong>推荐职位:</strong> ${posName}</p>
				</#if>
                <#if posName?exists && modType == "transmit" >
                    <p><strong>对应职位:</strong> ${posName}</p>
                </#if>
	    		<p class="lightFont">
	      			${basicDataVo.jobyearType}<@language cn="经验" en=""/>
	      			<#if (basicDataVo.highDegree)?exists && basicDataVo.highDegree !=""> | ${basicDataVo.highDegree} </#if>
	      			| ${basicDataVo.gender} 
	      			<#if (basicDataVo.marriage)?exists && basicDataVo.marriage !="" && basicDataVo.marriage !="保密" && basicDataVo.marriage !="Secret"> | ${basicDataVo.marriage} </#if>
	      			| ${basicDataVo.age} <@language cn="岁" en="years"/>
	      			<#if (perUserVo.stature)?exists && perUserVo.stature !=""> | ${perUserVo.stature}cm </#if>
	      			<#if (basicDataVo.political)?exists && basicDataVo.political !=""> | ${basicDataVo.political} </#if>
	      			<#if (perUserVo.nation)?exists && perUserVo.nation !=""> | <@language cn="${perUserVo.nation}" en="${perUserVo.nationEn}"/> </#if>
					<#if (basicDataVo.nowSalary)?exists && basicDataVo.nowSalary !=""> | <@language cn="目前月薪：" en="Current Salary:"/>
						${basicDataVo.nowSalary}(<@language cn="元/月" en="Yuan/month"/>)
					</#if>
	      			<#if (intentInfoVo.jobCode)?exists && intentInfoVo.jobCode !=""> | <@language cn="期望职位：" en="Desired Position:"/>
	      			${intentInfoVo.jobCode}</#if>
	    		</p>
	    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  			<tr>
		    			<td width="230">
		    				<@language cn="现居住地：" en="Residency:"/>
		    				${basicDataVo.location}
		    			</td>
		    			<td>
		    				<#if (basicDataVo.hometown)?exists && basicDataVo.hometown !="">
			    				<@language cn="户口：" en="Hukou:"/>${basicDataVo.hometown}
		    				</#if>
		    			</td>
		  			</tr>
					<tr>
                        <td>
							<@language cn="求职状态：" en="Current Situation:"/>
					        ${basicDataVo.jobState}
                        </td>
                        <td>
							<#if (perUserVo.cardType)?exists && perUserVo.cardType !="" && perUserVo.cardType gt 0 >
								<@language cn="${basicDataVo.cardType}：" en="${basicDataVo.cardType}:"/>
                                <span id="cardNum_com.job5156:eyas-core:jar:1.0">
									<#if loginFlag && buyFlag>
                                  <@number num="${perUserVo.cardNum}" />
                                <#else>
										<@hidden />
									</#if>
                                </span>
							</#if>
                        </td>
					</tr>
			  		  <tr>
                          <td>
                              <@language cn="联系方式：" en="Contact:"/>
                              <span id="mobile_com.job5156:eyas-core:jar:1.0">
                                  <#if loginFlag && buyFlag >
                                      <@number num="${perUserVo.mobile}" />
                                      <span data-act="attr" data-id="com.job5156:eyas-core:jar:1.0" data-tel="${perUserVo.mobile}">${(perUserVo.mobilePlace)!}</span>
                                  <#else>
                                     <span id="tel"><a class="button" href="#"  data-act="buy" data-id="com.job5156:eyas-core:jar:1.0" ><@tobuy/></a></span><span id="attr" data-id="" data-tel=""></span>
                                  </#if>
                                  <#if (perUserVo.mobileActivation)==1>
                                      <span style="float:right;" class="testIcon" title="已通过验证"><i class="m"></i></span>
                                  </#if>
                              </span>
                          </td>
						  <td>
                          <a href="#" id="feedback"
							  <#if loginFlag && buyFlag >
								data-act="1"
							</#if>
                              >若手机号异常，反馈属实5倍返点>></a>
						  </td>
			  	    </tr>
					<#if (perUserVo.address)?exists && perUserVo.address !="" && loginFlag && addressShowFlag>
		  			<tr>
						<td   colspan="2">
							<@language cn="地址：" en="Address:"/>
						<@language cn="${ perUserVo.address}" en="${ perUserVo.addressEn}"/>
						</td>
		  			</tr>
					</#if>
					<#if (workInfoVoList)?exists || (educationInfoVoList)?exists>
					<tr>
		    			<td width="300" class="td-wordBreak" style="border-top:solid 1px #ccc; padding-top:5px;">
						<#if (educationInfoVoList)?exists && (educationInfoVoList?size>0)>
		    				<strong class="lightFont"><@language cn="最高学历" en="Highest education"/></strong><br />
		    				<@language cn="学历：" en="Degree:"/>
		    				${basicDataVo.highDegree}<br />
							<#if (basicDataVo.speciality)?exists && basicDataVo.speciality != "">
								<@language cn="专业：" en="Major:"/>
								${basicDataVo.speciality}<br />
							</#if>
							<@language cn="学校：" en="School:"/>
							${basicDataVo.schoolName}
						<#else>
							&nbsp;
						</#if>
						</td>
		    			<td class="td-wordBreak" style="border-top:solid 1px #ccc; padding-top:5px;">
		    			<#list workInfoVoList as workInfoVo>
							<#if workInfoVo_index == 0>
								<strong class="lightFont">
									<@language cn="最近工作" en="Recent work"/>
									<#if workInfoVo.timeDiff ? exists && workInfoVo.timeDiff != "">
										[${workInfoVo.timeDiff}]
									</#if>
								</strong><br />
								
								<@language cn="公司：" en="Company:"/>
								<span id="recent_comname_com.job5156:eyas-core:jar:1.0">
								<#if ((loginFlag && buyFlag) || (loginFlag && isSysUser)) >
									${workInfoVo.comName}
								<#else>
								<@comNameHidden />
								</#if>
								</span>
								<br/>
                                <#if (workInfoVo.jobName)?exists &&  workInfoVo.jobName != "">
                                    <@language cn="职位：" en="Job Title:"/> ${workInfoVo.jobName}<br/>
                                </#if>
								<#if (workInfoVo.comCalling)?exists && workInfoVo.comCalling != "">
									<@language cn="行业： " en="Industry:"/>
									${workInfoVo.comCalling}
								</#if>
							</#if>
						</#list>
						</td>
					</tr>
					</#if>
				</table>
	    	</td>
			<td width="140" align="right" style="padding-top:10px;padding-right:20px;vertical-align:top;">
			<p class="upDate">${perUserVo.loginDate?string('yyyy-MM-dd')}</p>
			<#if loginFlag>
	    	  <#if (perUserVo.filePath)?exists && (perUserVo.fileName)?exists  >
	    	    <img src="${avatarUrl}" width="120" height="160"/>
	    	  <#else>
                  <#if perUserVo.gender == 1 >
                      <img src="${imageUrl}/static/img/photoMale.gif"  width="120" height="160" />
                  </#if>
                  <#if perUserVo.gender == 2 >
                      <img src="${imageUrl}/static/img/photoFemale.gif"  width="120" height="160" />
                  </#if>
	    	  </#if>
			<#else>
			    <img id="avatar_com.job5156:eyas-core:jar:1.0" src="${imageUrl}/static/img/photoScreen.gif" width="120" height="160"/>
			</#if>
			
			</td>
	  	</tr>
	</table>
</#if>
</div>
<#if (intentInfoVo.jobCode)?exists && intentInfoVo.jobCode !="">
<div class="itemBlock">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="titBar" style="margin-top:10px;padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
            	<h3><@language cn="求职意向" en="Career Objective"/></h3>
            </td>
        </tr>
		<tr>
		    <td>
		    	<@language cn="到岗时间：" en="I can start:"/>
		    	${intentInfoVo.checkindate}
		    </td>
		</tr>
		<tr>
			<td>
				<@language cn="工作类型：" en="Type of Employment:"/>
				${intentInfoVo.jobType}
			</td>
		</tr>
		<tr>
			<td>
				<@language cn="期望地区：" en="Desired Location:"/>
				${intentInfoVo.jobLocation}
			</td>
		</tr>
		<tr>
		  	<td>
                <#if (intentInfoVo.salary)?exists || intentInfoVo.negotiable || (intentInfoVo.treatment)?exists ><@language cn="期望待遇：" en="Desired salary:"/></#if>
                <#if (intentInfoVo.salary)?exists >${intentInfoVo.salary}</#if>
                <#if (intentInfoVo.salary)?exists && intentInfoVo.salary !="面议" && intentInfoVo.salary !="Negotiable"><@language cn="元/月" en="Yuan/month"/></#if>
                <#if (intentInfoVo.negotiable)?exists && intentInfoVo.negotiable==true>(<@language cn="可面议" en="May be negotiable"/>)</#if>
                <#if (intentInfoVo.treatment)?exists>${intentInfoVo.treatment}</#if>
                <#if (intentInfoVo.otherMandate)?length gt 1><@language cn="其他要求：" en="Other Mandate:"/>${intentInfoVo.otherMandate}</#if>
		  	</td>
		</tr>
		<tr>
		  	<td>
			  	<@language cn="期望职位：" en="Desired Position:"/>
			  	${intentInfoVo.jobCode}
		  	</td>
		</tr>
		<#if (intentInfoVo.professionSkill)?exists>
		<tr>
		  	<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="td-wordBreak">
						<@language cn="职业技能/自我评价：" en="Technical Expertise/Self Assessment:"/><br />
		  				<pre>${intentInfoVo.professionSkill}</pre>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</#if>
	</table>
</div>
</#if>
<#if (workInfoVoList)?exists && (workInfoVoList?size>0)>
<div class="itemBlock">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
	    	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
	    		<h3><@language cn="工作经验/社会实践" en="Work Experience"/></h3>
	    	</td>
	  	</tr>
	<#list workInfoVoList as workInfoVo>
		<tr>
		  	<td style="<#if workInfoVo_index != 0>border-top:solid 1px #ddd;</#if>padding-top:5px;"><strong>
			  	<#if workInfoVo.begin?exists> ${workInfoVo.begin} <@dateEnd end="${workInfoVo.end}" /> | </#if>
			  	<span id="comName_com.job5156:eyas-core:jar:1.0${workInfoVo_index}">
                    <#if loginFlag ><#if workInfoVo_index == 0 && !buyFlag && !isSysUser><@comNameHidden /><#else>${workInfoVo.comName}</#if><#else>
                        <@comNameHidden />
                    </#if>
                </span>
			  	<#if (workInfoVo.section)?exists && workInfoVo.section !="">| ${workInfoVo.section}</#if>
			  	<#if (workInfoVo.jobName)?exists && workInfoVo.jobName !="">| ${workInfoVo.jobName}</#if>
		   	</strong></td>
		</tr>
		<tr>
		  	<td>
				<#if (workInfoVo.comCalling)?exists>
					<@language cn="行业类别：" en="Industry:"/>
					${workInfoVo.comCalling}	
				</#if>
				<#if (workInfoVo.comType)?exists>
					 | <@language cn="性质：" en="Company Type:"/>${workInfoVo.comType}
				</#if>
				<#if (workInfoVo.comScale)?exists>
					 | <@language cn="规模：" en="Company Size:"/>${workInfoVo.comScale}
					<@language cn="人" en="people"/>
				</#if>
			</td>
		</tr>
		<#if (workInfoVo.description)?exists && workInfoVo.description !="">
		<tr>
		<#if languageType = 0>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="70">
							描　  述：
						</td>
						<td class="td-wordBreak"><pre>${workInfoVo.description}</pre></td>
					</tr>
				</table>
			</td>
		<#else>
		  	<td class="td-wordBreak">
				Work description:<br/>
				<pre>${workInfoVo.description}</pre>
		  	</td>
		</#if>
		</tr>
		</#if>
		<#if (workInfoVo.leftreason)?exists && workInfoVo.leftreason !="">
		<tr>
		  	<td class="td-wordBreak">
				<@language cn="离职原因：" en="Reason for Quit:"/>
				${workInfoVo.leftreason}
		  	</td>
		</tr>
		</#if>
	</#list>
	</table>
</div>
</#if>
<#if (projectInfoVoList)? exists && (projectInfoVoList?size>0)>
<div class="itemBlock">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
	    	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
	    		<h3><@language cn="项目经验" en="Project"/></h3>
	    	</td>
	  	</tr>
	<#list projectInfoVoList as projectInfoVo>
		<tr>
		  	<td style="<#if projectInfoVo_index != 0>border-top:solid 1px #ddd;</#if>padding-top:5px;"><strong>${projectInfoVo.projectName}(${projectInfoVo.begin} <@dateEnd end="${projectInfoVo.end}" />)</strong></td>
		</tr>
		<#if languageType = 0>
		<tr>
		  	<td style="padding:0 20px;">
		  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			  			<td width="70" align="right">项目描述：</td>
			  			<td class="td-wordBreak"><pre>${projectInfoVo.proDescribe}</pre></td>
					</tr>
					<tr>
			  			<td align="right">责任描述：</td>
			  			<td class="td-wordBreak"><pre>${projectInfoVo.dutyDescribe}</pre></td>
					</tr>
		  		</table>
		  	</td>
		</tr>
		<#else>
		<tr>
			<td class="td-wordBreak">Project Description:<br /><pre>${projectInfoVo.proDescribe}</pre></td>
		</tr>
		<tr>
			<td class="td-wordBreak">Responsibility:<br /><pre>${projectInfoVo.dutyDescribe}</pre></td>
		</tr>
		</#if>
	</#list>
	</table>
</div>
</#if>
<#if (educationInfoVoList)?exists && (educationInfoVoList?size>0)>
<div class="itemBlock">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
	    	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
	    		<h3><@language cn="教育经历" en="Education"/></h3>
	    	</td>
	  	</tr>
	<#list educationInfoVoList as educationInfoVo>
		<tr>
		  	<td style="<#if educationInfoVo_index != 0>border-top:solid 1px #ddd;</#if>padding-top:5px;"><strong>
				${educationInfoVo.begin} <@dateEnd end="${educationInfoVo.end}" /> |
			  	${educationInfoVo.schoolName} |  
			  	<#if (educationInfoVo.speciality)?exists && educationInfoVo.speciality !="">${educationInfoVo.speciality} |</#if>
			  	${educationInfoVo.degree}
		  	</strong></td>
		</tr>
		<#if (educationInfoVo.specialityDescription)?exists && educationInfoVo.specialityDescription !="">
		<tr>
			<#if languageType = 0>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="70" align="right">
							专业描述：
						</td>
						<td class="td-wordBreak"><pre>${educationInfoVo.specialityDescription}</pre></td>
					</tr>
				</table>
			</td>
			<#else>
			<td class="td-wordBreak">Description:<br /><pre>${educationInfoVo.specialityDescription}</pre></td>
			</#if>
		</tr>
		</#if>
	</#list>
	</table>
</div>
</#if>
<#if (trainInfoVoList)?exists && (trainInfoVoList?size>0)>
<div class="itemBlock">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
	    	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
		    	<h3><@language cn="培训经历" en="Training"/></h3>
	    	</td>
	  	</tr>
	<#list trainInfoVoList as trainInfoVo>
		<tr>
			<td style="<#if trainInfoVo_index != 0>border-top:solid 1px #ddd;</#if>padding-top:5px;">
				<strong>
			  	${trainInfoVo.begin} <@dateEnd end="${trainInfoVo.end}" />
			      ${trainInfoVo.trainSchoolName}  
			      | ${trainInfoVo.trainCourse}   
			      <#if (trainInfoVo.place)?exists && trainInfoVo.place !=""> | ${trainInfoVo.place}</#if>
			      <#if (trainInfoVo.certificate)?exists && trainInfoVo.certificate !=""> | ${trainInfoVo.certificate}</#if>
		      </strong>
		    </td>
		</tr>
		<#if (trainInfoVo.courseDescription)?exists && trainInfoVo.courseDescription !="">
		<tr>
			<#if languageType = 0>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="70" align="right">详细描述：</td>
						<td class="td-wordBreak"><pre>${trainInfoVo.courseDescription}</pre></td>
					</tr>
				</table>
			</td>
			<#else>
			<td class="td-wordBreak" style="padding:0 20px;">Description:<br /><pre>${trainInfoVo.courseDescription}</pre></td>
			</#if>
		</tr>
		</#if>
	</#list>
	</table>
</div>
</#if>
<#if (certificateInfoVoList)?exists && (certificateInfoVoList?size>0)>
<div class="itemBlock" id="certificate_com.job5156:eyas-core:jar:1.0"<#if !buyFlag> style="display:none"</#if>>
	<#if loginFlag && buyFlag >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
	      		<h3><@language cn="证书" en="Certificate"/></h3>
	      	</td>
	    </tr>
		<#list certificateInfoVoList as certificateInfoVo>
		<tr>
			<td style="<#if certificateInfoVo_index != 0>border-top:solid 1px #ddd;</#if>padding-top:5px;">
				${certificateInfoVo.acquireDate?string("yyyy/MM")}&nbsp;&nbsp;
				${certificateInfoVo.certificateName}
				<#if (certificateInfoVo.grade)?exists && certificateInfoVo.grade !="">
				[${certificateInfoVo.grade}]
				</#if>
				<#if (certificateInfoVo.certificateFileName)?exists && certificateInfoVo.certificateFileName !="">
					<br /><a href="${certificateInfoVo.uploadImageUrl}" target="_blank">${certificateInfoVo.uploadImageUrl}</a>
				</#if>
			</td>
	    </tr>
	  	</#list>
  	</table>
  	</#if>
</div>
</#if>
<#if (languageInfoVoList)?exists && (languageInfoVoList?size>0)>
<div class="itemBlock">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
	      		<h3><@language cn="语言能力" en="Language"/></h3>
	      	</td>
	    </tr>
  		<tr>
	      	<td style="padding-top:5px;">
			<#list languageInfoVoList as languageInfoVo>
				<#if languageInfoVo_index != 0><span style="margin:0 20px;">|</span></#if>
				${languageInfoVo.skill}<@language cn="：" en=":"/>${languageInfoVo.level}
			</#list>
			</td>
	    </tr>
	</table>
</div>
</#if>
<#if (accessoryInfoVoList)?exists && (accessoryInfoVoList?size>0)>
<div class="itemBlock" id="accessory_com.job5156:eyas-core:jar:1.0"<#if !buyFlag> style="display:none"</#if>>
<#if loginFlag && buyFlag >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
	      		<h3><@language cn="附件" en="Accessory"/></h3>
	      	</td>
	    </tr>
	<#list accessoryInfoVoList as accessoryInfoVo>
	    <tr>
		  	<td style="<#if accessoryInfoVo_index != 0>border-top:solid 1px #ddd;</#if>padding-top:5px;"> 
		  		<a href="${accessoryInfoVo.uploadImageUrl}" target="_blank">${ accessoryInfoVo.uploadName}</a>
		  	</td>
		</tr>
		<#if (accessoryInfoVo.opusPath)?exists && accessoryInfoVo.opusPath !="">
		<tr>
			<td>
				<@language cn="作品链接地址：" en="URL:"/><a href="${accessoryInfoVo.opusPath}" target="_blank">${accessoryInfoVo.opusPath}</a></td>
		</tr>
		</#if>
		<#if (accessoryInfoVo.description)?exists && accessoryInfoVo.description !="">
		<tr>
			<td>
				<@language cn="附件描述：" en="Description:<br />"/>
				<pre>${accessoryInfoVo.description}</pre>
			</td>
		</tr>
		</#if>
  	</#list>		
	</table>
</#if>
</div>
</#if> 

<div class="itemBlock">
<#if (perUserVo.userName)?exists && perUserVo.userName !="">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
	    	<td class="titBar" style="padding:5px 20px;border-top:1px solid #666666;background:#efefef;">
	    		<h3><@language cn="联系方式" en="Contact"/></h3>
	    	</td>
	  	</tr>
	  	<tr>
	    	<td id ="contact_com.job5156:eyas-core:jar:1.0">
	    		<#if !buyFlag >
				    <a class="button" href="#"  data-act="buy" data-id="com.job5156:eyas-core:jar:1.0" ><@tobuy/></a>
				</#if>
	    		<#if loginFlag && buyFlag >
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  		<tr>
						<td width="70" align="right"><@language cn="联系方式：" en="Contact:"/></td>
						<td width="350">
							<span style="float:left"> ${perUserVo.mobile} <@language cn="（手机）" en="(Mobile)"/></span>
							<span style="float:left" data-act="attr" data-id="com.job5156:eyas-core:jar:1.0" data-tel="${perUserVo.mobile}">${(perUserVo.mobilePlace)!}</span>
							<#if (perUserVo.phone)?exists && perUserVo.phone !="">
								${perUserVo.phone} <@language cn="（电话）" en="(tel)"/>
							</#if>
                            <#if (perUserVo.mobileActivation)==1>
                                <span class="testIcon" title="已通过验证"><i class="m"></i></span>
                            </#if>
						</td>
						<#if (perUserVo.homepage)?exists && perUserVo.homepage !="">
						<td width="70" align="right" class="td-wordBreak"><@language cn="主页：" en="Homepage:"/></td>
						<td><a href="${perUserVo.homepage}">${perUserVo.homepage}</a></td>
						<#else>
						<td width="70" align="right">&nbsp;</td>
						<td>&nbsp;</td>
						</#if>
			  		</tr>
			  		<tr>
						<td align="right"><@language cn="邮箱：" en="E-mail:"/></td>
						<td>
						<a style="float:left;" href="mailTo:${perUserVo.email}">${perUserVo.email}</a>
                        <#if (perUserVo.mailActivation)==1>
                            <span class="testIcon" title="已通过验证"><i class="e"></i></span>
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
				</#if>
			</td>
	  	</tr>
	</table>
</#if>
</div>