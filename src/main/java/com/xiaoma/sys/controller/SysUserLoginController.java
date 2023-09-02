package com.xiaoma.sys.controller;

import com.xiaoma.sys.dto.TokenInfoDTO;
import com.xiaoma.sys.entity.SysUserEntity;
import com.xiaoma.sys.service.SysTokenService;
import com.xiaoma.sys.service.SysUserService;
import com.xiaoma.sys.utils.ResultInfo;
import com.xiaoma.sys.vo.SysUserLoginByUsernameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/sys/login")
public class SysUserLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysTokenService sysTokenService;

    @PostMapping("/auth")
    public ResultInfo<TokenInfoDTO> authLogin(@Valid @RequestBody SysUserLoginByUsernameVo vo) {
        // 校验输入的账号密码是否正确
        SysUserEntity sysUserEntity = sysUserService.checkUserInputIsTrue(vo);
        // 根据用户信息生成token
        TokenInfoDTO tokenInfoDTO = sysTokenService.generateTokenByUserInfo(sysUserEntity);
        return new ResultInfo<TokenInfoDTO>().ok(tokenInfoDTO);
    }

    // TODO 登录成功之后需要的操作

}
