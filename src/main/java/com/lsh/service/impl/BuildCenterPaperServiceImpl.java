package com.lsh.service.impl;

import com.lsh.service.PaperAbstract;
import com.lsh.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/05/28
 */
@Service("centerPaper")
public class BuildCenterPaperServiceImpl extends PaperAbstract {
    public void test(){
        System.out.println("BuildCenterPaperServiceImpl test");
    }

    @Override
    public Result buildPaper() {
        return null;
    }
}
