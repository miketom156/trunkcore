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
<div style="color:#333;font-family:宋体;font-size:12px;text-align:left; line-height:24px; padding:0px;margin:0 auto; ">
   	<div style="margin-bottom:10px;padding:5px 10px;font-size:14px;background:#efefef;">
   		<#if (intentInfoVo.keywords)?exists && intentInfoVo.keywords !="">
	   		<@language cn="简历关键字：" en="key:"/>${intentInfoVo.keywords}
	   	</#if>
   	</div>
<#if (perUserVo.userName)?exists>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:12px;">
	  <tr>
	    <td valign="top">
	    <h2 style="margin-bottom:10px;padding:0;height:24px;line-height:24px;font-size:24px;font-weight:bold;">
	    	<@language cn="${perUserVo.userName}" en="${perUserVo.userNameEn}"/>
	    	<span style="font-size:14px;">
	    	(<@language cn="编号：" en="No."/>${resumeNo})
	    	</span>
	    </h2>
            <#if posName?exists >
                <p><strong>应聘职位:</strong> ${posName}</p>
            </#if>
	    <div style="margin-bottom:10px;font-size:14px;color:#37c;line-height:24px;">
	      ${basicDataVo.jobyearType}<@language cn="经验" en=""/>
	      <#if (basicDataVo.highDegree)?exists && basicDataVo.highDegree !=""> | ${basicDataVo.highDegree} </#if>
	      | ${basicDataVo.gender} 
	      <#if (basicDataVo.marriage)?exists && basicDataVo.marriage !="" && basicDataVo.marriage !="保密" && basicDataVo.marriage !="Secret"> | ${basicDataVo.marriage} </#if>
	      | ${basicDataVo.age} <@language cn="岁" en="years"/>
	      <#if (perUserVo.stature)?exists && perUserVo.stature !=""> | ${perUserVo.stature}cm </#if>
	      <#if (basicDataVo.political)?exists && basicDataVo.political !=""> | ${basicDataVo.political} </#if>
	      <#if (perUserVo.nation)?exists && perUserVo.nation !=""> | <@language cn="${perUserVo.nation}" en="${perUserVo.nationEn}"/> </#if>
	      <#if (basicDataVo.nowSalary)?exists && basicDataVo.nowSalary !=""> | <@language cn="目前月薪：" en="Current Salary:"/>
	      	${basicDataVo.nowSalary}
	      	<@language cn="元/月" en="Yuan/month"/>
	      </#if>
	      <#if (intentInfoVo.jobCode)?exists && intentInfoVo.jobCode !=""> | <@language cn="期望职位：" en="Desired Position:"/>
	      	${intentInfoVo.jobCode}
	      </#if>
	    </div>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:12px;line-height:24px;">
		  <tr style="padding:5px 0;">
		    <td width="230">
		    	<@language cn="现居住地：" en="Residency:"/>
		    	${basicDataVo.location}
		    </td>
		    <td>
		    <#if (basicDataVo.hometown)?exists && basicDataVo.hometown !="">
			    	<@language cn="户口：" en="Hukou:"/>${basicDataVo.hometown}
		    </#if>
		    &nbsp;
		    </td>
		  </tr>
		  
			  <tr>
			    <td><@language cn="联系方式：" en="Contact:"/>
		    	${ perUserVo.mobile}
			    </td>
			    <td>
				<#if (perUserVo.cardType)?exists && perUserVo.cardType !="">
				<@language cn="${basicDataVo.cardType}：" en="${basicDataVo.cardType}:"/>
			    ${perUserVo.cardNum}
				</#if>
				</td>
			  </tr>
  			<#if (perUserVo.address)?exists && perUserVo.address !="">
  			<tr>
    			<td  colspan="2">
                    <@language cn="地址：" en="Address:"/>
			        <@language cn="${ perUserVo.address}" en="${ perUserVo.addressEn}"/>
                </td>
  			</tr>
  			</#if>
  			<tr>
    			<td colspan="2">
                    <@language cn="求职状态：" en="Current Situation:"/>
			        ${basicDataVo.jobState}
                </td>
  			</tr>
		</table>
	    </td>
    	<td width="140" align="right" valign="top">
    		<p style="color:#888; margin-bottom:10px;margin-right:8px;height:30px;line-height:30px;">${perUserVo.loginDate?string('yyyy-MM-dd')}</p>
	    	<#if (perUserVo.filePath)?exists && (perUserVo.fileName)?exists>
	    		<img src="${avatarUrl}" width="120" height="160">
	    	<#else>
	    		<img src="${imageUrl}/static/img/photoBlank.gif" width="120" height="160">
	    	</#if>
    	</td>
	    
	  </tr>
	</table>
