package com.li.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HTTPServletRequestUtil
 * @Description:获取前端请求HttpServletRequest中参数的工具类
 * @author: libl
 * @date: 2019/05/29 16:50
 */
public class HTTPServletRequestUtil {
    public static int getInt(HttpServletRequest request, String name) {
        try {
            return Integer.decode(request.getParameter(name));
        } catch (Exception e) {
            return -1;
        }
    }

    public static Long getLong(HttpServletRequest request, String name) {
        try {
            return Long.valueOf(request.getParameter(name));
        } catch (Exception e) {
            return -1l;
        }
    }

    public static Double getDouble(HttpServletRequest request, String name) {
        try {
            return Double.valueOf(request.getParameter(name));
        } catch (Exception e) {
            return -1d;
        }
    }

    public static Boolean getBoolean(HttpServletRequest request, String name) {
        try {
            return Boolean.valueOf(request.getParameter(name));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String name) {
        try {
            String result = request.getParameter(name);
            if (result != null)
                result = result.trim();
            if ("".equals(result))
                result = null;
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
