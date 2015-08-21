package com.job5156.core.bo.sms;


import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.webapp.model.com.ComPosition;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InviteTestWeiXinMsgBo extends WeiXinMsgBo {


    private String comName;
    private String posName;
    private Integer comId;
    private Date testDate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private ComPosition position;
    private String userName;

    public InviteTestWeiXinMsgBo(Integer comId, String comName, String userName, Date testDate, ComPosition position) {
         this.userName=userName;
        this.comName = comName;
        this.testDate = testDate;
        this.comId = comId;
        this.position = position;
    }

    @Override
    public String getContent() {
        return
                "<a href='http://www.job5156.com/corp/" + comId + "'>" + comName + "</a>"
                        + "诚邀您参加 "
                        + "<a href='http://www.job5156.com/jobs/" + KeyMaskUtil.posIdToNo(position.getId(), (short) 1) + "' >" + position.getPosName() + "</a>"
                        + "[" + position.getWorkLocationStr()
                        + "|" + position.getSalaryStr()
                        + "|" + position.getReqDegreeStr()
                        + "|" + position.getReqWorkYearStr() + "]"
                        + "的线上测评，请于" + format.format(testDate)
                        + "之前登录智通人才网(网页版)进行测评，过期将自动失效。";
    }


}