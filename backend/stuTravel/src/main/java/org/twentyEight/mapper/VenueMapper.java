package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.twentyEight.pojo.Venue;

@Mapper
public interface VenueMapper {
    @Insert("INSERT INTO venue(id, name, type, latitude, longitude, place_id, osmid) VALUES(#{id}, #{name}, #{type}, #{latitude}, #{longitude}, #{placeId}, #{osmid})")
    void insertVenue(Venue venue);
}
