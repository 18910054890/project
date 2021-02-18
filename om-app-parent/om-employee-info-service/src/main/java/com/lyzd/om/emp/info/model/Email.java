package com.lyzd.om.emp.info.model;

import com.lyzd.om.shared.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Email {
	/**邮件接收者**/
	private String recvicePersion;
}
