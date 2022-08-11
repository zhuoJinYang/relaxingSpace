package com.rspace.domain.util;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求工具类
 */
public class HttpRequestUtil {
    private HttpRequestUtil() {}

    public static ServletRequestAttributes getRequestAttributes(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static String getHeader(HttpServletRequest request,String name){
        return getRequest().getHeader(name);
    }

    public static String getHeader(String name){
        return getHeader(getRequest(),name);
    }

    public static void setHeader(HttpServletResponse response,String name,String value){
        response.setHeader(name, value);
    }

    public static void setHeader(String name,String value){
        setHeader(getResponse(),name,value);
    }

    public static Object getAttribute(HttpServletRequest request,String name){
        return request.getAttribute(name);
    }

    public static Object getAttribute(String name){
        return getAttribute(getRequest(), name);
    }

    public static void setAttribute(HttpServletRequest request,String name,Object value){
        request.setAttribute(name,value);
    }

    public static void setAttribute(String name,Object value){
        setAttribute(getRequest(), name, value);
    }

    public static String getFormHeaderOrAttribute(HttpServletRequest request,String name){
        String value = getHeader(request,name);
        if(!StringUtils.hasLength(value)){
            value = (String) getAttribute(request, name);
        }
        return value;
    }

    public static String getFromHeaderOrAttribute(String name) {
        String value = getHeader(name);
        if (!StringUtils.hasLength(value)) {
            value = (String) getAttribute(name);
        }
        return value;
    }

    /**
     * 获取请求头中全部参数
     */
    public static Map<String,String> getParamMapFromHeader(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            params.put(name,getHeader(name));
        }
        return params;
    }
    public static Map<String,String> getParamMapFromHeader(){
        return getParamMapFromHeader(getRequest());
    }

    /**
     * 获取请求头中的UserAgent信息
     */
    public static String getUserAgent(HttpServletRequest request){
        return getHeader(request, HttpHeaders.USER_AGENT);
    }
    public static String getUserAgent(){
        return getUserAgent(getRequest());
    }

    /**
     * 根据HttpServletRequest获取访问的内位ip地址
     */
    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
    /**
     * 根据HttpServletRequest获取访问的内网ip地址
     */
    public static String getIpAddress() {
        return getIpAddress(getRequest());
    }

    /**
     * 根据请求头中的UserAgent参数判断该访问的客户端类型
     *
     * @return pc / mobile
     */
    public static String getAccessClientType(HttpServletRequest request) {
        String agent = getUserAgent(request).toLowerCase();
        if (agent.contains("iphone") || agent.contains("ipad") || agent.contains("ipod")) {
            return "mobile";
        } else if (agent.contains("android")) {
            return "mobile";
        } else {
            return "pc";
        }
    }

    /**
     * 根据请求头中的UserAgent参数判断该访问的客户端类型
     *
     * @return pc / mobile
     */
    public static String getAccessClientType() {
        return getAccessClientType(getRequest());
    }

    public static String encodeUrl(String url) {
        if (!StringUtils.hasText(url)) {
            return null;
        }
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decodeUrl(String url) {
        if (!StringUtils.hasText(url)) {
            return null;
        }
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
