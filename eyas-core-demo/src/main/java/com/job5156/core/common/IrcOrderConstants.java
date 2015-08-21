package com.job5156.core.common;

public class IrcOrderConstants {

    public static final class OrderStatus{
        public static final int CRE_WAIT4_PAYMENT = 1;
        public static final int PAYED_WAIT4_CONFIRM = 2;
        public static final int CANCELED = 3;
        public static final int CONFIRMED = 4;
        public static final int COMPLETED = 5;
    }
    
    public static String getOrderStatusStr(byte status){
        switch(status){
        case OrderStatus.CRE_WAIT4_PAYMENT :
            return "等待付款";
        case OrderStatus.PAYED_WAIT4_CONFIRM :
            return "已付款";
        case OrderStatus.CANCELED :
            return "订单已取消";
        case OrderStatus.CONFIRMED :
            return "订单已确认";
        case OrderStatus.COMPLETED :
            return "完成";
        }
        return null;
    }
    
    public static final class OrderType{
        public static final int CUS_IN_PERSON = 1;
        public static final int SALES_HELP = 2;
    }
    
    public static final class OrderItemStatus{
        public static final int CRE = 1;
        public static final int DELETE = 2;
    }
    
    
    public static final class CusType{
        /**
         *个人会员
         */
        public static final int JOB_SEEKER = 1; 
        /**
         * 企业会员
         */
        public static final int COMPANY = 2;
    }
    
}
