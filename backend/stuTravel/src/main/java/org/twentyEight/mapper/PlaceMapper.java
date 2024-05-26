package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.twentyEight.pojo.Place;
import org.twentyEight.pojo.Venue;

@Mapper
public interface PlaceMapper {
    @Insert("insert into place(name, popularity, data_path, rating) values(#{name},#{popularity},#{data_path},#{rating})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPlace(Place place);

    @Select("select * from place where id = #{id}")
    Place findPlaceById(Integer id);
}
