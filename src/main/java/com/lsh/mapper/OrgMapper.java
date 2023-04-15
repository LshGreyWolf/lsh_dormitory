package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Grade;
import com.lsh.domain.Org;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Org)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-18 14:55:18
 */
@Mapper
public interface OrgMapper extends BaseMapper<Org> {
    public int create(Org org);

    public int delete(Integer id);

    public int updateOrg(Org org);

    public int updateSelective(Org org);

    public List<Org> query(Org org);

    public Org detail(Integer id);

    public int count(Org org);

    public List<Org> queryOrgBySelectionId(Integer selectionId);

    List<Org> queryByPage(Org org);

    void updateDeleted(int parseInt);
}

