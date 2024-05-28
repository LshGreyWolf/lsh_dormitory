package com.lsh.service;

import com.lsh.utils.Result;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/05/28
 */
public abstract class PaperAbstract implements PaperService{

    public Result buildPaperTemp(){
        System.out.println("PaperAbstract");
        test();
        return Result.ok("抽象类");
    }
    public void test(){

    }
}
