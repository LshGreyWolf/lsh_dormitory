package com.lsh.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
public class Constant {

    //用户类型
    public static Map<Integer,String> typeString = new HashMap<>();


    static {
        typeString.put(0,"管理员");
        typeString.put(1,"宿管员");
    }

}


