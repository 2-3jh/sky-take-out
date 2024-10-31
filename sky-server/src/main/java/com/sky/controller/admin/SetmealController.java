package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜品相关接口
 */
@Api(tags="套餐相关接口")
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @ApiOperation("新增套餐")
    @PostMapping
    public Result insert(@RequestBody  SetmealDTO setmealDTO) {
        log.info("新增套餐{}",setmealDTO);
        setmealService.insert(setmealDTO);
        return Result.success();
    }

    /**
     *根据id查询套餐
     */
    @ApiOperation("根据id查询套餐")
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根据id查询套餐{}",id);
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @ApiOperation("分页查询套餐")
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult= setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 根据套餐id改变套餐的状态
     * @return
     */
    @ApiOperation("改变套餐的状态")
    @PostMapping("/status/{status}")
    public Result status(@PathVariable Integer status,int id) {
        setmealService.updateStatus(id,status);
        return Result.success();
    }

}
