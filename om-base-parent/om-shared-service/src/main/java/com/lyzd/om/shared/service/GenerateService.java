package com.lyzd.om.shared.service;

import java.util.List;

import com.lyzd.om.shared.model.common.BeanField;
import com.lyzd.om.shared.model.common.GenerateInput;


public interface GenerateService {

	/**
	 * 获取数据库表信息
	 * 
	 * @param tableName
	 * @return
	 */
	List<BeanField> listBeanField(String tableName);

	/**
	 * 转成驼峰并大写第一个字母
	 * 
	 * @param string
	 * @return
	 */
	String upperFirstChar(String string);

	/**
	 * 生成代码
	 * 
	 * @param input
	 */
	void saveCode(GenerateInput input);
}
