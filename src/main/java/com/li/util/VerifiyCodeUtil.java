package com.li.util;

import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: VerifiyCodeUtil
 * @Description:验证码工具类
 * @author: libl
 * @date: 2019/05/31 9:28
 */
public class VerifiyCodeUtil {
    private static final Logger logger = LoggerFactory.getLogger(VerifiyCodeUtil.class);

    /**
     * @Description:验证码校验
     * @Param:前端HttpServletRequest
     * @return: boolean
     * @Author: li
     */
    public static boolean verifyCode(HttpServletRequest request) {
        // 图片中的验证码
        String verifyCodeEXpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("verifyCodeExpected:{}", verifyCodeEXpected);
        // 用户输入的验证码
        String verifyCodeActual = HTTPServletRequestUtil.getString(request, "verifyCodeActual");
        logger.debug("verifyCodeActual:{}", verifyCodeActual);
        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeEXpected))
            return false;
        return true;
    }
}
