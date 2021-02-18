package com.lyzd.om.shared.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.shared.dao.MailDao;
import com.lyzd.om.shared.model.common.Mail;
import com.lyzd.om.shared.service.MailService;
import com.lyzd.om.shared.service.SendMailSevice;
import com.lyzd.om.shared.service.utils.UserUtil;

//@Service
public class MailServiceImpl implements MailService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SendMailSevice sendMailSevice;
	@Autowired
	private MailDao mailDao;

	@Override
	@Transactional
	public void save(Mail mail, List<String> toUser) {
		mail.setUserId(UserUtil.getLoginUser().getId());
		mailDao.save(mail);

		toUser.forEach(u -> {
			int status = 1;
			try {
				sendMailSevice.sendMail(u, mail.getSubject(), mail.getContent());
			} catch (Exception e) {
				log.error("发送邮件失败", e);
				status = 0;
			}

			mailDao.saveToUser(mail.getId(), u, status);
		});

	}

}