</#if>
<#if (workInfoVoList)?exists || (educationInfoVoList)?exists>
	<hr size="1" noshade color="#eeeeee">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;">
		<tr>
		    <td width="300" valign="top" style="table-layout:fixed;word-break:break-all;">
		    <#if (educationInfoVoList)?exists>
		    	<div style="margin-bottom:10px;font-size:14px;color:#37c;">
		    		<@language cn="最高学历" en="Highest education"/>
				</div>
				<div style="font-size:12px;">
			    	<@language cn="学历：" en="Degree:"/>
			    	${basicDataVo.highDegree}<br />
					<#if (basicDataVo.speciality)?exists && basicDataVo.speciality != "">
						<@language cn="专业：" en="Major:"/>
						${basicDataVo.speciality}<br />
					</#if>
					<@language cn="学校：" en="School:"/>
					${basicDataVo.schoolName}
				</div>
			<#else>
				&nbsp;
			</#if>
			</td>
		    <td valign="top" style="table-layout:fixed;word-break:break-all;">
		    <#list workInfoVoList as workInfoVo>
				<#if workInfoVo_index = 0>
			    	<div style="margin-bottom:10px;font-size:14px;color:#37c;">
				    	<@language cn="最近工作" en="Recent work"/>
				    	<#if workInfoVo.timeDiff ? exists && workInfoVo.timeDiff != "">
				    	[${workInfoVo.timeDiff}]
				    	</#if>
			    	</div>
			    	<div style="font-size:12px;">
				    	<@language cn="公司：" en="Company:"/>
				    	${workInfoVo.comName} <br />
						<#if (workInfoVo.comCalling)?exists && workInfoVo.comCalling != "">
							<@language cn="行业： " en="Industry:"/>
							${workInfoVo.comCalling}<br />
						</#if>
						<#if (workInfoVo.jobName)?exists &&  workInfoVo.jobName != "">
							<@language cn="职位：" en="Job Title:"/> ${workInfoVo.jobName}
						</#if>
					</div>
				</#if>
		    </#list>
			</td>
	  	</tr>
	</table>
</#if>
<#if (intentInfoVo.jobCode)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="求职意向" en="Career Objective"/>
	</h3>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
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
		<#if (intentInfoVo.salary)?exists || (intentInfoVo.treatment)?exists>
		<tr>
		  	<td>
		  		<#if (intentInfoVo.salary)?exists || intentInfoVo.negotiable || (intentInfoVo.treatment)?exists ><@language cn="期望待遇：" en="Desired salary:"/></#if>
                <#if (intentInfoVo.salary)?exists && basicDataVo.nowSalary !="面议" && basicDataVo.nowSalary !="Negotiable">${intentInfoVo.salary}<@language cn="元/月" en="Yuan/month"/></#if>
        		<#if intentInfoVo.negotiable && (intentInfoVo.salary)?exists>(<@language cn="可面议" en="May be negotiable"/>)</#if>
                <#if (intentInfoVo.treatment)?exists>${intentInfoVo.treatment}</#if>
                <#if (intentInfoVo.otherMandate)?length gt 1><@language cn="其他要求：" en="Other Mandate:"/>${intentInfoVo.otherMandate}</#if>
		  	</td>
		</tr>
		</#if>
		<tr>
		  	<td>
			  	<@language cn="期望职位：" en="Desired Position:"/>
			  	${intentInfoVo.jobCode}
		  	</td>
		</tr>
		<#if (intentInfoVo.professionSkill)?exists>
		<tr>
		  	<td style="table-layout:fixed;word-break:break-all;">
		  		<@language cn="职业技能/自我评价：" en="Technical Expertise/Self Assessment:"/><br />
		  		${intentInfoVo.professionSkill?replace("\n","<br />")}
			</td>
		</tr>
		</#if>
	</table>
