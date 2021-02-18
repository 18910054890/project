package com.lyzd.om.shared.service;

import com.lyzd.om.shared.model.common.LoginUser;
import com.lyzd.om.shared.model.common.Token;

/**
 * Token管理器<br>
 * 可存储到redis或者数据库<br>
 * 具体可看实现类<br>
 * 默认基于redis，实现类为 com.lyzd.om.server.service.impl.TokenServiceJWTImpl<br>
 * 如要换成数据库存储，将TokenServiceImpl类上的注解@Primary挪到com.lyzd.om.server.service.impl.TokenServiceDbImpl
 * 
 */
public interface TokenService {

	Token saveToken(LoginUser loginUser);

	void refresh(LoginUser loginUser);

	LoginUser getLoginUser(String token);

	boolean deleteToken(String token);

}
