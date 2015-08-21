package com.job5156.core.bo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Date:2015/5/14 17:16
 *
 * @author pzm
 * @version 1.0
 */
public class BizRetBo<T> {

    private BizRetCode retCode; //1:成功，0：失败，-1：未登录
    private String ref;  //页面跳转地址

    private T item;//返回对象
    private Collection<T> items;//返回对象列表
    private Map<String,String> msgParams = new HashMap<>();
    public BizRetBo() {
    }

    public BizRetBo(BizRetCode retCode) {
        this(retCode, null, null, null);
    }
    public BizRetBo(BizRetCode retCode, String msgParamName, String msgParamValue) {
        this(retCode, null, null, null);
        addMsgParam(msgParamName,msgParamValue);
    }

    public BizRetBo(BizRetCode retCode, T item) {
        this(retCode, null, item, null);
    }

    public BizRetBo(BizRetCode retCode, Collection<T> items) {
        this(retCode, null, null, items);
    }

    public BizRetBo(BizRetCode retCode, String ref) {
        this(retCode, ref, null, null);
    }

    public BizRetBo(BizRetCode retCode, String ref, T item, Collection<T> items) {
        this.retCode = retCode;
        this.ref = ref;
        this.item = item;
        this.items = items;
    }

    public BizRetCode getRetCode() {
        return retCode;
    }

    public String getRetCodeVal() {
        return retCode.getRetCode();
    }

    public void setRetCode(BizRetCode retCode) {
        this.retCode = retCode;
    }

    public void addMsgParam(String paramName,String paramValue){
        msgParams.put(paramName,paramValue);
    }

    public String getMsg() {
        String retMsg = retCode.getMsg();
        for(Map.Entry<String, String> paramEntry : msgParams.entrySet()){
            retMsg = retMsg.replaceAll("${" + paramEntry.getKey() + "}", paramEntry.getValue());
        }
        return retMsg;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}