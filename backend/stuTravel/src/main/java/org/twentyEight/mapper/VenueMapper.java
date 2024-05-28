package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.twentyEight.pojo.Venue;

import java.util.List;

@Mapper
public interface VenueMapper {
    @Insert("INSERT INTO venue(id, name, type, latitude, longitude, place_id, osmid) VALUES(#{osmid}, #{name}, #{type}, #{latitude}, #{longitude}, #{placeId}, #{osmid})")
    void insertVenue(Venue venue);

    @Select("SELECT * FROM venue WHERE place_id = #{placeId}")
    List<Venue> findVenuesByPlaceId(Long placeId);

    List<Venue> listByPlaceId(Long placeId, String venueName, String type);
}
