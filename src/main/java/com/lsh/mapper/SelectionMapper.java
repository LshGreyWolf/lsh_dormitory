package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.Selection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (Selection)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 17:22:50
 */
@Mapper
public interface SelectionMapper extends BaseMapper<Selection> {

    List<Selection> querySelection(Selection selection);

    void saveSelection(Selection selection);

    void updateSelection(Selection selection);
}
