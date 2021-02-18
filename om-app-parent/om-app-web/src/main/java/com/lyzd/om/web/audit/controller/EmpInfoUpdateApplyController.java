package com.lyzd.om.web.audit.controller;

import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lyzd.om.emp.sdk.command.EmpInfoUpdateReApplyCommand;
import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;
import com.lyzd.om.spring.common.dto.JwtUserDto;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.workflow.emp.service.impl.EmployeeApplyService;
import com.lyzd.om.workflow.emp.service.impl.EmployeeTaskQueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *  员工信息更新申请
 *  
 * @author Thinker
 *
 */
@RestController
@RequestMapping("/api/infoUpdate")
@Api(tags = "Employee：员工信息更新申请")
public class EmpInfoUpdateApplyController {
	
	private EmployeeApplyService employeeApplyService;
	private EmployeeTaskQueryService employeeTaskQueryService;
	
	
	/**
	 * Constructor injection.
	 * @param employeeApplyService
	 * @param employeeTaskQueryService
	 */
	@Autowired
	public EmpInfoUpdateApplyController(EmployeeApplyService employeeApplyService,
			EmployeeTaskQueryService employeeTaskQueryService) {
		super();
		this.employeeApplyService = employeeApplyService;
		this.employeeTaskQueryService = employeeTaskQueryService;
	}

	@PostMapping(value = "/apply")
	@ResponseStatus(CREATED)
	@ApiOperation(value = "员工信息更新申请")
	public Result apply(@RequestBody @Valid EmployeeApplyCommand command) {
		JwtUserDto user = this.getJwtUserDto();
		command.setUserId(user.getMyUser().getUserId());
		command.setName(user.getUsername());
		command.setDeptName(user.getMyUser().getDeptName());
		command.setApplyType("Employee-Info-Edit");
		
		employeeApplyService.apply(command, null);
		return Result.ok().message("操作成功");
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/queryApply/{currentPage}/{pageSize}")
    @ResponseBody
    @ApiOperation(value = "查询申请")
    //@PreAuthorize("hasAnyAuthority('hrAudit:list')")
    public Result queryApply(@PathVariable(name = "currentPage") int currentPage,@PathVariable(name = "pageSize") int pageSize){
		JwtUserDto user = this.getJwtUserDto();
		return employeeApplyService.queryApply(user.getMyUser().getUserId()+"", currentPage, pageSize);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/queryTasks")
    @ResponseBody
    @ApiOperation(value = "查询任务列表")
    //@PreAuthorize("hasAnyAuthority('hrAudit:list')")
    public Result queryTasks(){
		String userId = this.getJwtUserDto().getMyUser().getUserId()+"";
    	return  Result.ok().data(employeeApplyService.queryTasks(userId, 0, 10, employeeTaskQueryService));
    }
    
    private JwtUserDto getJwtUserDto() {
    	return (JwtUserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    @PostMapping(value = "/reApply")
	@ResponseStatus(CREATED)
	@ApiOperation(value = "重新申请")
	public Result reApply(@RequestBody @Valid EmpInfoUpdateReApplyCommand command) {
    	
		JwtUserDto user = this.getJwtUserDto();
		command.setUserId(user.getMyUser().getUserId());
		command.setName(user.getUsername());
		employeeApplyService.reApply(command, null); 
		return Result.ok().message("操作成功");
	}

    
}
