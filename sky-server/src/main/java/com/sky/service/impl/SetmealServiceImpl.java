package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
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
        setmeal.setStatus(StatusConstant.DISABLE);
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

    @Override
    public SetmealVO getById(Long id) {
        SetmealVO setmealVO = setmealMapper.getById(id);
        return setmealVO;
    }

    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        //开启分页查询
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());

        //进行分页查询
        Page<SetmealVO> result =  setmealMapper.page(setmealPageQueryDTO);

        //返回
        return new PageResult(result.getTotal(),result.getResult());
    }

    @Override
    public void updateStatus(long id, Integer status) {
        Setmeal setmeal = Setmeal.builder().
                id(id)
                .status(status)
                .build();
        setmealMapper.update(setmeal);
    }

    /**
     *
     */

}
