package com.neuedu.demo.tijian.service;

import com.neuedu.demo.tijian.pojo.Hospital;
import com.neuedu.demo.tijian.pojo.Setmeal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EchartsService {
    public Map<String, Integer> getHpsData();

    public Map<String, Integer> getMealsData(Integer hpId, Integer type);
}
