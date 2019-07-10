package com.li.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @ClassName: EncryptPropertyPlaceholderConfigurer
 * @Description: 继承PropertyPlaceholderConfigurer，重写convertProperty
 * @author: libl
 * @date: 2019/07/08 11:45
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    // 需要加密的字段数组
    private String[] encryptPropNames={"jdbc.username","jdbc.password"};

    /**
     * @Description:  对关键的属性进行转换
     * @Param: propertyName
     * @Param: propertyValue
     * @return: String
     */
    @Override
    protected String convertProperty(String propertyName,String propertyValue){
        if (isEncryptProp(propertyName)){
            // 解密
            String decryptValue=DESUtils.getDecryptString(propertyValue);
            return decryptValue;
        }else {
            return propertyValue;
        }
    }

    /**
     * @Description: 判断该属性是否加密
     * @Param: propertyName
     * @return: boolean
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName:encryptPropNames){
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }


}
