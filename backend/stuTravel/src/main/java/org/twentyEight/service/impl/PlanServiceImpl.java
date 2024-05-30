package org.twentyEight.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twentyEight.mapper.PlaceMapper;
import org.twentyEight.mapper.PlanMapper;
import org.twentyEight.mapper.VenueMapper;
import org.twentyEight.pojo.PageBean;
import org.twentyEight.pojo.Place;
import org.twentyEight.pojo.Plan;
import org.twentyEight.pojo.Venue;
import org.twentyEight.service.PlanService;
import org.twentyEight.utils.MapViewGenerationUtil;
import org.twentyEight.utils.NearestVenueUtil;
import org.twentyEight.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private VenueMapper venueMapper;
    @Autowired
    private PlaceMapper placeMapper;



    @Override
    public void createPlanWithVenues(Plan plan, Long placeId, List<Long> venueIds) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        plan.setCreateUser(userId);
        plan.setCreateTime(LocalDateTime.now());
        plan.setUpdateTime(LocalDateTime.now());
        plan.setPlaceId(placeId);
        planMapper.insertPlan(plan);
        if (venueIds != null && !venueIds.isEmpty()) {
            planMapper.insertPlanVenues(plan.getId(), venueIds);
            for (Long venueId : venueIds) {
                venueMapper.incrementPopularityByVenueId(venueId);
            }
        }
        updatePlanMapView(plan, placeId, venueIds);
    }

    @Override
    public Place getPlaceById(Long placeId) {
        return placeMapper.findPlaceById(placeId);
    }


    @Override
    public void deletePlan(Integer planId) {
        // 删除关联的Plan-Venue记录
        planMapper.deletePlanVenuesByPlanId(planId);
        // 然后删除计划本身
        planMapper.deletePlan(planId);
    }

    @Override
    public void updatePlanWithVenues(Plan plan, Long placeId, List<Long> venueIds) {
        plan.setPlaceId(placeId);
        planMapper.updatePlan(plan);
        planMapper.deletePlanVenuesByPlanId(plan.getId());

        if (venueIds != null && !venueIds.isEmpty()) {
            planMapper.insertPlanVenues(plan.getId(), venueIds);
        }
        updatePlanMapView(plan, placeId, venueIds);
    }

    @Override
    public PageBean<Place> listPlace(Integer pageNum, Integer pageSize, String name, String address) {
        PageBean<Place> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Place> as = placeMapper.list(name, address);
        Page<Place> p = (Page<Place>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }



    @Override
    public PageBean<Venue> listVenuesByPlaceId(Long placeId, Integer pageNum, Integer pageSize, String venueName, String type) {
        PageBean<Venue> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Venue> as = venueMapper.listByPlaceId(placeId, venueName, type);
        Page<Venue> p = (Page<Venue>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }


    @Override
    public PageBean<Plan> listMyPlan(Integer pageNum, Integer pageSize, Long placeId, Integer planId, String planTitle) {
        PageBean<Plan> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Plan> plans = planMapper.listPlan(userId, planId, placeId, planTitle);
        Page<Plan> p = (Page<Plan>) plans;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public List<Venue> listNearestVenuesByPlaceVenueId(Long placeId, Long venueId, String venueName, String type, Integer radius) {
        PageBean<Venue> pb = new PageBean<>();
        List<Venue> venues = venueMapper.listByPlaceId(placeId, venueName, type);
        Venue targetVenue = venueMapper.findVenueByVenueId(venueId);

        return NearestVenueUtil.findNearestVenues(venues, targetVenue.getLatitude(), targetVenue.getLongitude(), radius);
    }

    private void updatePlanMapView(Plan plan, Long placeId, List<Long> venueIds) {
        try {
            MapViewGenerationUtil mapGenerator = new MapViewGenerationUtil();
            Place place = placeMapper.findPlaceById(placeId);

            String place_formatted = place.getFormattedName();
            StringBuilder queryString = new StringBuilder("-r " + plan.getId() +
                    (plan.getStrategy().equals("DIST") ? " 0" : " 1") +
                    (plan.getStrategy().equals("TIME") ? " 0 " : " 1 ") +
                    place_formatted + " ");
            for (Long venueId : venueIds) {
                queryString.append(venueId.toString()).append(" ");
            }
            mapGenerator.creatQuery(queryString.toString());
            int requiredTime = 0; // (int) Double.parseDouble(result);
            try {
                requiredTime = (int) Double.parseDouble(mapGenerator.endProcess());
                String savePath = System.getenv("MAP_DATA") + "\\map_view_html_test\\"
                        + plan.getId() + ".html";
                planMapper.insertPlanMapViewAndTime(plan.getId(), savePath, requiredTime);
            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
