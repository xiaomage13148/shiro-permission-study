package com.xiaoma.sys.controller;

import com.xiaoma.sys.service.SysUserService;
import com.xiaoma.sys.utils.ResultInfo;
import com.xiaoma.sys.vo.SysUserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sys/register")
public class SysUserRegisterController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/back")
    public ResultInfo<Object> userRegister(@Valid @RequestBody SysUserRegisterVo vo) {
        sysUserService.userRegisterWithoutInfo(vo);
        return new ResultInfo<>().ok();
    }

}
