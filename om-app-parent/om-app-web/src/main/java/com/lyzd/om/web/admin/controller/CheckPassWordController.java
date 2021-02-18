package com.lyzd.om.web.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyzd.om.emp.info.sdk.commond.CheckPwdCommand;
import com.lyzd.om.emp.info.service.PwdService;
import com.lyzd.om.spring.common.dto.Result;
@Controller
@RequestMapping("/api/checkPwd")
public class CheckPassWordController {
	@Autowired
	private PwdService pwdService;
//(value = "/newPwd")
	@PostMapping
	@PreAuthorize("hasAnyAuthority('user:edit')")
	 @ResponseBody
	public Result updatePwd(@RequestBody @Valid CheckPwdCommand checkPwdCommand) {
		if (pwdService.checkPwd(checkPwdCommand)) {
			return Result.ok().message("修改成功");
		} else {
			return Result.error().message("密码强度等级不足");
		}

	}

}
