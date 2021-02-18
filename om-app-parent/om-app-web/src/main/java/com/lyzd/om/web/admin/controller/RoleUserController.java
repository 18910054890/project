package com.lyzd.om.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyzd.om.shared.entity.admin.MyRoleUser;
import com.lyzd.om.shared.service.admin.RoleUserService;
import com.lyzd.om.shared.service.log.aop.MyLogA;
import com.lyzd.om.spring.common.dto.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Thinker
 *
 */
@Controller
@RequestMapping("/api/roleuser")
@Api(tags = "系统：用户角色管理")
public class RoleUserController {
    @Autowired
    private RoleUserService roleUserService;

    @PostMapping()
    @ResponseBody
    @ApiOperation(value = "通过用户id返回用户角色")
    @PreAuthorize("hasAnyAuthority('user:list')")
    @MyLogA("查询用户角色")
    public Result getRoleUserByUserId(Integer userId) {
        List<MyRoleUser> tbRoleUser =roleUserService.getMyRoleUserByUserId(userId);
        if(tbRoleUser != null){
            return Result.ok().data(tbRoleUser);
        }else{
            return Result.error();
        }
    }
}
