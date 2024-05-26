package org.twentyEight.service;

import org.twentyEight.pojo.Place;
import org.twentyEight.pojo.Plan;
import org.twentyEight.pojo.Venue;

import java.util.List;

public interface PlanService {
    public void createPlanWithVenues(Plan plan, List<Integer> venueIds);
    public List<Venue> getVenuesByPlaceId(Integer placeId);
    public Place getPlaceById(Integer placeId);
    public List<Venue> getVenuesByPlaceIdAndQuery(Integer placeId, String query, String type, int limit, int offset);
}
