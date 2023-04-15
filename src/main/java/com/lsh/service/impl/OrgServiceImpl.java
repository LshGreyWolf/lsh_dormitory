package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Grade;
import com.lsh.domain.Org;
import com.lsh.mapper.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.OrgService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (Org)表服务实现类
 *
 * @author makejava
 * @since 2023-03-18 14:55:18
 */
@Service("orgService")
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {
    @Autowired
    private OrgMapper orgMapper;

    public int create(Org org) {
        return orgMapper.create(org);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                orgMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return orgMapper.delete(id);
    }

    public int updateOrg(Org org) {
        return orgMapper.updateOrg(org);
    }

    public int updateSelective(Org org) {
        return orgMapper.updateSelective(org);
    }

    public PageInfo<Org> query(Org org) {
        if(org != null && org.getPage() != null){
            PageHelper.startPage(org.getPage(),org.getLimit());
        }
        return new PageInfo<Org>(orgMapper.query(org));
    }

    public Org detail(Integer id) {
        return orgMapper.detail(id);
    }



    public List<Org> queryOrgBySelectionId(Integer selectionId){
        return orgMapper.queryOrgBySelectionId(selectionId);
    }

    @Override
    public PageInfo<Org> queryByPage(Org org) {

        if (org != null && org.getPage() != null) {
            PageHelper.startPage(org.getPage(), org.getLimit());
        }
        return new PageInfo<Org>(orgMapper.queryByPage(org));

    }

//    @Override
//    public int updateDeleted(String ids) {
//        String[] arr = ids.split(",");
//        int row = 0;
//        for (String s : arr) {
//            if(!StringUtils.isEmpty(s)){
//                orgMapper.updateDeleted(Integer.parseInt(s));
//                row++;
//            }
//        }
//        return row;
//
//    }
}

