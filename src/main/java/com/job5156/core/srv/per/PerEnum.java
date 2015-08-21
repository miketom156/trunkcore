package com.job5156.core.srv.per;

/**
 * Function: 个人业务层共用的enum类
 *
 * @autor:LEETON
 * Date: 14-11-29
 * Time: 上午9:51
 */
public class PerEnum {

    /**
     *
     * 应聘提示信息枚举类
     * @author xh
     * @date 2014-5-7 上午10:12:52
     *
     */
    public enum ApplyErrType {
        /**
         * 未登陆
         */
        APPLY_FAILURE_UNLOGIN("apply_failure_unlogin", "请先登录再进行应聘操作"),
        /**
         * 为选择应聘简历
         */
        APPLY_FAILURE_RESUME_NO_SELECT("apply_failure_resume", "请选择应聘该职位的简历"),
        /**
         * 简历信息不存在
         */
        APPLY_FAILURE_RESUME_NOT_EXIST("apply_failure_resume", "简历信息不存在"),
        /**
         * 该简历不是当前用户的
         */
        APPLY_FAILURE_RESUME_NOT_OWNER("apply_failure_resume", "当前登录用户没有这份简历"),
        /**
         * 用户信息不存在
         */
        APPLY_FAILURE_USER_NOT_EXIST("apply_failure_resume", "用户信息不存在"),
        /**
         * 职位信息不存在
         */
        APPLY_FAILURE_POSITION_NOT_EXIST("apply_failure_position", "该职位信息不存在"),
        /**
         * 企业信息不存在
         */
        APPLY_FAILURE_COM_NOT_EXIST("apply_failure_position", "该企业不存在"),
        /**
         * 简历不完整
         */
        APPLY_FAILURE_RESUME_INCOMPLATE("apply_failure_non", ""),
        /**
         * 简历未通过审核
         */
        APPLY_FAILURE_RESUME_UNAUDIT("apply_failure_unaudit", "您的简历未审核通过，请提供真实的简历信息"),
        /**
         * 职位已停止招聘
         */
        APPLY_FAILURE_POSITION_STOP("apply_failure_stop", "该职位已经停止招聘了"),
        /**
         * 简历不符合要求
         */
        APPLY_FAILURE_RESUME_UNMATCH("apply_failure_unmatch", "应聘失败！此职位设置了应聘要求，很抱歉您的简历不符合该要求。"),
        /**
         * 黑名单限制
         */
        APPLY_FAILURE_RESUME_BLACK("apply_failure_black", "应聘失败！企业设置对您的简历进行了过滤，请选择其他企业。"),  //#1712
        /**
         * 30天内不能超过2次
         */
        APPLY_FAILURE_WARN_TWICE("apply_warn_twice", "同一职位30天内不能应聘超过2次"),
        /**
         * 今天已经应聘
         */
        APPLY_FAILURE_WARN_DUPLICATE("apply_warn_duplicate", "今天已经应聘过了,请不要重复提交!"),
        /**
         * 二次应聘
         */
        SECOND_APPLY("secondApply", ""),
        /**
         * 一天内应聘不能超过500次
         */
        APPLY_LIMIT("apply_limit", "一天内应聘不能超过500次"),

        /**
         * 3个月内应聘同一家公司最多只能应聘该公司的3个职位(取消2014-05-27）
         */
        APPLY_LIMIT_BY_POSTION("apply_limit", "3个月内应聘同一家公司最多只能应聘该公司的3个职位"),

        /**
         * 您的求职名片未完善，暂不能应聘
         */
        APPLY_CARD_NOT_PERFECT("apply_card_not_perfect", "您的求职名片未完善，暂不能应聘"),

        /**
         * 本职位不接收名片应聘，您可以进一步完善简历以升级为正式简历。
         */
        APPLY_NOT_ACCPET_CARD("apply_not_accpet_card", "本职位不接收微名片应聘，您可以进一步完善简历以升级为正式简历"),

        /**
         * 你的手机号未验证，暂不能应聘
         */
        APPLY_NOT_VERIFY_MOBILE("apply_not_verify_mobile", "你的手机号未验证，暂不能应聘"),

        /**
         * 应聘成功
         */
        APPLY_SUCCESS("success", "应聘成功"),

        /**
         * 应聘检查没有发现错误
         */
        APPLY_NOT_ERROR("blank","");

        private String errType;
        private String errMsg;

        public String getErrType() {
            return errType;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        private ApplyErrType(String errType, String errMsg) {
            this.errType = errType;
            this.errMsg = errMsg;
        }

        @Override
        public String toString() {
            return this.errMsg;
        }
    }
}
