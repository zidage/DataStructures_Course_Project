package org.twentyEight.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twentyEight.mapper.DiaryMapper;
import org.twentyEight.pojo.Diary;
import org.twentyEight.pojo.PageBean;
import org.twentyEight.service.DiaryService;
import org.twentyEight.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public void add(Diary diary) {
        // 补充属性值
        diary.setCreateTime(LocalDateTime.now());
        diary.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        diary.setCreateUser(userId);
        diaryMapper.add(diary);
    }

    @Override
    public PageBean<Diary> list(Integer pageNum, Integer pageSize, Integer placeId, String state) {
        // 创建PageBean对象
        PageBean<Diary> pb = new PageBean<>();
        // 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 调用Mapper完成查询
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Diary> as = diaryMapper.list(userId, placeId, state);
        // Page中提供了方法，可以获取PageHelper分页查询后，得到的总记录条数和当前页数据
        Page<Diary> p = (Page<Diary>) as;

        // 把数据填充到PageBean对象之中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Diary> listCommunity(Integer pageNum, Integer pageSize, Long placeId) {
        PageBean<Diary> pb = new PageBean<>();
        // 开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 调用Mapper完成查询
        List<Diary> as = diaryMapper.listCommunity(placeId);
        // Page中提供了方法，可以获取PageHelper分页查询后，得到的总记录条数和当前页数据
        Page<Diary> p = (Page<Diary>) as;

        // 把数据填充到PageBean对象之中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Diary getByDiaryId(Integer diaryId) {
        return diaryMapper.getByDiaryId(diaryId);
    }

    @Override
    public void incrementPopularityByDiaryId(Integer diaryId) {
        diaryMapper.incrementPopularityByDiaryId(diaryId);
    }

    @Override
    public void updateRating(Diary diary) {
        Integer diaryId = diary.getId();
        Double rating = diary.getRating();
        Integer ratingCount = diary.getRatingCount();
        diaryMapper.updateRating(diaryId, rating, ratingCount);
    }

    @Override
    public void updateDiary(Diary editDiary) {
        diaryMapper.updateDiary(editDiary);
    }
}
