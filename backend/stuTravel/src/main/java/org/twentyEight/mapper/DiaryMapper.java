package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.twentyEight.pojo.Diary;

import java.util.List;

@Mapper
public interface DiaryMapper {
    // 新增
    @Insert("insert into diary(title, content, cover_img, state, create_user, create_time, update_time, plan_id, place_id)" +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{createUser}, #{createTime}, #{updateTime}, #{planId}, #{placeId})")
    void add(Diary diary);

    List<Diary> list(Integer userId, Integer planId, String state);

    List<Diary> listCommunity(Long placeId);

    Diary getByDiaryId(Integer id);

    @Update("update diary set popularity=popularity+1 where id=#{diaryId}")
    void incrementPopularityByDiaryId(Integer diaryId);

    @Update("update diary set rating=#{rating}, rating_count=#{ratingCount} where id=#{diaryId}")
    void updateRating(Integer diaryId, Double rating, Integer ratingCount);
}
