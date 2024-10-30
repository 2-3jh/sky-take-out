package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜品管理
 */
@Api(tags="菜品相关接口")
@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @ApiOperation("新增菜品")
    @PostMapping
    public Result Save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.saveDishWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询云{}",dishPageQueryDTO);
        PageResult pageResult=dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 删除菜品
     */
    @ApiOperation("删除菜品")
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品{}，ids");
        dishService.deleteBatch(ids);
        return Result.success();
    }
    /**
     * 获取菜品信息
     */
    @ApiOperation("跟据id查询菜品信息")
    @GetMapping("/{id}")
    public Result<DishVO> findById(@PathVariable Long id){
        log.info("根据id查询菜品信息：{}",id);
        DishVO dishVO=dishService.getDishAndFlavorsById(id);
        return Result.success(dishVO);
    }


    /**
     * 更新菜品信息
     * @return
     */
    @ApiOperation("更新菜品信息")
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO){
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 根据分类的id查询菜品
     */
    @ApiOperation("根据分类的id查询菜品")
    @GetMapping("/list")
    public Result getByCategoryId( Long categoryId){
        List<DishVO> list = dishService.getByCategoryId(categoryId);
        return Result.success(list);
    }
}
