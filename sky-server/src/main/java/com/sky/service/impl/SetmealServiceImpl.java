package com.sky.service.impl;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {


    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    /**
     * 添加菜品
     * @param setmealDTO
     */
    @Override
    @Transactional
    public void insert(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //插入套餐信息
        setmealMapper.insert(setmeal);
        //获取套餐的id
        Long setMealId = setmeal.getId();
        //对setmealDish进行赋值
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        //添加setmealDish
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setMealId);
            setmealDishMapper.insert(setmealDish);
        }
    }
}
