package com.lyzd.om.spring.common.security.handler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lyzd.om.spring.common.dto.JwtUserDto;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.spring.common.utils.JwtUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Thinker
 *
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    	List<Map<String, Object>>  reDateList = new ArrayList();
    	Map map = new HashMap();
         JwtUserDto userDetails = (JwtUserDto)authentication.getPrincipal();//拿到登录用户信息
         String firstLogin = userDetails.getMyUser().getFirstLogin();
         String phoneNo = userDetails.getMyUser().getPhone();
         Integer userId = userDetails.getMyUser().getUserId();
         map.put("firstLogin",firstLogin);
         map.put("phoneNo",phoneNo);
         map.put("userId",userId);
         reDateList.add(map);
        Result result = Result.ok().message("登录成功");
        result.setData(reDateList);
        //修改编码格式
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json");
        //输出结果
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }
}
