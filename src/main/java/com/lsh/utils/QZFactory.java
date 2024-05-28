package com.lsh.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/05/28
 */
public class QZFactory {
    public QZFactory () {

    }

    @SuppressWarnings("unchecked")
    public static <T> T chooseCreate (String name) {
        String beanName = BuildPaperEnum.getBeanNameBySimpleBizKey(name);
        if(StringUtils.isNotBlank(beanName)) {
            return (T) SpringContextUtils.getBean(beanName);
        }else {
            return null;
        }
    }
}
