package com.lyzd.om.web.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import com.lyzd.om.emp.sdk.command.AuditCommand;
import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;
import com.lyzd.om.workflow.emp.model.HrAuditTask;
import com.lyzd.om.workflow.emp.service.impl.EmployeeApplyService;
import com.lyzd.om.workflow.emp.service.impl.HrAuditService;

@SpringBootTest
public class EmployeeInfoAuditServiceTest {
	
	@Autowired
	HrAuditService hrAuditService;
	
	@Autowired
	private EmployeeApplyService empInfoUpdateApplyService;
	
	@Autowired
	private TaskService taskService;
	
	//@Test
	 public void deploy() {
		 hrAuditService.deploy();
	}
	 
	//@Test
	public void apply() {
		
		EmployeeApplyCommand command  = new EmployeeApplyCommand();
		command.setUserId(1);
		command.setName("wangmingzhe");
		command.setApplyType("Test-2");
		command.setDeptName("OpeningBank");
		empInfoUpdateApplyService.apply(command, null);
	}
	
	//@Test
	public void queryTasks() {
		System.out.println(hrAuditService.queryTasks("wangmingzhe", null, null, null, 1, 1));
	}
	
	//@Test
	public void count() {
		
    		
		Long conter = hrAuditService.count("hr");
		System.out.println(conter);
	}
	
	
	
	
	@Test
	public void hrAudit() {
		AuditCommand ac = new AuditCommand();
		
		ac.setId("c5c69cff224545d6a6f65f6cdf0b0118");
		ac.setIsPass("1");
		ac.setAuditOpinion("agree");
		hrAuditService.hrAudit(ac, null);
	}
	
	/**
	 * 人事经理审核不通过。（不通过打回到发起人。发起人可以根据流程id。重新提交）。rePack
	 * 
	 * @return
	 */
	//@Test
	public void hrReject() {
		List<Task> T = taskService.createTaskQuery().taskAssignee("hr").list();
		if (!ObjectUtils.isEmpty(T)) {
			for (Task item : T) {
				Map<String, Object> variables = new HashMap<>();
				variables.put("isSuccess", false);
				item.setAssignee("hr");
				taskService.setVariableLocal(item.getId(), "isSuccess", false);
				taskService.complete(item.getId(), variables);
				// modelRollBack.init(item.getId());

			}
		}
	}
	
	/**
	 * 人员重新申请
	 * 
	 * @return
	 */
	//@Test
	public Object reApply() {
		// Authentication.setAuthenticatedUserId("wmz");
		Map<String, Object> variables = new HashMap<>();
		variables.put("applyUser", "wmz_1");
		variables.put("hr", "hr");
		// 根据流程id获取属于自己的待办。
		List<Task> list = taskService.createTaskQuery().taskAssignee("wmz_1").
				taskDefinitionKey("sid-11C4CF2C-B2DB-4CA4-9758-46715CF178A0").list();
				//.processInstanceId("sid-11C4CF2C-B2DB-4CA4-9758-46715CF178A0").list();
		if (!ObjectUtils.isEmpty(list)) {
			for (Task item : list) {
				taskService.complete(item.getId(), variables);

			}
		}
		return list;
	}
	
	

}
