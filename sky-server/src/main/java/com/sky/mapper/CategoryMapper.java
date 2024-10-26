package com.sky.mapper;

import com.sky.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into sky_take_out.category (id,type,name,sort,status,create_time,create_user,update_time,update_user) " +
            "values (#{id},#{type},#{name},#{sort},#{status},#{createTime},#{createUser},#{updateTime},#{updateUser}) ")
    void save(Category category);
}
