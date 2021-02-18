package com.lyzd.om.shared.service.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.lyzd.om.spring.common.dto.Result;

/**
 * @author Thinker
 *
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {

    private String defaultFilterProcessUrl = "/login";
    private String method = "POST";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    	//TODO delete false condition...
    	if(false) {
    		if (method.equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
                // 登录请求校验验证码，非登录请求不用校验
                HttpSession session = request.getSession();
                String requestCaptcha = request.getParameter("captcha");
                //验证码的信息存放在seesion种，具体看EasyCaptcha官方解释
                String genCaptcha = (String) request.getSession().getAttribute("captcha");
                response.setContentType("application/json;charset=UTF-8");
                if (StringUtils.isEmpty(requestCaptcha)){
                    //删除缓存里的验证码信息
                    session.removeAttribute("captcha");
                    response.getWriter().write(JSON.toJSONString(Result.error().message("验证码不能为空!")));
                    return;
                }
                if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                    session.removeAttribute("captcha");
                    response.getWriter().write(JSON.toJSONString(Result.error().message("验证码错误!")));
                    return;
                }
            }
    	}
        
        chain.doFilter(request, response);
    }
    
    
//    /**
//     * 对图片验证码进行校验
//     * @param servletWebRequest：请求参数 包含表单提交的图片验证码信息
//     * @throws ServletRequestBindingException
//     * @throws ValidateCodeException: 验证码校验失败 抛出异常
//     */
//    private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException, ValidateCodeException {
//        //从Session获取保存在服务器端的验证码
//        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
//
//        //获取表单提交的图片验证码
//        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
//
//        //验证码空校验
//        if (StringUtils.isBlank(codeInRequest)) {
//            throw new ValidateCodeException("验证码不能为空！");
//        }
//
//        //验证码校验
//        if (codeInSession == null) {
//            throw new ValidateCodeException("验证码不存在，请重新发送！");
//        }
//
//        //验证码过期校验
//        if (codeInSession.isExpire()) {
//            sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
//            throw new ValidateCodeException("验证码已过期！");
//        }
//
//        //判断是否相等
//        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
//            throw new ValidateCodeException("验证码不正确！");
//        }
//
//        //从Session移除该字段信息
//        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
//    }
}
