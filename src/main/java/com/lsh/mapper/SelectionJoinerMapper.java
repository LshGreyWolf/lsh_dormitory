package com.lsh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.domain.SelectionJoiner;
import org.apache.ibatis.annotations.Mapper;


/**
 * (SelectionJoiner)表数据库访问层
 *
 * @author lsh
 * @since 2023-03-28 17:25:14
 */
@Mapper
public interface SelectionJoinerMapper extends BaseMapper<SelectionJoiner> {

    void deleteBySelectionId(Integer id);
}
