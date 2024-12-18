package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from sky_take_out.dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page pageQuery(DishPageQueryDTO dishPageQueryDTO);


    @Select("select status from sky_take_out.dish where id=#{id}")
    int getstatus(long id);

    @Delete("Delete from sky_take_out.dish where id=#{id}")
    void deteleById(Long id);

    /**
     * 根据id插叙菜品
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.dish where id =#{id}")
    Dish getById(Long id);

    /**
     * 跟新菜品
     * @param dish
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据分类的id查询菜品
     * @param categoryId
     * @return
     */
    @Select("select * from sky_take_out.dish where category_id=#{categoryId}")
    List<DishVO> getByCategoryId(Long categoryId);
}
