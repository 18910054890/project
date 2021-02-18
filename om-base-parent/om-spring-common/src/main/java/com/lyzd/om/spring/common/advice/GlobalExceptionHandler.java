package com.lyzd.om.spring.common.advice;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lyzd.om.shared.exception.MyException;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.spring.common.dto.ResultCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Thinker
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 指定处理什么异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result error(Exception e) {
		log.error(e.getMessage());
		e.printStackTrace();
		return Result.error().message("执行了全局异常");
	}

	/**
	 * 自定义异常
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MyException.class)
	public Result error(MyException e) {
		log.error(e.getMessage());
		e.printStackTrace();
		return Result.error().code(e.getCode()).message(e.getMsg());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public Result handleAuthorizationException(AccessDeniedException e) {
		log.error(e.getMessage());
		return Result.error().code(ResultCode.FORBIDDEN).message("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(BadCredentialsException.class)
	public Result userNotFound(BadCredentialsException e) {
		log.error(e.getMessage());
		return Result.error().code(ResultCode.FORBIDDEN).message("用户名或者密码错误");
	}

	@ExceptionHandler(LockedException.class)
	public Result userLocked(LockedException e) {
		log.error(e.getMessage());
		return Result.error().code(ResultCode.FORBIDDEN).message(e.getMessage());
	}

	@ExceptionHandler(AuthenticationServiceException.class)
	public Result handleAuthenticationServiceException(AuthenticationServiceException e) {
		log.error(e.getMessage());
		return Result.error().message("验证码错误");
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public Result handleInvalidRequest(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		
		log.error(e.getMessage());
		StringBuilder msg =  new StringBuilder();
		Map<String, Object> error = e.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, fieldError -> {
					String message = fieldError.getDefaultMessage();
					if(message !=null && message.trim().length() > 0) {
						msg.append(message).append(" ");
					}
					
					return message;
				}));
		
		return Result.error().message(isEmpty(msg) ? "无错误提示" : msg.toString());
	}
}
