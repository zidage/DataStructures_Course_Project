package org.twentyEight.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.twentyEight.pojo.Place;
import org.twentyEight.pojo.Plan;
import org.twentyEight.pojo.Venue;
import org.twentyEight.service.PlanService;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    @PostMapping
    public void createPlan(@RequestBody PlanRequest planRequest) {
        planService.createPlanWithVenues(planRequest.getPlan(), planRequest.getVenueIds());
    }

    @GetMapping("/place/{placeId}/venues")
    public List<Venue> getVenuesByPlaceIdAndQuery(@PathVariable Integer placeId,
                                                  @RequestParam(required = false) String query,
                                                  @RequestParam(required = false) String type,
                                                  @RequestParam(defaultValue = "10") int limit,
                                                  @RequestParam(defaultValue = "0") int offset) {
        return planService.getVenuesByPlaceIdAndQuery(placeId, query, type, limit, offset);
    }

    @GetMapping("/place/{placeId}")
    public Place getPlaceById(@PathVariable Integer placeId) {
        return planService.getPlaceById(placeId);
    }
}

@Getter
@Setter
class PlanRequest {
    private Plan plan;
    private List<Integer> venueIds;
}
