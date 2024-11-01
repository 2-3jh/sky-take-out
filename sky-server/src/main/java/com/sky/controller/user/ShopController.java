package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 管理端店铺状态相关接口
 */
@RestController("adminShopController")
@Api(tags="用户端店铺相关接口")
@RequestMapping("/user/shop")
@Slf4j
public class ShopController {


    public final static String KEY = "SHOP_STATUS";
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 获取营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus() {
        Integer status = (Integer)redisTemplate.opsForValue().get(KEY);
        log.info("获取营业状态,{}",status==1?"营业中":"打烊中");
        return Result.success(status);
    }

}
