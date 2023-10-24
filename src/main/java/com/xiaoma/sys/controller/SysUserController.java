package com.xiaoma.sys.controller;

import com.xiaoma.sys.dto.UserInfoDTO;
import com.xiaoma.sys.entity.SysUserEntity;
import com.xiaoma.sys.service.SysUserService;
import com.xiaoma.sys.utils.ResultInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/getAllUser")
    public ResultInfo<List<UserInfoDTO>> getAllUser() {
        List<SysUserEntity> userEntityList = sysUserService.list();
        List<UserInfoDTO> userInfoDTOList = userEntityList.stream().map(user -> {
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            BeanUtils.copyProperties(user, userInfoDTO);
            return userInfoDTO;
        }).collect(Collectors.toList());
        return new ResultInfo<List<UserInfoDTO>>().ok(userInfoDTOList);
    }
}
