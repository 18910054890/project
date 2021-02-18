package com.lyzd.om.workflow.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.sdk.event.MessageNotifyEvent;
import com.lyzd.om.shared.entity.admin.MyRole;
import com.lyzd.om.spring.common.dto.JwtUserDto;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.workflow.emp.model.Message;
import com.lyzd.om.workflow.emp.model.repository.MessageRepository;

/**
 * MessageService
 * 
 * @author Thinker
 *
 */
@Component
public class MessageService {

	@Autowired
	MessageRepository hrMessageRepository;

	public void save(MessageNotifyEvent event) {
		Message hm = Message.create(event.getInitUserId()+"", event.getReader(),
				event.getApplyType(), event.getBussinesId(), "0",event.getCreatedAt());
		hrMessageRepository.save(hm);
	}
	
	 /**
	  * Count HR message amount for display.
	 * @return
	 */
	public long count(JwtUserDto jwtUserDto) {
		 return hrMessageRepository.count(getReader(jwtUserDto));
	 }
	
	/**
	 * query by Id
	 * @param id
	 * @return
	 */
	public Message byId(String id) {
		return hrMessageRepository.byId(id);
	}

	/**
	 * Marking readFlag
	 * @param id
	 */
	public void updateStatus(String id) {
		Message hm = hrMessageRepository.byId(id);
		hm.setIsRead("1");
		hrMessageRepository.save(hm);

	}
	
	public Result queryMessages(JwtUserDto jwtUserDto, int currentPage, int pageSize) {
		return hrMessageRepository.queryMessages(getReader(jwtUserDto), currentPage, pageSize);
	}
	
	private String getReader(JwtUserDto jwtUserDto) {
		
		return isHR(jwtUserDto) == true ? "HR" : jwtUserDto.getMyUser().getUserId() + "";
	}
	
	/** is HR?
	 * @param jwtUserDto
	 * @return
	 */
	private boolean isHR(JwtUserDto jwtUserDto) {
		List<MyRole> roles = jwtUserDto.getRoleInfo();
		for (MyRole role : roles) {
			if("HR".equalsIgnoreCase(role.getRoleName())) {
				return true;
			}
		}
		return false;
		
	}

}
