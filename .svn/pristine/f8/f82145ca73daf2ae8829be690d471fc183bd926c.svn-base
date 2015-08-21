package com.job5156.core.bo.mail;

public class ApplyJobMailBo extends MailBo<ApplyJobMailBo.PlaceHolderVo> {

	@Override
	public MailServer getServer() {
		return MailServer.MAIL_SERVER_MAIN;
	}

	@Override
	public String getTemplate() {
		return "/mail/per/applyJob.ftl";
	}

	@Override
	public String getFrom() {
		return "no-reply@job5156.com";
	}

	@Override
	public String getFromName() {
		return "智通人才网";
	}

	@Override
	public String getSubject() {
		return this.subject;
	}

	public ApplyJobMailBo(PlaceHolderVo vo, String[] to, String subject) {
        setTemplateMap(vo);
        setTo(to);
        setSubject(subject);
    }
	
	public static class PlaceHolderVo {
		private String comName;
		private String posName;
		private Integer emailFormat;
        private String userName;
        private String resumeContentCn;
        private String resumeContentEn;

        public PlaceHolderVo(String comName, String posName,String userName, String resumeContentCn, String resumeContentEn) {
            this.userName = userName;
            this.resumeContentCn = resumeContentCn;
            this.resumeContentEn = resumeContentEn;
        }

        public String getComName() {
			return comName;
		}

		public void setComName(String comName) {
			this.comName = comName;
		}

		public String getPosName() {
			return posName;
		}

		public void setPosName(String posName) {
			this.posName = posName;
		}

		public Integer getEmailFormat() {
			return emailFormat;
		}

		public void setEmailFormat(Integer emailFormat) {
			this.emailFormat = emailFormat;
		}

		public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getResumeContentCn() {
            return resumeContentCn;
        }

        public void setResumeContentCn(String resumeContentCn) {
            this.resumeContentCn = resumeContentCn;
        }

        public String getResumeContentEn() {
            return resumeContentEn;
        }

        public void setResumeContentEn(String resumeContentEn) {
            this.resumeContentEn = resumeContentEn;
        }
    }
}
