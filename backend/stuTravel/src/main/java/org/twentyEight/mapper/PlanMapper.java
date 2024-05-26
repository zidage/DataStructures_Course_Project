package org.twentyEight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.twentyEight.pojo.Plan;

import java.util.List;

@Mapper
public interface PlanMapper {
    @Insert("insert into " +
            "plan (create_user, title, place, transport, required_time) " +
            "values (#{createUser}, #{title}, #{place}, #{transport}, #{requiredTime})")
    void insertPlan(Plan plan);

    @Insert({"<script>",
            "INSERT INTO plan_venue (plan_id, venue_id) VALUES ",
            "<foreach collection='venueIds' item='venueId' separator=','>",
            "(#{planId}, #{venueId})",
            "</foreach>",
            "</script>"
    })
    void insertPlanVenues(Integer planId, List<Integer> venueIds);
}
