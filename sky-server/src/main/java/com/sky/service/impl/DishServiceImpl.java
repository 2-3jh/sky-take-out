package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 根据id批量删除
     * @param ids
     */
    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        //判断状态是否为起售
        for (Long id : ids) {
            if(dishMapper.getstatus(id)== StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        //判断是否有关联的套餐
        List<Long> list=setmealDishMapper.getSetMealIdsByDishIds(ids);
        if(list!=null&&list.size()>0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        //删除菜品
        for (Long id : ids) {
            //删除菜品
            dishMapper.deteleById(id);
            //删除口味
            dishFlavorMapper.deteleByDishId(id);
        }
        //删除口味
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public DishVO getDishAndFlavorsById(Long id) {
        Dish dish=dishMapper.getById(id);
        List<DishFlavor> list=dishFlavorMapper.getByDishId(id);
        DishVO dishVO=new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(list);
        return dishVO;
    }

    /**
     * 更新菜品
     * @param dishDTO
     */
    @Transactional
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);

        //删除菜品的所有口味
        dishFlavorMapper.deteleByDishId(dishDTO.getId());
        //添加菜品的新口味
        Long dishId=dishDTO.getId();
        List<DishFlavor> flavors=dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            flavors.forEach(flavor -> {flavor.setDishId(dishId);});
            dishFlavorMapper.insertBatch(flavors);
        }

    }

    @Override
    public List<DishVO> getByCategoryId(Long categoryId) {
        List<DishVO> list = dishMapper.getByCategoryId(categoryId);
        return list;
    }


    /**
     * 新增菜品
     * @param dishDTO
     */
    @Override
    public void saveDishWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insert(dish);

        Long id = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            flavors.forEach(flavor -> {flavor.setDishId(id);});
            dishFlavorMapper.insertBatch(flavors);
        }


    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }


}
