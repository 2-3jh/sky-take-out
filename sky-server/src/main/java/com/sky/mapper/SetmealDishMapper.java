package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);

    @Insert("insert into sky_take_out.setmeal_dish ( setmeal_id, dish_id, name, price, copies) " +
            "values(#{setmealId},#{dishId},#{name},#{price},#{copies})")
    void insert(SetmealDish setmealDish);

    /**
     * 根据套餐的id删除数据
     * @param setmealId
     */
    @Delete("delete from sky_take_out.setmeal_dish where setmeal_id=#{setmealId}")
    void deleteBySetmealId(Long setmealId);

    @Select("select * from sky_take_out.setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getSetmealByDishId(Long id);
}
