package org.twentyEight.service;

import org.twentyEight.pojo.PageBean;
import org.twentyEight.pojo.Place;
import org.twentyEight.pojo.Plan;
import org.twentyEight.pojo.Venue;

import java.util.List;

public interface PlanService {
    public void createPlanWithVenues(Plan plan, Long placeId, List<Long> venueIds);
    public Place getPlaceById(Long placeId);


    void deletePlan(Integer planId);

    void updatePlanWithVenues(Plan plan, Long placeId, List<Long> venueIds);

    PageBean<Place> listPlace(Integer pageNum, Integer pageSize, String name, String address);

    PageBean<Venue> listVenuesByPlaceId(Long placeId, Integer pageNum, Integer pageSize, String venueName, String type);
}
