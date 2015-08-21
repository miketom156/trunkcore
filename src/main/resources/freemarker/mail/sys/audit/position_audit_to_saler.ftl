<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#0174DF">
	<tr bgcolor="#FFFFFF" height="80" align="center">
		<td align="left" valign="middle">
			<div id="letter_6911862">
            <div style='margin-left: 30px; margin-right: 40px;'>尊敬的<font color="blue">${auditInfo.comPositionVo.comInfoVo.salerName}</font>（先生/女士），你好！<br/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;您的客户“<font color="red" id="comName_6911862">${auditInfo.comPositionVo.comInfoVo.comName}</font>”发布的“<font color="red">${auditInfo.comPositionVo.posName}</font>”职位存在不规范信息，
            <font>${auditInfo.tipContent}</font>
            </div>
            <div style="height: 10px;"></div>
            <div style='margin-left: 30px;'>
            	<table style="border: 1px solid; width: 95%;">
            		<tr bgcolor="#EFEFEF" align="center">
            			<td width="35%">职位名称</td>
                        <td>职位的问题与说明</td>
                    </tr>
                    <tr align="center">
                    	<td>${auditInfo.comPositionVo.posName}</td>
                    	<td>
                    		<ol style="list-style: decimal;">
                    			${auditInfo.reasonContent}
                    		</ol>
                    	</td>
                    </tr>
                </table>
            </div>
            <div style="height: 10px;"></div>
            <div style='margin-left: 30px;'>
            	<table style="border: 0px; width: 95%;">
                	<tr>
                        <td align="left">
                        	立即查看该职位：<a target="_blank" href="${siteUrl}/com/pos/preview/${auditInfo.comPositionVo.id?c}"><font color="blue">${siteUrl}/com/pos/preview/${auditInfo.comPositionVo.id?c}</font></a>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                        	智通人才网站客服:${user.name}
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                        	分机:${user.phone}
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            ${auditInfo.nowDate}
                        </td>
                    </tr>
                </table>
            </div>                     	
            </div>
        </td>
	</tr>            
</table>