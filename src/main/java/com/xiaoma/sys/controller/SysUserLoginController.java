package com.xiaoma.sys.controller;

import com.xiaoma.sys.utils.ResultInfo;
import com.xiaoma.sys.vo.SysUserLoginByUsernameVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/login")
public class SysUserLoginController {

    @PostMapping("/auth")
    public ResultInfo<Object> authLogin(@Valid @RequestBody SysUserLoginByUsernameVo vo) {
        // TODO 尚未完成登录功能
        log.info("登录成功 , 参数: {}" , vo.toString());
        return new ResultInfo<>().ok();
    }

}
