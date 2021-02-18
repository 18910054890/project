package com.lyzd.om.emp.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author GM
 *
 */
@Component
@Slf4j
public class EmailService {
//	@Autowired
//	private JavaMailSender mailSender;
//
//	@Value("${spring.mail.username}")
//	private String sendPersion;
//	private EmployeeRepository employeeRepository;
//
//	/**
//	 * @param to 接收者
//	 * @param subject
//	 * @param content
//	 * @return
//	 */
//	public boolean sendSimpleMail(String to, String subject, String content) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		// 增加取随机数6位
//		String a = "";
//
//		employeeRepository.save(null);
//		message.setFrom(sendPersion); // 邮件发送者
//		message.setTo(to); // 邮件接受者
//		message.setSubject(subject); // 主题
//		message.setText(content); // 内容
//
//		try {
//			mailSender.send(message);
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}
}
