package com.neuedu.demo.tijian.service.impl;

import com.neuedu.demo.tijian.pojo.Hospital;
import com.neuedu.demo.tijian.pojo.Setmeal;
import com.neuedu.demo.tijian.service.EchartsService;
import com.neuedu.demo.tijian.service.HospitalService;
import com.neuedu.demo.tijian.service.OrdersService;
import com.neuedu.demo.tijian.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private SetmealService setmealService;
    @Override
    public Map<String,Integer> getHpsData() {
        List<Hospital> hospitals = hospitalService.getHospitals();

        Map<String,Integer> hpData = new HashMap<>();
        for (Hospital hospital : hospitals) {
            int count = ordersService.countByHpId(hospital.getHpid());
            hpData.put(hospital.getName(), count);
        }
        return hpData;
    }

    @Override
    public Map<String, Integer> getMealsData(Integer hpId, Integer type) {
        Map<String, Integer> mealData = new HashMap<>();
        List<Setmeal> setMeals = setmealService.getSetmeals(type);
        System.out.println(setMeals);
        for (Setmeal setmeal : setMeals) {
            int count = ordersService.countByHpIdAndMealId(hpId, setmeal.getSmid());
            mealData.put(setmeal.getName(), count);
        }
        return mealData;
    }
}
