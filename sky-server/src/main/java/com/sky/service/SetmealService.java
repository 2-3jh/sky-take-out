package com.sky.service;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    /**
     * 添加套餐
     */
    void insert (SetmealDTO setmealDTO);

    SetmealVO getById(Long id);

    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void updateStatus(long id, Integer status);

    void deteleBatch(List<Long> ids);

    void update(SetmealDTO setmealDTO);
}
