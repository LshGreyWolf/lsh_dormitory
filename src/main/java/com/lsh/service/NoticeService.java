package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Notice;


/**
 * (Notice)表服务接口
 *
 * @author lsh
 * @since 2023-03-28 17:21:34
 */
public interface NoticeService extends IService<Notice> {

    PageInfo<Notice> queryByPage(Notice notice);

    int create(Notice notice);

    int delete(String ids);

    int updateSelective(Notice notice);
}
