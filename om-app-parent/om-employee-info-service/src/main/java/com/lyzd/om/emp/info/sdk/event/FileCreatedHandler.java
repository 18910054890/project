package com.lyzd.om.emp.info.sdk.event;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lyzd.om.emp.info.model.Employee;
import com.lyzd.om.emp.info.model.InterViewInfo;
import com.lyzd.om.emp.info.model.Resumption;
import com.lyzd.om.emp.info.repository.EmployeeRepository;
import com.lyzd.om.emp.info.sdk.commond.CreateEducationCommand;
import com.lyzd.om.emp.info.sdk.commond.CreateEmployeeCommand;
import com.lyzd.om.emp.info.sdk.commond.CreateSkillCommand;
import com.lyzd.om.emp.info.sdk.commond.CreateWorkExperienceCommand;
import com.lyzd.om.emp.info.sdk.commond.InterViewInfoCommond;
import com.lyzd.om.emp.info.sdk.commond.ResumptionCommond;
import com.lyzd.om.emp.info.service.EmployeeService;
import com.lyzd.om.shared.annotation.ConsumerPointCut;
import com.lyzd.om.shared.entity.admin.MyUser;
import com.lyzd.om.shared.event.EventHandler;


import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * First, Read and resolve file by file name. Finally, persist to database.
 *
 * @author Thinker
 *
 */
@Slf4j
@Component
public class FileCreatedHandler implements EventHandler<FileCreatedEvent> {
	private EmployeeRepository employeeRepository;
    private static final String XLS="xls";
    private static final String XLSX="xlsx";
    
    @Autowired
    private EmployeeService employeeService;

    
    //UserService
    
