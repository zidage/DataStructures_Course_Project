package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.twentyEight.pojo.Diary;

import java.util.List;

@Mapper
public interface DiaryMapper {
    // 新增
    @Insert("insert into diary(title, content, cover_img, state, create_user, create_time, update_time, plan_id)" +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{createUser}, #{createTime}, #{updateTime}, #{planId})")
    void add(Diary diary);

    List<Diary> list(Integer userId, Integer planId, String state);
}
