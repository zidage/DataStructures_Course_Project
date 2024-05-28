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
        }
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

        planMapper.insertPlanVenues(plan.getId(), venueIds);

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

}