	@ConsumerPointCut
	public void handleEvent(FileCreatedEvent event) {
		try {
			String fileName = event.getFileName();
			FileInputStream fileInputStream = new FileInputStream(fileName);
			getCourseListByExcel(fileInputStream, fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取上会excel表格的数据 
	 */
	public  List getCourseListByExcel1(InputStream in, String fileName) throws Exception {

		List list = new ArrayList<>();
		// 创建excel工作簿
		Workbook work = getWorkbook(in, fileName);
		
		if (null == work) {
			throw new Exception("创建Excel为空！");
		}

		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 滤过第一行标题
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				
				List<Object> li = new ArrayList<>();
				for (int y = 0; y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					// 日期类型转换
//					if (y == 3) {
//						// cell.setCellType(CellType.STRING);
//						double s1 = cell.getNumericCellValue();
//						Date date = HSSFDateUtil.getJavaDate(s1);
//						li.add(date);
//						continue;
//					}

					li.add(cell); //添加本行每格的数据
				}
				list.add(li);     //添加每行的简历数据给list
			}
		}
		work.close();
		return list;
	}
	
	
	
	/**
	 * 获取简历excel表格的数据 
	 */
	public  List getCourseListByExcel(InputStream in, String fileName) throws Exception {

		List list = new ArrayList<>();
		// 创建excel工作簿
		Workbook work = getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel为空！");
		}

		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 滤过第一行标题
			for (int j = 3; j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row == null || row.getFirstCellNum() == j) {
					continue;
				}
				
				List<Object> li = new ArrayList<>();
				for (int y = 2; y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					// 日期类型转换
//					if (y == 3) {
//						// cell.setCellType(CellType.STRING);
//						double s1 = cell.getNumericCellValue();
//						Date date = HSSFDateUtil.getJavaDate(s1);
//						li.add(date); 
//						continue;
//					}

					li.add(cell); //添加本行每格的数据
				}
				list.add(li);     //添加每行的简历数据给list
			}
		}
		work.close();
		return list;
	}
	
	
	/**
	 * 保存上会excel中的信息
	 */
    public  void saveExcelInfo2(List list) {
		// 为字段赋值
    	Employee employee=null;
		//ResumptionCommond resumptionCommond=new ResumptionCommond();
		//InterViewInfoCommond  interViewInfoCommond=new InterViewInfoCommond();
    	InterViewInfo interViewInfo=new InterViewInfo();
    	Resumption resumption=new Resumption();
		
    	if(list==null) {
    		log.error("未获取到excel的数据");
    		return ;
    	}
		for(int i=0;i<list.size();i++){//遍历每一行li
			List li=(List) list.get(i);
			if(li==null) {
				log.error("该行未获取到数据");
	    		return ;
			}
			int  j=0;
			if(li.get(0).toString().startsWith("P")) {//老员工上会表
				//employee=Employee.create((Integer)li.get(0),li.get(1).toString(),li.get(2).toString());
				j=3;
			}else {
				employee=Employee.create(li.get(0).toString(),li.get(1).toString());
				j=2;
			}
			//遍历每个li的cell 
			resumption.setOfficeDate(li.get(j).toString());
			resumption.setSendOfferTime(li.get(j+1).toString());
				
			interViewInfo.setInterviewRate(li.get(j+5).toString());//不取入职时间、入职年、入职月
			interViewInfo.setCompInterviewer(li.get(j+6).toString());
			interViewInfo.setHnInterviewer(li.get(j+7).toString());

			resumption.setComInsideLevel(li.get(j+8).toString());//公司内部级别
			resumption.setDeptName(li.get(j+9).toString());
			resumption.setComInsidepost(li.get(j+10).toString());
			resumption.setCsName(li.get(j+11).toString()); //行内处室/自主研发
			resumption.setCsProject(li.get(j+12).toString());
			resumption.setHxLevl(li.get(j+13).toString());
			resumption.setTerritory(li.get(j+14).toString());
			resumption.setHeadman(li.get(j+15).toString());     
			resumption.setHr(li.get(j+21).toString());//对应招聘接口人
			
			addShanghui(employee,resumption,interViewInfo);
		}
			log.info("excel数据保存成功！");		
	} 

	/**
	 * 保存简历excel中的信息
	 */
    public  void saveExcelInfo(List list) {
		// 为字段赋值
		Employee employee=new Employee();
			if("个人信息".equals(list.get(0).toString())) {
//			employee.setName(list.get(2).toString());
//			employee.setSex(list.get(4).toString());
//			employee.setPhone(list.get(6).toString());
//			employee.setEmail(list.get(8).toString());
//			employee.setIdcard(list.get(10).toString());// 身份证
//			employee.setWorkExperience(list.get(10).toString());
//			employee.setPoliticsStatus(list.get(12).toString());// 政治面貌
//			employee.setIsMarried(list.get(14).toString());// 婚姻状况
//			employee.setBirthPlace(list.get(16).toString());//出生地
//			employee.setNowLiveAddr(list.get(18).toString());
			}
	
			//employeeService.save();
		
	}

    
	/**
	 * 保存简历excel中的信息
	 */
    public  void saveExcelInfo1(List list) {
		// 为字段赋值
		Employee employee=new Employee();
			if("个人信息".equals(list.get(0).toString())) {
//			
			}
	
			
//            String teacherId = teacherService.getTeacByName(courseList.get(2).toString());
//			 格式化时间
//			Date date = new SimpleDateFormat("yyyymmddhhmmss", Locale.US).parse(list.get(3).toString());
//			course.setCourseTime(
//					new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));
//
//			course.setClassRoom(courseList.get(4).toString());
//			course.setCourseWeek(
//					Integer.parseInt(new DecimalFormat("0").format(Double.parseDouble(courseList.get(5).toString()))));
//
//			course.setCourseType(courseList.get(6).toString());
//			// 通过院系名称查询院系id
//			Integer collegeId = collegeService.getCollegeByName(courseList.get(7).toString());
//			// 院系有误，直接跳过这条记录
//			course.setCollegeId(collegeId);
//			course.setScore(
//					Integer.parseInt(new DecimalFormat("0").format(Double.parseDouble(courseList.get(8).toString()))));
//			// 默认不开启选课
//			course.setIsOn(0);
//
//			logger.error("course = " + course);
//
//			Integer courseId = null;
//			courseId = courseService.getCourseByThree(course);

			EmployeeService employeeService = new EmployeeService();
			// 执行插入操作
			//employeeService.addEmployee(employee, createEducationCommand, createWorkExperienceCommand,
			//		createSkillCommand);
	

		//return list;
	}

	public  Workbook getWorkbook(InputStream in, String fileName)  {

		Workbook book = null;
		String filetype = fileName.substring(fileName.lastIndexOf("."));

		try {
			if (".xls".equals(filetype)) {
				book = new HSSFWorkbook(in);
			} else if (".xlsx".equals(filetype)) {
				book = new XSSFWorkbook(in);
			} else {
				throw new Exception("请上传excel文件！");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return book;
	}

	
	
	@Transactional
	public void addShanghui(Employee employee,Resumption resumption,InterViewInfo interViewInfo){
		String userId="";
		//给my_user表中存数据，    还需要生成roleId
		//User user = userRepository.byId(userId);
		MyUser myUser=new MyUser();  
		//myUser.set
		//userDao.save();
		//("insert into my_user(dept_id,user_name, password, nick_name, phone, email, status, create_time, update_time) values(#{deptId},#{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, now(), now())")
		//userDao.save(MyUser myUser);  
		//从my_user表中获取userId
		//userId=user.getUserId().toString();
		
		//给my_employee表中存数据          ***换userId ***
		if(employee!=null && (employee.getUserId()!=null||""!=employee.getUserId().toString())) {//若员工信息已存数据库
			employeeRepository.updateEmployeeByUserId(employee,employee.getUserId().toString());
		}else if(employee!=null) {
			employeeRepository.save(employee);
		}else {
			log.error("员工信息为空！");
		}
		//resumption.setUserNo(userId);
		if(resumption!=null && (resumption.getUserNo()!=null||""!=resumption.getUserNo().toString())) {//若员工信息已存数据库
			employeeRepository.updateShanghuiResumption(resumption,resumption.getUserNo().toString());
		}else if(resumption!=null) {
			employeeRepository.saveResumption(resumption);
		}else {
			log.error("员工信息为空！");
		}

		if(interViewInfo!=null && (resumption.getUserNo()!=null||""!=resumption.getUserNo().toString())) {//若员工信息已存数据库
			//employeeRepository.updateInterViewInfo(interViewInfo,resumption.getUserNo().toString());
		}else if(resumption!=null) {
			employeeRepository.saveResumption(resumption);
		}else {
			log.error("员工信息为空！");
		}
		
	}
	
//	/**
//	 * 插入用户
//	 * 
//	 * @param myUser
//	 * @return
//	 */
//	@Options(useGeneratedKeys = true, keyProperty = "userId")
//	@Insert("insert into my_user(dept_id,user_name, password, nick_name, phone, email, status, create_time, update_time) values(#{deptId},#{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, now(), now())")
//	int save(MyUser myUser);
}