</#if>
<#if (workInfoVoList)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="工作经验/社会实践" en="Work Experience"/>
	</h3>
	<#list workInfoVoList as workInfoVo>
		<#if workInfoVo_index != 0><hr size="1" noshade color="#eeeeee"></#if>
		<div>${workInfoVo.begin} <@dateEnd end="${workInfoVo.end}" /></div>
		<div><strong>
			${workInfoVo.comName}
			<#if (workInfoVo.section)?exists && workInfoVo.section !="">| ${workInfoVo.section}</#if>
			<#if (workInfoVo.jobName)?exists && workInfoVo.jobName !="">| ${workInfoVo.jobName}</#if>		
		</strong></div>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
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
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
	    <tr>
	    <#if languageType = 0>
        	<td>
            	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="line-height:24px;font-size:12px;">
                	<tr>
                    	<td width="62">
                    		描&nbsp;&nbsp;&nbsp;&nbsp;述：
                    	</td>
                        <td style="table-layout:fixed;word-break:break-all;">${workInfoVo.description?replace("\n","<br />")}</td>
                    </tr>
                </table>
            </td>
        <#else>
	      <td style="table-layout:fixed;word-break:break-all;">
	      	Work description:<br />
	      	${workInfoVo.description?replace("\n","<br />")}
	      </td>
	    </#if>
	    </tr>
	  </table>
      <#if (workInfoVo.leftreason)?exists && workInfoVo.leftreason !="">
	  <div>
      	<@language cn="离职原因：" en="Reason for Quit:"/>
      	${workInfoVo.leftreason}
	  </div>
	  </#if>
	</#list>
</#if>
<#if (projectInfoVoList)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="项目经验" en="Project"/>
	</h3>
	<#list projectInfoVoList as projectInfoVo>
		  <#if projectInfoVo_index != 0><hr size="1" noshade color="#eeeeee"></#if>
		  <div>${projectInfoVo.begin} <@dateEnd end="${projectInfoVo.end}" /></div>
		  <div><strong>${projectInfoVo.projectName}</strong></div>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
		    <#if languageType = 0>
		    <tr>
		      <td>
		       <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
		        <tr>
		          <td width="62">
		          	项目描述：
		          </td>
		          <td style="table-layout:fixed;word-break:break-all;">${projectInfoVo.proDescribe?replace("\n","<br />")} </td>
		        </tr>
		        <tr>
		          <td>
		          	责任描述：
		          </td>
		          <td style="table-layout:fixed;word-break:break-all;">${projectInfoVo.dutyDescribe?replace("\n","<br />")}</td>
		        </tr>
		      </table>
		      </td>
		    </tr>
		    <#else>
		    <tr>
		    	<td style="table-layout:fixed;word-break:break-all;">Project Description:<br />${projectInfoVo.proDescribe?replace("\n","<br />")} </td>
		    </tr>
		    <tr>
		    	<td style="table-layout:fixed;word-break:break-all;">Responsibility:<br />${projectInfoVo.dutyDescribe?replace("\n","<br />")}</td>
		    </tr>
		    </#if>
		  </table>
	</#list>
</#if>
<#if (educationInfoVoList)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="教育经历" en="Education"/>
	</h3>
	<#list educationInfoVoList as educationInfoVo>
		<#if educationInfoVo_index != 0><hr size="1" noshade color="#eeeeee"></#if>
		<div>${educationInfoVo.begin} <@dateEnd end="${educationInfoVo.end}" /></div>
		<div><strong>
			${educationInfoVo.schoolName} |  
			<#if (educationInfoVo.speciality)?exists && educationInfoVo.speciality !="">${educationInfoVo.speciality} |</#if>
			${educationInfoVo.degree}
		</strong></div>
		<#if (educationInfoVo.specialityDescription)?exists && educationInfoVo.specialityDescription !="">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
		    <tr>
		    	<#if languageType = 0>
            	<td>
                	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="line-height:24px;font-size:12px;">
                    	<tr>
                        	<td width="62">
                        		专业描述：
                        	</td>
                            <td style="table-layout:fixed;word-break:break-all;">${educationInfoVo.specialityDescription?replace("\n","<br />")}</td>
                        </tr>
                    </table>
                </td>
            	<#else>
                <td style="table-layout:fixed;word-break:break-all;">Description:<br />${educationInfoVo.specialityDescription?replace("\n","<br />")}</td>
            	</#if>
		    </tr>
		</table>
		</#if>
	</#list>
</#if>
<#if (trainInfoVoList)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="培训经历" en="Training"/>
	</h3>
	<#list trainInfoVoList as trainInfoVo>
		  <#if trainInfoVo_index != 0><hr size="1" noshade color="#eeeeee"></#if>
		  <div>${trainInfoVo.begin} <@dateEnd end="${trainInfoVo.end}" /></div>
		  <div><strong>
		      ${trainInfoVo.trainSchoolName}  
		      | ${trainInfoVo.trainCourse}   
		      <#if (trainInfoVo.place)?exists && trainInfoVo.place !=""> | ${trainInfoVo.place}</#if>
		      <#if (trainInfoVo.certificate)?exists && trainInfoVo.certificate !=""> | ${trainInfoVo.certificate}</#if>
	      </strong></div>
	      <#if (trainInfoVo.courseDescription)?exists && trainInfoVo.courseDescription !="">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
		    <tr>
		    	<#if languageType = 0>
            	<td>
                	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="line-height:24px;font-size:12px;">
                    	<tr>
                        	<td width="62">
                        		详细描述：
                        	</td>
                            <td style="table-layout:fixed;word-break:break-all;">${trainInfoVo.courseDescription?replace("\n","<br />")}</td>
                        </tr>
                    </table>
                </td>
                <#else>
                <td style="table-layout:fixed;word-break:break-all;">Description:<br />${trainInfoVo.courseDescription?replace("\n","<br />")}</td>
                </#if>
		    </tr>
		</table>
		</#if>
	</#list>
