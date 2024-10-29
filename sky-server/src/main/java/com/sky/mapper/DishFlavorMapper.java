package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     *批量插入数据
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品的id删除口味
     * @param id
     */
    @Delete("delete from sky_take_out.dish_flavor where id=#{id}")
    void deteleByDishId(Long id);

    /**
     * 根据菜品的id查询口味
     * @param dishId
     * @return
     */
    @Select("select * from sky_take_out.dish_flavor where dish_id=#{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
