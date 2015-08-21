package com.job5156.core.srv;

import com.job5156.core.bo.mail.MailBo;
import com.job5156.core.bo.mail.MailBo.MailAttachVo;
import com.job5156.core.bo.mail.MailBo.MailServer;
import com.job5156.core.common.Constants;
import com.job5156.core.common.MailConstants;
import com.job5156.core.common.helper.FreeMarkerHelper;
import com.job5156.core.common.util.ChineseCodeChangeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;
/**
 * <p>邮件核心业务类</p>
 * Date:2015/5/20 10:50
 *
 * @author pzm
 * @version 1.0
 */
@Service
public class MailSrv {

    @Resource
    private FreeMarkerHelper freeMarkerHelper;

    @Resource
    private JavaMailSender mailSender, mailExchangeSender;

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
        if (StringUtils.endsWith(fromAddr, "@job5156.com")) {//已备案通道，取消伪装发送 2014-10-16
            //fromAddr = "chitone" + RandomStringUtils.randomAlphanumeric(4) + "@job5156.com";
        }

        if (emailType.equals(MailConstants.MAIL_TYPE_TRADITIONAL)) {
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

    private JavaMailSender getMailSenderByServer(MailServer mailServer) {
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
