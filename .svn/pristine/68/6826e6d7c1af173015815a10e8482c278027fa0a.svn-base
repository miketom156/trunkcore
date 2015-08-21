package com.job5156.core.bo.mail.talebase;


import com.job5156.core.bo.mail.MailBo;

import java.util.Date;

/**
 * 企业测评邮件VO
 * 
 * @author 夏安定
 * 
 */
public class TalebaseComTestMailBo extends MailBo<TalebaseComTestMailBo.PlaceHolderVo> {
	public MailServer getServer() {
		return MailServer.MAIL_SERVER_MAIN;
	}

	public String getTemplate() {
		return "/mail/com/talebase/talebaseComTestInvite.ftl";
	}

	public String getFrom() {
		return "no-reply@job5156.com";
	}

	public String getFromName() {
		return "智通人才网";
	}

	public String getSubject() {
		return "企业测评邀请邮件";
	}

	public TalebaseComTestMailBo(PlaceHolderVo vo, String[] to) {
		setTemplateMap(vo);
		setTo(to);
	}

	public static class PlaceHolderVo {
		private String comId;
		private String comName;
		private String username;
		private String posName;
		private Date date;
		private Date sendDate;

		public PlaceHolderVo(String comId, String comName, String username,String posName,Date date, Date sendDate) {
			this.comId = comId;
			this.comName = comName;
			this.username = username;
			this.posName = posName;
			this.date = date;
			this.sendDate = sendDate;
		}

		public String getComId() {
			return comId;
		}

		public String getComName() {
			return comName;
		}

		public String getUsername() {
			return username;
		}

		public String getPosName() {
			return posName;
		}

		public Date getDate() {
			return date;
		}

		public Date getSendDate() {
			return sendDate;
		}
	}
}
