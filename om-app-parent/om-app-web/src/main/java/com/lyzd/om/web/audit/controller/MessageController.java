package com.lyzd.om.web.audit.controller;



import static org.springframework.http.HttpStatus.CREATED;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lyzd.om.spring.common.dto.JwtUserDto;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.workflow.emp.service.impl.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *  Hr审核
 *  
 * @author Thinker
 *
 */
@RestController
@RequestMapping("/api/msg")
@Api(tags = "msg：消息通知")
public class MessageController {
	
	@Autowired
	private MessageService hrMessageService;

    @SuppressWarnings("unchecked")
	@GetMapping(value = "/count")
    @ResponseBody
    @ApiOperation(value = "统计消息总数")
    //@PreAuthorize("hasAnyAuthority('hrAudit:list')")
    //@MyLogA("查询任务")
    public Result<Long> count(){
    	return Result.ok().count(hrMessageService.count(this.getJwtUserDto()));
    }
    
	@GetMapping(value = "/query")
    @ResponseBody
    @ApiOperation(value = "查询任务列表")
    //@PreAuthorize("hasAnyAuthority('hrAudit:list')")
    public Result query(HttpServletRequest request){
		int currentPage = 0;
		int pageSize = 100;
		
		if(request.getParameter("currentPage") != null && request.getParameter("currentPage") != null) {
    		currentPage = Integer.valueOf(request.getParameter("currentPage"));
    		pageSize = Integer.valueOf(request.getParameter("pageSize"));
    	}
    	return hrMessageService.queryMessages(this.getJwtUserDto(), 0, 10);
    }
    
    @SuppressWarnings("rawtypes")
	@PutMapping(value = "/updateStatus/{id}")
	@ResponseStatus(CREATED)
	@ApiOperation(value = "更新消息状态")
	public Result updateStatus(@PathVariable(name = "id") String id) {
    	hrMessageService.updateStatus(id);
    	return Result.ok().message("操作成功");
    }
    
    private JwtUserDto getJwtUserDto() {
    	return (JwtUserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}
