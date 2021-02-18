package com.lyzd.om.shared.service;

import java.util.List;

import com.lyzd.om.shared.model.common.Mail;


public interface MailService {

	void save(Mail mail, List<String> toUser);
}
