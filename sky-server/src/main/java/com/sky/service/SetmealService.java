package com.sky.service;


import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;

public interface SetmealService {

    /**
     * 添加套餐
     */
    void insert (SetmealDTO setmealDTO);
}
