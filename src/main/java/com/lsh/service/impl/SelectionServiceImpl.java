package com.lsh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Org;
import com.lsh.domain.Selection;
import com.lsh.domain.SelectionJoiner;
import com.lsh.mapper.OrgMapper;
import com.lsh.mapper.SelectionJoinerMapper;
import com.lsh.mapper.SelectionMapper;
import com.lsh.service.OrgService;
import com.lsh.service.SelectionJoinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsh.service.SelectionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (Selection)表服务实现类
 *
 * @author lsh
 * @since 2023-03-28 17:22:50
 */
@Service("selectionService")
public class SelectionServiceImpl extends ServiceImpl<SelectionMapper, Selection> implements SelectionService {
    @Autowired
    private SelectionMapper selectionMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private SelectionJoinerMapper selectionJoinerMapper;

    @Override
    public PageInfo<Selection> querySelection(Selection selection) {
        if (selection != null && selection.getPage() != null) {
            PageHelper.startPage(selection.getPage(), selection.getLimit());
        }
        return new PageInfo<Selection>(selectionMapper.querySelection(selection));

    }

    @Override
    @Transactional
    public void saveSelection(Selection selection) {
        selectionMapper.saveSelection(selection);
        List<Integer> clazzIds = selection.getClazzIds();
        //选择的班级的id集合
        ArrayList<Integer> selectedIds = new ArrayList<>();
        //得到选择的ids，因为里面不止有班级的id 而且还有专业等的id，
        //因为要插入的是select——joiner 只需要班级的id即可
        clazzIds.forEach(item -> {
            Org org = orgMapper.detail(item);
            if (org.getType() == 4) {
                selectedIds.add(org.getId());
            }
        });

        selectedIds.forEach(i->{
            SelectionJoiner selectionJoiner = new SelectionJoiner();
            selectionJoiner.setClazzId(i);
            selectionJoiner.setSelectionId(selection.getId());
            selectionJoinerMapper.insert(selectionJoiner);
        });


    }

    @Override
    @Transactional
    public void updateSelection(Selection selection) {

        selectionMapper.updateSelection(selection);
        //删除已设置的信息
        selectionJoinerMapper.deleteBySelectionId(selection.getId());

        List<Integer> clazzIds = selection.getClazzIds();
        //筛选出对应的班级
        List<Integer> selectIds = new ArrayList();
        clazzIds.forEach(item->{
            Org detail = orgMapper.detail(item);
            if(detail.getType() == 4){
                selectIds.add(detail.getId());
            }
        });
        selectIds.forEach(item->{
            SelectionJoiner joiner = new SelectionJoiner();
            joiner.setClazzId(item);
            joiner.setSelectionId(selection.getId());
            selectionJoinerMapper.insert(joiner);
        });

    }
}
