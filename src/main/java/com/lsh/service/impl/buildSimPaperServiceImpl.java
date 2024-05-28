package com.lsh.service.impl;

import com.lsh.service.PaperAbstract;
import com.lsh.service.PaperService;
import com.lsh.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/05/28
 */
@Service("buildSimPaper")
public class buildSimPaperServiceImpl extends PaperAbstract {


        public void test(){
            System.out.println("buildSimPaperServiceImpl test");
        }

    @Override
    public Result buildPaper() {
        return null;
    }
}
