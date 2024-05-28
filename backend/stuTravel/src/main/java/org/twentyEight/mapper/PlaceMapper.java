package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.twentyEight.pojo.Place;

import java.util.List;

@Mapper
public interface PlaceMapper {


    @Insert("insert into place(id, name, popularity, data_path, rating, address) values(#{id},#{name},#{popularity},#{data_path},#{rating},#{address})")
    void insertPlace(Place place);

    @Select("select * from place where id = #{id}")
    Place findPlaceById(Long id);

    // @Select("select * from place")
    List<Place> list(String placeName, String address);
}
