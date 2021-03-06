package com.job5156.core.common.helper;

import com.google.common.collect.Maps;
import com.job5156.core.common.Constants;
import com.job5156.core.eao.RedisEao;
import com.job5156.core.eao.per.PerUserConnectEao;
import com.job5156.webapp.model.per.PerUserConnect;
import com.job5156.core.bo.sms.WeiXinMsgBo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.jsoup.helper.StringUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class WeiXinApiHelper {

    @Resource
    private RedisEao redisEao;

    private static Logger logger = Logger.getLogger(WeiXinApiHelper.class);


    @Resource
    private PerUserConnectEao perUserConnectEao;
    public static HttpClient httpClient;

    static {
        HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(5000);
        params.setSoTimeout(20000);
        params.setDefaultMaxConnectionsPerHost(50); //设置最大连接数(每主机)
        params.setMaxTotalConnections(50); //最大连接数(总)
        httpClient = new HttpClient(httpConnectionManager);
        httpClient.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
        httpClient.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
        httpClient.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
        httpClient.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, HTTP.UTF_8);
        httpClient.getHttpConnectionManager().closeIdleConnections(0);
    }


    /**
     * 用于获取微信的accesstoken
     * 由于重新获取会导致上一次获取的失效
     * 所以应该使用同一的方法获取（微信服务号外包方的接口）
     * 只是测试用
     *
     * @return
     */
    public String getAccessToken() {
        //------------------------------------------获取accesstoken
        GetMethod getMethod = null;
        String accessToken = "";
        try {
            getMethod = new GetMethod(Constants.WEIXIN_API_GETACESSTOKEN
                    + "&appid=" + Constants.WEIXIN_APP_ID + "&secret=" + Constants.WEIXIN_APP_SECRET);
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return "";
            }
            byte[] res = getMethod.getResponseBody();
            String resStr = new String(res);
            if (StringUtil.isBlank(resStr)) {
                return "";
            }
            JSONObject json = new JSONObject(resStr);
            if (!json.has("access_token") || StringUtil.isBlank(json.getString("access_token"))) {
                return "";
            }
            accessToken = json.getString("access_token");
            return accessToken;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }
        return "";
    }

    /**
     * 用于获取微信的accesstoken
     *
     * @return
     */
    public String getAccessTokenFromEMBS() {
        //------------------------------------------获取accesstoken
        GetMethod getMethod = null;
        String accessToken = "";
        try {
            getMethod = new GetMethod(Constants.WEIXIN_API_GETACESSTOKEN_EMBS
            );
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return "";
            }
            byte[] res = getMethod.getResponseBody();
            String resStr = new String(res);
            if (StringUtil.isBlank(resStr)) {
                return "";
            }
            JSONObject json = new JSONObject(resStr);
            if (json.getInt("ret") == 0) {
                logger.error("获取accessToken失败。");
                return "";
            }
            if (!json.has("accessToken") || StringUtil.isBlank(json.getString("accessToken"))) {
                return "";
            }
            accessToken = json.getString("accessToken");
            return accessToken;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }
        return "";
    }


    public static void main(String args[]) {
        final CountDownLatch latch = new CountDownLatch(1000);
        final AtomicInteger a = new AtomicInteger(1);
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new WeiXinApiHelper().sendTextMsgToUser("ojjNQuBs-rFUqN0iYxmWbjedxe94", "test", 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            }).start();
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println(" a = " + a);
    }

    /**
     * 获取当前关注者数量
     *
     * @return
     */
    public Integer getFollowersCount() {
        Map<String, Object> paramsMap = Maps.newHashMap();
        String accessToken = getAccessTokenFromEMBS();//获取accessToken
        if (StringUtils.isBlank(accessToken)) {
            logger.error("获取关注者列表时无法获取accessToken");
            return -1;//获取accessToken出错
        }
        paramsMap.put("access_token", accessToken);
        String params = "";
        if (paramsMap != null && paramsMap.size() > 0) {
            for (Map.Entry param : paramsMap.entrySet()) {
                params += "&" + param.getKey() + "=" + param.getValue();
            }
            params = params.substring(1);
            params = "?" + params;
        }
        GetMethod getMethod = new GetMethod(Constants.WEIXIN_API_USERLIST_PREFIX + params);
        getMethod.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode >= HttpStatus.SC_OK && statusCode <= HttpStatus.SC_PARTIAL_CONTENT) {//成功状态的返回码
            } else if (statusCode >= HttpStatus.SC_MULTIPLE_CHOICES && statusCode <= HttpStatus.SC_TEMPORARY_REDIRECT) {//重定向
                int i = 1;
                while (i <= 5 && (statusCode >= HttpStatus.SC_MULTIPLE_CHOICES && statusCode <= HttpStatus.SC_TEMPORARY_REDIRECT)) {   //最多重定向5次
                    getMethod = new GetMethod(getMethod.getResponseHeader("location").getValue() + params);
                    statusCode = httpClient.executeMethod(getMethod);
                    i++;
                }
            } else {
                throw new RuntimeException("未出理返回码：" + statusCode + "\n" + getMethod.getResponseBodyAsString());
            }
            String json = getMethod.getResponseBodyAsString();
            if (StringUtils.isNotBlank(json)) {
                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject.has("errcode")) {
                    logger.error("调用获取关注者列表接口出错：" + jsonObject.toString());
                    return -1;
                }
                return jsonObject.getInt("total");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            getMethod.releaseConnection();
        }
        return -1;
    }


    /**
     * 获取绑定用的带参数二维码
     *
     * @param senceid     关于secceid的范围划分： （后续的调用可以再此处加上注释，防止重复了）
     *                    微信规定的范围：无符号32位整形(1~Integer.MAX_VALUE)
     *                    1.用于绑定网站当前登录用户的id与微信账号 100000000 ~ 199999999
     *                    2.senceId在1~100000范围内用于获取永久二维码，用于微信活动模块
     * @param accessToken
     * @return
     */
    public String getRQCodeTicketForConnect(String senceid, String accessToken, int retryTimeToken, int retryTimeUrl) {
        String ticket = null;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(Constants.WEIXIN_API_GETQRCODE_TICKET_PREFIX
                    + accessToken);
            StringRequestEntity requestEntity = new StringRequestEntity(
                    "{\"expire_seconds\": " + Constants.WEIXIN_QRCODE_EXPIRE_TIME + ", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + senceid + "}}}",
                    "application/json",
                    "UTF-8");
            postMethod.setRequestEntity(requestEntity);
            int statusCode = 0;
            try {
                statusCode = httpClient.executeMethod(postMethod);
            } catch (Exception e) {
                logger.error("调用获取二维码的url时出现异常", e.getCause());
                Thread.sleep(((int) Math.random() * 500) + 500);   //等待一段时间后再发送
                if (retryTimeUrl < 3) {
                    return getRQCodeTicketForConnect(senceid, accessToken, retryTimeToken, retryTimeUrl + 1);
                } else {
                    logger.error("重复调用获取二维码的url三次后放弃。");
                    return "";
                }
            }
            if (statusCode != HttpStatus.SC_OK) {
                return "";
            }
            byte[] res = postMethod.getResponseBody();
            String resStr = new String(res);
            if (StringUtil.isBlank(resStr)) {
                return "";
            }
            JSONObject json = new JSONObject(resStr);
            if (json.has("errcode") && (json.getInt("errcode") == 42001 || json.getInt("errcode") == 40001) && retryTimeToken < 3) {
                logger.error("senceid【" + senceid + "】，第【" + (retryTimeToken + 1) + "】次调用微信api时accessToken【" + accessToken + "】过期,重新获取并调用");
                Thread.sleep(1000);
                return getRQCodeTicketForConnect(senceid, getAccessTokenFromEMBS(), retryTimeToken + 1, retryTimeUrl);
            }
            if (!json.has("ticket") || StringUtil.isBlank(json.getString("ticket"))) {
                return "";
            }
            ticket = json.getString("ticket");
            return ticket;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }
        return "";
    }

    /**
     * 获取绑定用的带参数二维码
     *
     * @param senceid     关于secceid的范围划分： （后续的调用可以再此处加上注释，防止重复了）
     *                    微信规定的范围：无符号32位整形(1~Integer.MAX_VALUE)
     *                    1.用于绑定网站当前登录用户的id与微信账号 100000000 ~ 199999999
     *                    2.senceId在1~100000范围内用于获取永久二维码，用于微信活动模块
     * @param accessToken
     * @return
     */
    public String getPermanentRQCodeTicketForConnect(String senceid, String accessToken, int retryTimeToken, int retryTimeUrl) {
        String ticket = null;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(Constants.WEIXIN_API_GETQRCODE_TICKET_PREFIX
                    + accessToken);
            StringRequestEntity requestEntity = new StringRequestEntity(
                    "{ \"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + senceid + "}}}",
                    "application/json",
                    "UTF-8");
            postMethod.setRequestEntity(requestEntity);
            int statusCode = 0;
            try {
                statusCode = httpClient.executeMethod(postMethod);
            } catch (Exception e) {
                logger.error("调用获取二维码的url时出现异常", e.getCause());
                Thread.sleep(((int) Math.random() * 500) + 500);   //等待一段时间后再发送
                if (retryTimeUrl < 3) {
                    return getRQCodeTicketForConnect(senceid, accessToken, retryTimeToken, retryTimeUrl + 1);
                } else {
                    logger.error("重复调用获取二维码的url三次后放弃。");
                    return "";
                }
            }
            if (statusCode != HttpStatus.SC_OK) {
                return "";
            }
            byte[] res = postMethod.getResponseBody();
            String resStr = new String(res);
            if (StringUtil.isBlank(resStr)) {
                return "";
            }
            JSONObject json = new JSONObject(resStr);
            if (json.has("errcode") && (json.getInt("errcode") == 42001 || json.getInt("errcode") == 40001) && retryTimeToken < 3) {
                logger.error("senceid【" + senceid + "】，第【" + (retryTimeToken + 1) + "】次调用微信api时accessToken【" + accessToken + "】过期,重新获取并调用");
                Thread.sleep(1000);
                return getRQCodeTicketForConnect(senceid, getAccessTokenFromEMBS(), retryTimeToken + 1, retryTimeUrl);
            }
            if (!json.has("ticket") || StringUtil.isBlank(json.getString("ticket"))) {
                return "";
            }
            ticket = json.getString("ticket");
            return ticket;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }
        return "";
    }

    public String getQRCodeImgUrl(Integer senceid) {
        //获取accesstoken
        String accessToken = getAccessTokenFromEMBS();
        if (StringUtil.isBlank(accessToken)) {
            logger.error("获取accessToken失败");
            return null;
        }
        String ticket = getPermanentRQCodeTicketForConnect(senceid + "", accessToken, 0, 0);
        if (StringUtil.isBlank(ticket)) {
            logger.error("获取二维码ticket失败");
            return null;
        }
        //返回二维码的对应ticket的url
        return Constants.WEIXIN_API_GETQRCODE_PREFIX + ticket;
    }


    /**
     * 使用茜雅获得的token，直接调用微信接口发送消息
     *
     * @param openid
     * @param message
     * @param retryTime
     * @return
     */
    public Integer sendTextMsgToUser(String openid, String message, int retryTime) {
        if (StringUtils.isBlank(openid)) {
            logger.error("服务号调用微信推送文本消息接口：openid为空");
            return Constants.WEIXIN_RETURN_CODE_FAILURE;
        }
        if (StringUtils.isBlank(message)) {
            logger.error("服务号调用微信推送文本消息接口：发送消息为空");
            return Constants.WEIXIN_RETURN_CODE_FAILURE;
        }
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(Constants.WEIXIN_API_SEND_TEXT_MESSAGE_PREFIX + getAccessTokenFromEMBS()
            );
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("touser", openid);
            jsonObject.put("msgtype", "text");
            JSONObject text = new JSONObject();
            text.put("content", message);
            jsonObject.put("text", text);
            StringRequestEntity requestEntity = new StringRequestEntity(
                    jsonObject.toString(),
                    "application/json",
                    "UTF-8");
            postMethod.setRequestEntity(requestEntity);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("服务号调用微信推送文本消息接口：http应答不为200，【" + statusCode + "】");
                return Constants.WEIXIN_RETURN_CODE_FAILURE;
            }
            byte[] res = postMethod.getResponseBody();
            String resStr = new String(res);
            if (StringUtil.isBlank(resStr)) {
                logger.error("服务号调用微信推送文本消息接口：微信服务器返回值为空");
                return -3;
            }
            JSONObject json = new JSONObject(resStr);
            if (json.has("errcode") && json.getInt("errcode") != 0) {
                if ((json.getInt("errcode") == 42001 || json.getInt("errcode") == 40001) && retryTime < 3) {
                    logger.info("openid【" + openid + "】，第【" + (retryTime + 1) + "】次调用微信api时accessToken过期或无效,重新获取并调用");
                    Thread.sleep(1000);
                    return sendTextMsgToUser(openid, message, retryTime + 1);
                }
                if (json.getInt("errcode") == 45015) {
                    logger.info("服务号调用微信推送文本消息接口请求无效，用户未签到。：openid：【" + openid + "】,：【" + json + "】");
                    return Constants.WEIXIN_RETURN_CODE_SUCCESS;
                }
                logger.error("服务号调用微信推送文本消息接口：openid：【" + openid + "】,微信接口返回错误：【" + json + "】");
                return json.getInt("errcode");
            } else {
                return Constants.WEIXIN_RETURN_CODE_SUCCESS;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }
        logger.error("服务号调用微信推送文本消息接口：操作失败。");
        return Constants.WEIXIN_RETURN_CODE_FAILURE;
    }

    /**
     * 向用户发送信息
     * 检查用户是否已经绑定
     * 返回发送结果
     *
     * @return
     */
    public Integer sendTextMessageToUserWithChecked(Integer userId, WeiXinMsgBo vo) {
        if (StringUtils.isNotBlank(vo.getContent())) {
            try {
                List<PerUserConnect> perUserConnects = perUserConnectEao.findByUserIdAndType(userId, Constants.CONNECT_TYPE_WEIXIN);
                if (CollectionUtils.isNotEmpty(perUserConnects)) {//已绑定用户才发送
                    return sendTextMsgToUser(perUserConnects.get(0).getOpenId(), vo.getContent(), 0);
                } else {
                    return Constants.WEIXIN_RETURN_CODE_UNBIND;//该用户未绑定
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return Constants.WEIXIN_RETURN_CODE_FAILURE;
    }


    /**
     * 使用外包方接口发送text
     * 要是返回accessToken过期的话
     * 重试3次，失败的话放弃
     *
     * @param openid
     * @param message
     * @param retryTimeToken
     * @return
     */
    public Integer sendTextMsgToUserFromEMBS(String openid, String message, int retryTimeToken, int retryTimeUrl) {
        if (StringUtils.isBlank(openid)) {
//            return "opeid为空。";
            return -3;
        }
        if (StringUtils.isBlank(message)) {
//            return "message为空。";
            return -3;
        }
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(Constants.WEIXIN_API_SEND_TEXT_MSG_EMBS
            );
            postMethod.addParameter("openid", openid);
            postMethod.addParameter("message", message);
            int statusCode = 0;
            try {
                statusCode = httpClient.executeMethod(postMethod);
            } catch (Exception e) {
                logger.error("调用发送微信消息的url时出现异常", e.getCause());
                Thread.sleep((int) Math.random() * 1000);   //等待一段时间后再发送
                if (retryTimeUrl < 3) {
                    return sendTextMsgToUserFromEMBS(openid, message, retryTimeToken, retryTimeUrl + 1);
                } else {
                    logger.error("重复调用发送微信消息的ur三次后放弃。");
                    return -3;
                }
            }
            if (statusCode != HttpStatus.SC_OK) {
                return -3;
            }
            byte[] res = postMethod.getResponseBody();
            String resStr = new String(res);
            if (StringUtil.isBlank(resStr)) {
                return -3;
            }
            JSONObject json = new JSONObject(resStr);
            if (json.has("ret") && (json.getInt("ret") == 42001 || json.getInt("ret") == 40001) && retryTimeToken < 3) {
                logger.error("openid【" + openid + "】，第【" + (retryTimeToken + 1) + "】次调用微信api时accessToken过期或无效,重新获取并调用");
                Thread.sleep(1000);
                return sendTextMsgToUserFromEMBS(openid, message, retryTimeToken + 1, retryTimeUrl);
            }
            if (json.has("ret") && json.getInt("ret") == 45015) {
                //logger.info("openid【" + openid + "】并未签到。");
                return Constants.WEIXIN_RETURN_CODE_UNSIGNED;
            }
            if (json.has("_code")) {
                return json.getInt("_code");
            }
            if (json.has("ret")) {
                return json.getInt("ret");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }
        return -3;
    }

    /**
     * 获取前台调用微信jsapi接口的配置参数
     *
     * @param url
     * @return
     */
    public Map<String, Object> getApiJsParam(String url) {
        Map<String, Object> ret = getWeiXinJsApiTicket();
        //获取jsApiTicket不成功或需要重试的话返回
        if (!((Integer)ret.get("ret")).equals(1)) {
            return ret;
        }
        String jsApiTicket = ret.get("jsApiTicket").toString();
        //根据jsApiTicket获取数字签名并返回其他配置信息
        ret = sign(jsApiTicket, url);
        ret.put("ret",1);
        return ret;
    }

    /**
     * 获取jsApiTicket：
     * 应该仍存在失效的情况：当ticket还有较短的时间就过期，比如0.5秒
     * 这时候客户端成功获取ticket，当客户端拿着这一ticket时去配置jsapi的时候，后端已重新请求
     * 并获取jsApiTicket，这样客户端的ticket就是失效的（客户端允许失败重试解决？）
     *
     * @return
     */
    public Map<String, Object> getWeiXinJsApiTicket() {
        Map<String, Object> ret = Maps.newHashMap();
        //检查redis中的ticket是否有效，有则返回
        if (redisEao.isKeyExist(Constants.WEIXIN_JS_API_TICKET_REIDS_KEY)) {
            String jsApiTicket = redisEao.readFromString(Constants.WEIXIN_JS_API_TICKET_REIDS_KEY);
            ret.put("ret", 1);
            ret.put("jsApiTicket", jsApiTicket);
            return ret;
        }
        try {
            final RedisTemplate redisTemplate = redisEao.getRedisTemplate(Constants.WEIXIN_LOCK_JS_API_TICKET_REIDS_KEY);
            //如果无效向微信服务器获取（通过乐观锁保证有一个线程在获取，获取乐观锁失败则已存在获取的线程，返回-2提示客户端等待后重试）
            Boolean isSuc = (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] redisKey = redisTemplate.getStringSerializer().serialize(Constants.WEIXIN_LOCK_JS_API_TICKET_REIDS_KEY);
                    try {
                        connection.watch(redisKey);
                        if (!connection.exists(redisKey)) {
                            connection.multi();
                            connection.set(redisKey, redisTemplate.getStringSerializer().serialize(Constants.WEIXIN_LOCK_JS_API_TICKET_REIDS_KEY));
                            connection.expire(redisKey, Constants.WEIXIN_LOCK_JS_API_TICKET_EXPIRE_TIME);
                            List<Object> result = connection.exec();
                            return result != null;
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    } finally {
                        connection.unwatch();
                    }
                    return false;
                }
            });
            if(!isSuc){  //获取乐观锁失败则已存在获取的线程，返回-2提示客户端等待后重试
                ret.put("ret", -2);
                return ret;
            }
            //获取成功则保存到redis并返回，否则返回失败
            String jsApiTicket = getWeiXinJsApiTicketFromWeixin();
            if(StringUtils.isBlank(jsApiTicket)){
                ret.put("ret", -1);//向微信服务器获取jsApiTicket失败
                return ret;
            }
            redisEao.writeToString(Constants.WEIXIN_JS_API_TICKET_REIDS_KEY,jsApiTicket);
            redisEao.setKeyExpire(Constants.WEIXIN_JS_API_TICKET_REIDS_KEY,Constants.WEIXIN_JS_API_TICKET_EXPIRE_TIME);
            ret.put("ret", 1);
            ret.put("jsApiTicket", jsApiTicket);
            return ret;
        } catch (Exception e) {
            //若出现异常清除jsapiticket，使得下一次请求重新获取
            redisEao.del(Constants.WEIXIN_JS_API_TICKET_REIDS_KEY);
            logger.error(e.getMessage(), e);
        } finally {
            //释放乐观锁
            redisEao.del(Constants.WEIXIN_LOCK_JS_API_TICKET_REIDS_KEY);
        }
        ret.put("ret", -1);
        return ret;
    }

    /**
     * 从微信提供的接口中获取jsApiTicket，当redis中缓存的jsApiTicket过期才调用
     *
     * @return
     */
    public String getWeiXinJsApiTicketFromWeixin() {
        GetMethod getMethod = null;
        String jsApiTicket = "";
        //获取accesstoken
        String accessToken = getAccessTokenFromEMBS();
        if (StringUtil.isBlank(accessToken)) {
            logger.error("获取accessToken失败");
            return null;
        }
        try {
            getMethod = new GetMethod(Constants.WEIXIN_API_GET_JS_API_TICKET_URL
                    + "?access_token=" + accessToken + "&type=jsapi");
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                return "";
            }
            byte[] res = getMethod.getResponseBody();
            String resStr = new String(res);
            if (StringUtil.isBlank(resStr)) {
                return "";
            }
            JSONObject json = new JSONObject(resStr);
            if (json.has("errcode")
                    && json.getInt("errcode") > 0) {
                logger.error("获取jsApiTicket失败,返回【" + resStr + "】");
                return "";
            }
            if (!json.has("ticket") || StringUtil.isBlank(json.getString("ticket"))) {
                return "";
            }
            jsApiTicket = json.getString("ticket");
            return jsApiTicket;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }
        return "";
    }

    /**
     * 获取前台微信js接口config所需的参数，包括数字签名（这是微信官方加密的例子）
     *
     * @param jsApiTicjet
     * @param url
     * @return
     */
    public static Map<String, Object> sign(String jsApiTicjet, String url) {
        Map<String, Object> ret = Maps.newHashMap();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsApiTicjet +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("appId", Constants.WEIXIN_APP_ID);
//        ret.put("jsapi_ticket", jsApiTicjet);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    /**
     * 字节码值转换为16进制字符串
     *
     * @param hash
     * @return
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 生成随机uuid作为数字签名的随机数
     *
     * @return
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取当前的时间戳用语生成数字签名
     *
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


}
