package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.twentyEight.pojo.Venue;

import java.util.List;

@Mapper
public interface VenueMapper {
    @Insert("INSERT INTO venue(id, name, type, latitude, longitude, place_id, osmid) VALUES(#{id}, #{name}, #{type}, #{latitude}, #{longitude}, #{placeId}, #{osmid})")
    void insertVenue(Venue venue);

    @Select("SELECT * FROM venue WHERE place_id = #{placeId}")
    List<Venue> findVenuesByPlaceId(Integer placeId);

    @Select({
            "<script>",
            "SELECT * FROM venue WHERE place_id = #{placeId}",
            "<if test='query != null'>",
            "AND name LIKE CONCAT('%', #{query}, '%')",
            "</if>",
            "<if test='type != null'>",
            "AND type = #{type}",
            "</if>",
            "LIMIT #{limit} OFFSET #{offset}",
            "</script>"
    })
    List<Venue> findVenuesByPlaceIdAndQuery(
            @Param("placeId") Integer placeId,
            @Param("query") String query,
            @Param("type") String type,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}
