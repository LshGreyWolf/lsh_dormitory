package com.lsh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Org;
import com.lsh.mapper.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * (Org)表服务接口
 *
 * @author makejava
 * @since 2023-03-18 14:55:18
 */

public interface OrgService {


    public int create(Org org);

    public int delete(String ids);

    public int delete(Integer id);

    public int update(Org org);

    public int updateSelective(Org org);

    public PageInfo<Org> query(Org org);

    public Org detail(Integer id);

    public int count(Org org);

    public List<Org> queryOrgBySelectionId(Integer selectionId);
}
