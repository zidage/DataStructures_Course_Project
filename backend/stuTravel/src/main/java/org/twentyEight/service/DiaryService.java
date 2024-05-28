package org.twentyEight.service;

import org.twentyEight.pojo.Diary;
import org.twentyEight.pojo.PageBean;

public interface DiaryService {
    // 新增文章
    void add(Diary diary);

    // 条件分页列表查询
    PageBean<Diary> list(Integer pageNum, Integer pageSize, Integer planId, String state);
}
