package com.job5156.core.common.helper;

import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class CookieHelper {

	@Value("${cookie.domain}")
	private String cookieDomain;

	@Value("${cookie.group.split}")
	private String cookieGroupSplit;

	@Value("${cookie.charset}")
	private String cookieCharset;

	private String cookiePath = "/";
	private int cookieExpiry = -1;

	public String getCookieDomain() {
		return cookieDomain;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	public String getCookiePath() {
		return cookiePath;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public int getCookieExpiry() {
		return cookieExpiry;
	}

	public void setCookieExpiry(int cookieExpiry) {
		this.cookieExpiry = cookieExpiry;
	}

	public Cookie createCookie(String name, Object value) {
		Cookie cookie = new Cookie(name, ObjectUtils.toString(value));
		if (StringUtils.isNotBlank(getCookieDomain())) {
			cookie.setDomain(getCookieDomain());
		}
		cookie.setPath(getCookiePath());
		cookie.setMaxAge(getCookieExpiry());
		return cookie;
	}

    public Cookie createCookieByTopDomain(String name, Object value) {
        Cookie cookie = new Cookie(name, ObjectUtils.toString(value));
        if (StringUtils.isNotBlank(getCookieDomain())) {
            cookie.setDomain("www.job5156.com");
        }
        cookie.setPath(getCookiePath());
        cookie.setMaxAge(getCookieExpiry());
        return cookie;
    }

	public Cookie createCookie(String name, Object value, int expiry) {
		Cookie cookie = new Cookie(name, ObjectUtils.toString(value));
		if (StringUtils.isNotBlank(getCookieDomain())) {
			cookie.setDomain(getCookieDomain());
		}
		cookie.setPath(getCookiePath());
		cookie.setMaxAge(expiry);
		return cookie;
	}

	public Cookie createGroupCookie(String groupName, Map<String, String> valueMap) throws UnsupportedEncodingException {
		return this.createGroupCookie(groupName, valueMap, this.getCookieExpiry());
	}

	public Cookie createGroupCookie(String groupName, Map<String, String> valueMap, int expiry)
			throws UnsupportedEncodingException {
		StringBuffer cookieBuf = new StringBuffer();
		if (MapUtils.isNotEmpty(valueMap)) {
			for (Map.Entry<String, String> entry : valueMap.entrySet()) {
				if (StringUtils.isNotBlank(entry.getKey())) {
					cookieBuf.append(cookieGroupSplit);
					cookieBuf.append(entry.getKey());
					cookieBuf.append("=");
					cookieBuf.append(URLEncoder.encode(entry.getValue(), cookieCharset));
				}
			}
			if (StringUtils.isNotBlank(cookieBuf.toString())) {
				cookieBuf.delete(0, cookieGroupSplit.length());
			}
		}
		return this.createCookie(groupName, cookieBuf.toString(), expiry);
	}

	public Map<String, String> readMapFromGroupCookie(String groupName, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Cookie cookie = WebUtils.getCookie(request, groupName);
		return this.readMapFromGroupCookie(cookie);
	}

	public Map<String, String> readMapFromGroupCookie(Cookie cookie) throws UnsupportedEncodingException {
		Map<String, String> cookieMap = Maps.newHashMap();
		if (cookie != null) {
			String cookieValue = cookie.getValue();
			String[] cookieArr = StringUtils.split(cookieValue, cookieGroupSplit);
			if (ArrayUtils.isNotEmpty(cookieArr)) {
				for (String cookiePairVal : cookieArr) {
					String[] cookiePairArr = StringUtils.split(cookiePairVal, "=");
					if (cookiePairArr != null && cookiePairArr.length == 2) {
						cookieMap.put(cookiePairArr[0], URLDecoder.decode(cookiePairArr[1], cookieCharset));
					}
				}
			}
		}
		return cookieMap;
	}
}
