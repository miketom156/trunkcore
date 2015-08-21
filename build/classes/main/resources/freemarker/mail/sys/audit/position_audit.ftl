<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#0174DF">
	<tr bgcolor="#FFFFFF" height="80" align="center">
		<td align="left" valign="middle">
			<input type="hidden" id="posName_${pos.id}" value="${pos.posName}" />
			<br />
			<div id="letter_6911862">
            <div style='margin-left: 30px; margin-right: 40px;'>尊敬的<font color="blue">${pos.comInfo.comName}</font>公司<br/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;发现您最近发布的职位中有需要继续完善的地方，具体职位及完善建议如下：
            </div>
            <div style="height: 10px;"></div>
            <div style='margin-left: 30px;'>
            	<table style="border: 1px solid; width: 95%;">
            		<tr bgcolor="#EFEFEF" align="center">
            			<td width="35%">职位名称</td>
                        <td>职位的问题与说明</td>
                    </tr>
                    <tbodyalign="center">${reasonContent}</tbody>
                </table>
            </div>
            <div>请您尽快在两天内完成调整完善，否则职位将会自动屏蔽。如有疑问，请联系您的招聘顾问。</div>
            <div style="height: 10px;"></div>
            <div style='margin-left: 30px;'>
            	<table style="border: 0px; width: 95%;">
                	<tr>
                        <td align="left">
                        	立即查看该职位：<a target="_blank" href="${siteUrl}/com/pos/preview/${posNo}"><font color="blue">${siteUrl}/com/pos/preview/${posNo}</font></a>
                        </td>
                    </tr>
                </table>
            </div>                     	
            </div>
        </td>
	</tr>            
</table>