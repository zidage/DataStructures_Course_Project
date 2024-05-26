package org.twentyEight.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twentyEight.mapper.PlaceMapper;
import org.twentyEight.mapper.PlanMapper;
import org.twentyEight.mapper.VenueMapper;
import org.twentyEight.pojo.Place;
import org.twentyEight.pojo.Plan;
import org.twentyEight.pojo.Venue;
import org.twentyEight.service.PlanService;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private VenueMapper venueMapper;
    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public void createPlanWithVenues(Plan plan, List<Integer> venueIds) {
        planMapper.insertPlan(plan);
        if (venueIds != null && !venueIds.isEmpty()) {
            planMapper.insertPlanVenues(plan.getId(), venueIds);
        }
    }

    @Override
    public List<Venue> getVenuesByPlaceId(Integer placeId) {
        return venueMapper.findVenuesByPlaceId(placeId);
    }

    @Override
    public Place getPlaceById(Integer placeId) {
        return placeMapper.findPlaceById(placeId);
    }

    @Override
    public List<Venue> getVenuesByPlaceIdAndQuery(Integer placeId, String query, String type, int limit, int offset) {
        return venueMapper.findVenuesByPlaceIdAndQuery(placeId, query, type, limit, offset);
    }
}