</#if>
<#if (certificateInfoVoList)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="证书" en="Certificate"/>
	</h3>
	  <#list certificateInfoVoList as certificateInfoVo>
	  <#if certificateInfoVo_index != 0><hr size="1" noshade color="#eeeeee"></#if>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
  		<tr>
  			<td style="<#if certificateInfoVo_index != 0>border-top:solid 1px #ddd;</#if>padding-top:5px;">
				${certificateInfoVo.acquireDate?string("yyyy/MM")}
				${certificateInfoVo.certificateName}
				<#if (certificateInfoVo.grade)?exists && certificateInfoVo.grade !="">
				[${certificateInfoVo.grade}]
				</#if>
				<#if (certificateInfoVo.certificateFileName)?exists && certificateInfoVo.certificateFileName !="">
					<br /><a href="${certificateInfoVo.uploadImageUrl}">${certificateInfoVo.uploadImageUrl}</a>
				</#if>
			</td>
	    </tr>
	  </table>
	  </#list>
</#if>
<#if (languageInfoVoList)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="语言能力" en="Language"/>
	</h3>
	  <#list languageInfoVoList as languageInfoVo>
	  <#if languageInfoVo_index != 0><hr size="1" noshade color="#eeeeee"></#if>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
  		<tr>
	      <td width="160">${languageInfoVo.skill}</td>
	      <td>${languageInfoVo.level}</td>
	    </tr>
	  </table>
	  </#list>
</#if>
<#if (accessoryInfoVoList)?exists>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="附件" en="Accessory"/>
	</h3>
	  <#list accessoryInfoVoList as accessoryInfoVo>
	  	  <#if accessoryInfoVo_index != 0><hr size="1" noshade color="#eeeeee"></#if>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
		    <tr>
		      <td><a href="${accessoryInfoVo.uploadImageUrl}" target="_blank">${ accessoryInfoVo.uploadName}</a></td>
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
			      	${accessoryInfoVo.description?replace("\n","<br />")}
			      </td>
			    </tr>
		    </#if>
		  </table>
	  </#list>
</#if>
<#--hideContact用于判断是否隐藏联系人信息-->
<#if (perUserVo.userName)?exists && !hideContact>
	<h3 style="padding:5px 0;border-bottom:1px solid #dddddd;margin:10px 0;font-size:18px;font-weight:bold;">
		<@language cn="联系方式" en="Contact"/>
	</h3>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
	  <tr>
	    <td width="62" align="right">
	    	<@language cn="联系方式：" en="Contact:"/>
	    </td>
	    <td width="300">
	    	${perUserVo.mobile} <@language cn="（手机）" en="(Mobile)"/>
		    <#if (perUserVo.phone)?exists && perUserVo.phone !="">
		   	 ${perUserVo.phone} <@language cn="（电话）" en="(tel)"/>
		    </#if>
	    </td>
	    <#if (perUserVo.homepage)?exists && perUserVo.homepage !="">
	    <td width="62" align="right">
	    	<@language cn="主页：" en="Homepage:"/>
	    </td>
	    <td><a href="${perUserVo.homepage}">${perUserVo.homepage}</a></td>
	    <#else>
	    <td width="62" align="right">&nbsp;</td>
	    <td>&nbsp;</td>
	    </#if>
	  </tr>
	  <tr>
	    <td align="right"><@language cn="邮箱：" en="E-mail:"/></td>
	    <td><a href="mailTo:${perUserVo.email}">${perUserVo.email}</a></td>
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
	</table>
	<#if (perUserVo.address)?exists && perUserVo.address !="">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="line-height:24px;font-size:12px;">
	  <tr>
	    <td width="62" align="right">
	    	<@language cn="地址：" en="Address:"/>
	    </td>
	    <td><@language cn="${ perUserVo.address}" en="${ perUserVo.addressEn}"/></td>
	  </tr>
	</table>
	</#if>
</#if>
</div>
