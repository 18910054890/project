package com.lyzd.om.emp.info.representation;


import java.io.Serializable;

import lombok.Data;
import lombok.Value;

@Data
public class EmployeeRepresentation{
	private String name;// 姓名
	private String sex;// 性别
	private String phone;//手机号
	private String email;// 邮箱
	private String idcard;// 身份证
	private String politicsStatus;// 政治面貌
	private String isMarried;// 婚姻状况
	private String birthPlace;// 出生地
	private String birthDate;// 出生日期
	private String age;// 年龄
	private String domicile;// 户籍所在地省
	private String domicileAddr;// 户籍所在地地址
	private String registeredType;// 户籍性质
	private String familyLive;// 家庭地址
	private String familyPhone;// 家庭电话
	private String postalCode;// 邮编
	private String nation;// 民族
	private String sOSPersonName;// 紧急联系人
	private String sosPersonRelation;// 紧急联系人关系
	private String sOSPersonPhone   ;// 紧急联系人电话
	private String emStatus;// 员工信息提交状态
	private String bearStatus;
	/***目前居住地 */
	private String familyAddr;
	
 }
