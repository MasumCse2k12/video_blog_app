package com.rokomari.videoapp.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;

public class Utils {

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    public static String getClientIpAddress(HttpServletRequest request) {
        if (request == null) return "No IP Found";
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public static boolean isOk(Integer value) {
        return !(value == null || value.intValue() <= 0);
    }
    public static boolean isOk(Date value) {
        return !(value == null);
    }

    public static boolean isOk(Object value) {
        return !(value == null);
    }

    public static boolean isOk(Short value) {
        return !(value == null || value <= 0);
    }

    public static boolean isOk(Long value) {
        return !(value == null || value.longValue() <= 0);
    }

    public static boolean isOk(BigInteger value) {
        return !(value == null || value.intValue() <= 0);
    }

    public static boolean isOk(String str) {
        return !(str == null || str.isEmpty());
    }

}
