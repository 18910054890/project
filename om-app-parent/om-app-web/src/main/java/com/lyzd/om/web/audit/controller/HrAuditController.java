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

import com.lyzd.om.emp.info.service.UpdateEmployeeInfoService;
import com.lyzd.om.emp.sdk.command.AuditCommand;
import com.lyzd.om.spring.common.dto.JwtUserDto;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.workflow.emp.represation.AuditContentRepresentation;
import com.lyzd.om.workflow.emp.service.impl.HrAuditService;
import com.lyzd.om.workflow.emp.service.impl.QueryModifyInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *  Hr审核
 *  
 * @author Thinker
 *
 */
@RestController
@RequestMapping("/api/audit")
@Api(tags = "Hr：员工信息审核")
public class HrAuditController {
	
	@Autowired
	private HrAuditService hrAuditService;
	
	@Autowired
	private QueryModifyInfoService queryModifyInfoService;
	
	@Autowired
	private UpdateEmployeeInfoService updateEmployeeInfoService;
	
	
    @SuppressWarnings("unchecked")
	@GetMapping(value = "/countHrTask")
    @ResponseBody
    @ApiOperation(value = "统计任务总数")
    //@PreAuthorize("hasAnyAuthority('hrAudit:list')")
    //@MyLogA("查询任务")
    public Result<Long> countHrTask(){
    	return Result.ok().count(hrAuditService.countHrTask());
    }
    
    /**
     * 可选择提交人、所在部门、或是提交时间（1天内、3天内、7天内、1个月内、具体日期）进行待审批搜索
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/queryTasks")
    @ResponseBody
    @ApiOperation(value = "查询任务列表")
    //@PreAuthorize("hasAnyAuthority('hrAudit:list')")
    public Result queryTasks(String name, String deptName, String startTime, String endTime, Integer currentPage, Integer pageSize){
    	
    	return hrAuditService.queryTasks(name, deptName, startTime, endTime, currentPage, pageSize);
    	
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/queryAuditContent/{id}")  
    @ResponseBody
    @ApiOperation(value = "查询审核内容")
    //@PreAuthorize("hasAnyAuthority('hrAudit:list')")
    public Result<AuditContentRepresentation> queryAuditContent(@PathVariable(name = "id") String id){
    	AuditContentRepresentation ar = queryModifyInfoService.queryAuditContent(id);
    	return Result.ok().resData(ar);
    			
    	
    }
    
    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/hrAudit")
	@ResponseStatus(CREATED)
	@ApiOperation(value = "HR审核")
	public Result hrAudit(@RequestBody @Valid AuditCommand command) {
    	
    	command.setUserId(getJwtUserDto().getMyUser().getUserId());
    	command.setAuditor(getJwtUserDto().getMyUser().getNickName());
    	hrAuditService.hrAudit(command, updateEmployeeInfoService);
    	return Result.ok().message("操作成功");
    }
    
    private JwtUserDto getJwtUserDto() {
    	return (JwtUserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}
