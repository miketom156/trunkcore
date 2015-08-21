package com.job5156.core.srv;

import com.job5156.core.common.Constants;
import com.job5156.core.common.MailConstants;
import com.job5156.core.common.helper.FreeMarkerHelper;
import com.job5156.core.common.util.ChineseCodeChangeUtil;
import com.job5156.core.bo.mail.MailBo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import com.job5156.core.bo.mail.MailBo.*;
/**
 * <p>邮件核心业务类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Service
public class MailSrv {

    private static Logger logger = Logger.getLogger(MailSrv.class);

	@Resource
	private FreeMarkerHelper freeMarkerHelper;

	@Resource
	private JdbcTemplate jdbcTemplateCount;

    @Resource
    private JavaMailSender mailSender, mailExchangeSender;


	/**
	 * 发送邮件到邮件队列
	 * 
	 * @param vo
	 * @param taskId
	 */
    @SuppressWarnings("rawtypes")
	public void insertEmailQueue(MailBo vo, Integer taskId) {
        try {
            String content = freeMarkerHelper.generateContent(vo.getTemplate(), vo.getTemplateMap());
            Integer emailType = vo.getEmailType() == null ? MailConstants.MAIL_TYPE_SIMPLIFIED : vo.getEmailType();
            if (ArrayUtils.isNotEmpty(vo.getTo()) && StringUtils.isNotBlank(content)) {
                String sql = "INSERT INTO email_queue(title, from_addr, to_addr, from_name, task_id, content, email_type, create_date)"
                           + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                jdbcTemplateCount.update(sql, vo.getSubject(), vo.getFrom(),
                        StringUtils.join(vo.getTo(), MailConstants.MAIL_ADDRESS_SEPARATE_CHAR), vo.getFromName(), taskId, content,
                        emailType, new Date());
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error("  === INSEET DATA TO email_queue ON ERROR! === ");
        }
    }
    /**
     * 发送邮件
     *
     * @param vo 邮件信息
     * @return
     */
    public boolean send(MailBo vo) {
        boolean isSuccess = false;
        JavaMailSender sender = getMailSenderByVo(vo);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;

        String fromName = vo.getFromName();
        String fromAddr = vo.getFrom();
        String subject = vo.getSubject();

        Integer emailType = vo.getEmailType() == null ? MailConstants.MAIL_TYPE_SIMPLIFIED : vo.getEmailType();
        String content = freeMarkerHelper.generateContent(vo.getTemplate(), vo.getTemplateMap());
        if(StringUtils.endsWith(fromAddr, "@job5156.com")){//已备案通道，取消伪装发送 2014-10-16
            //fromAddr = "chitone" + RandomStringUtils.randomAlphanumeric(4) + "@job5156.com";
        }

        if(emailType.equals(MailConstants.MAIL_TYPE_TRADITIONAL)){
			/*fromName = FontConvert.gbkToBig5(fromName);
			subject = FontConvert.gbkToBig5(subject);
			content = FontConvert.gbkToBig5(content);*/

            fromName = ChineseCodeChangeUtil.toTraditional(fromName);
            subject = ChineseCodeChangeUtil.toTraditional(subject);
            content = ChineseCodeChangeUtil.toTraditional(content);
        }

        try {
            helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromAddr, fromName);
            helper.setTo(vo.getTo());
            helper.setSubject(subject);
            helper.setText(content, true);

            // 添加附件到邮件消息中
            MailAttachVo[] attachList = vo.getAttachList();
            if (attachList != null) {
                for (MailAttachVo attach : attachList) {
                    if (attach.getFile() == null)
                        continue;
                    helper.addAttachment(attach.getAttachName(), attach.getFile());
                }
            }
            // helper.addAttachment();
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            sender.send(message);
            isSuccess = true;
        } catch (Exception ex) {
            isSuccess = false;
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        return isSuccess;
    }

    @SuppressWarnings("rawtypes")
    private JavaMailSender getMailSenderByVo(MailBo vo) {
        return getMailSenderByServer(vo.getServer());
    }

    private JavaMailSender getMailSenderByTaskId(Integer taskId){
        Map<Integer, MailServer> mailServerMap = MailConstants.mailServerMap;
        if(mailServerMap.containsKey(taskId)){
            return getMailSenderByServer(mailServerMap.get(taskId));
        }
        return mailSender;
    }

    private JavaMailSender getMailSenderByServer(MailServer mailServer){
        switch (mailServer) {
            case MAIL_SERVER_MAIN:
                return mailSender;
            case MAIL_SERVER_EXCHANGE:
                return mailExchangeSender;
            case MAIL_SERVER_BUSINESS:
                return getRandomBusinessMailSender();
            default:
                return mailSender;
        }
    }

    private JavaMailSender getRandomBusinessMailSender() {
        Random random = new Random();
        int r = random.nextInt(Constants.MAIL_BUSINESS_SETTINGS.length - 1);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(Constants.MAIL_BUSINESS_SETTINGS[r][0]);
        mailSender.setUsername(Constants.MAIL_BUSINESS_SETTINGS[r][2]);
        mailSender.setPassword(Constants.MAIL_BUSINESS_SETTINGS[r][3]);
        Properties smtpAuthProperties = new Properties();
        smtpAuthProperties.setProperty("mail.smtp.auth", "true");
        mailSender.setJavaMailProperties(smtpAuthProperties);
        return mailSender;
    }
}
