package com.lyzd.om.emp.info.sdk.commond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckPwdCommand {
	private String newPwd;
	private String phoneNo;
}
