package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用接口
 */
@RestController
@Api(tags="通用接口")
@Slf4j
@RequestMapping("/admin/common")
public class CommonController {

    @PostMapping("upload")
    public Result<String> fileUpload(MultipartFile file) {
        return Result.success(file.getOriginalFilename());
    }

}
