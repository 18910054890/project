package com.lyzd.om.emp.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.emp.info.model.CheckPwd;
import com.lyzd.om.emp.info.repository.LoginRepository;
import com.lyzd.om.emp.info.sdk.commond.CheckPwdCommand;

import lombok.extern.slf4j.Slf4j;

/**
 * @author GM
 *
 */
@Slf4j
@Component
//@Repository
public class PwdService {
	private LoginRepository loginRepository;
	
	public PwdService() {
	}

    @Autowired
	public PwdService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
	 @Transactional
	public boolean checkPwd(CheckPwdCommand checkPwdCommand) {
		 CheckPwd checkPwd = CheckPwd.create(checkPwdCommand.getNewPwd(), checkPwdCommand.getPhoneNo());
		if(CheckPwd.checkPassWord(checkPwd.getNewPwd())) {
			loginRepository.save(checkPwd);
//			String sql = "update my_user set password =? and set first_login = '1' WHERE user_name=? and  user_id ='8'  and dept_id='6';";
//			Map<String, String> paramMap = of("newPwd", newPwd, "phoneNo", phoneNo);
//			jdbcTemplate.update(sql, paramMap);
//			return loginRepository.updatePwd(newPwd,phoneNo);
			return true;
		}else {
			return false;
		}
		
	}
}
