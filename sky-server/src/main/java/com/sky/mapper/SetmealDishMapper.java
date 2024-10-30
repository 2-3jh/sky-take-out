package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);

    @Insert("insert into sky_take_out.setmeal_dish ( setmeal_id, dish_id, name, price, copies) " +
            "values(#{setmealId},#{dishId},#{name},#{price},#{copies})")
    void insert(SetmealDish setmealDish);
}
