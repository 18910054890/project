package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.CheckPwd;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;
import com.lyzd.om.spring.common.utils.Md5;
import com.lyzd.om.spring.common.utils.PasswordEncoderImpl;


@Component
public class LoginRepository extends BaseRepository<CheckPwd>{
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	private DefaultObjectMapper objectMapper;
	
	@Autowired
	public LoginRepository(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doSave(CheckPwd checkPwd) {
//		String newPwd =Md5.crypt(checkPwd.getNewPwd());
//		String newPwd =passwordEncoder.encode(checkPwd.getNewPwd());
		String newPwd1 = checkPwd.getNewPwd();
		PasswordEncoderImpl passwordEncoderImpl = new PasswordEncoderImpl();
		String newPwd = passwordEncoderImpl.encode(newPwd1);
		String loginFlag = "1";
		String sql = "update my_user set password =:newPwd,first_login =:loginFlag WHERE phone=:phoneNo;";
		
		Map<String, String> paramMap = of("newPwd", newPwd,"loginFlag", loginFlag, "phoneNo", checkPwd.getPhoneNo());
		jdbcTemplate.update(sql, paramMap);
		
	}
	
	public boolean updatePwd(String newPwd,String phoneNo) {
//		newPwd =Md5.crypt(newPwd);
		String sql = "update my_user set password =:newPwd and set first_login = '1' WHERE phone=:phoneNo;";
		Map<String, String> paramMap = of("newPwd", newPwd, "phoneNo", phoneNo);
		jdbcTemplate.update(sql, paramMap);
		return true;
	}

}
