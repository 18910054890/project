package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.SaveAttachmentCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.ContractInfo;
import com.lyzd.om.emp.info.model.Education;
import com.lyzd.om.emp.info.model.Employee;
import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.emp.info.model.MyEmployee;
import com.lyzd.om.emp.info.model.Resumption;
import com.lyzd.om.emp.info.model.Skill;
import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.emp.info.model.eductionNewType;
import com.lyzd.om.emp.info.sdk.commond.CreateEmployeeCommand;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;
@Slf4j
@Component
public class EmployeeRepository extends BaseRepository<Employee> {

	private static final String SELECT_SQL0 = "SELECT ID, NAME FROM USERS WHERE user_name = :userName;";
	private static final String SELECT_SQL1 = "SELECT ID, NAME FROM USERS LIMIT :limit OFFSET :offset;";
	private static final String SELECT_SQL2 = "SELECT ID, NAME FROM USERS LIMIT :limit OFFSET :offset;";
	private static final String SELECT_SQL3 = "SELECT ID, NAME FROM USERS LIMIT :limit OFFSET :offset;";
	private static final String SELECT_SQL4 = "SELECT ID, NAME FROM USERS LIMIT :limit OFFSET :offset;";
//    private static final String COUNT_SQL = "SELECT COUNT(1) FROM USERS;";

	private NamedParameterJdbcTemplate jdbcTemplate;

	private DefaultObjectMapper objectMapper;

	public void saveEmployee(Employee employee) {
		String sql = "INSERT INTO my_employee (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, Object> paramMap = of("id", employee.getId(), "json",
				objectMapper.writeValueAsString(employee));
		jdbcTemplate.update(sql, paramMap);
	}
	
	@Autowired
	public EmployeeRepository(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doSave(Employee employee) {
		String sql = "INSERT INTO my_employee (ID,USERNAME,JSON_CONTENT) VALUES (:id,:username,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, Object> paramMap = of("id", employee.getId(), "username", employee.getName(), "json",
				objectMapper.writeValueAsString(employee));
		jdbcTemplate.update(sql, paramMap);
	}

//	public Education saveEducation1(String userNo, String schoolName, String major, String maxEducation, String startTime,
//			String endTime) {
//		String sql = "INSERT INTO my_education (ID,USERNAME,JSON_CONTENT) VALUES (:id,:username,:json) "
//				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
//		return jdbcTemplate.queryForObject(sql, of("id", userNo), educationMapper());
//	}

	public Education saveEducation(Education education) {
		String sql = "INSERT INTO my_education (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", education.getId(), "json", objectMapper.writeValueAsString(education));
		jdbcTemplate.update(sql, paramMap);
		return null;
	}

	

	
	/**
	 * @param id
	 * @param flag
	 * @return 员工模糊查询，flag值为0查询全部，1为姓名查询，2为部门查询，3为专长查询，4为业务查询
	 */
	public List<MyEmployee> byFlag(String id, String flag) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int pageSize = 0;
		int pageIndex = 10000;
		parameters.addValue("limit", pageSize);
		parameters.addValue("offset", (pageIndex - 1) * pageSize);
		List<MyEmployee> myEmployeeList = null;
		if ("0".equals(flag)) {
			myEmployeeList = jdbcTemplate.query(SELECT_SQL0, parameters,
					(rs, rowNum) -> new MyEmployee(rs.getString("userName"), rs.getString("deptName"),
							rs.getString("skill"), rs.getString("userNo")));
		} else if ("1".equals(flag)) {
			myEmployeeList = jdbcTemplate.query(SELECT_SQL1, parameters,
					(rs, rowNum) -> new MyEmployee(rs.getString("userName"), rs.getString("deptName"),
							rs.getString("skill"), rs.getString("userNo")));
		} else if ("2".equals(flag)) {
			myEmployeeList = jdbcTemplate.query(SELECT_SQL2, parameters,
					(rs, rowNum) -> new MyEmployee(rs.getString("userName"), rs.getString("deptName"),
							rs.getString("skill"), rs.getString("userNo")));
		} else if ("3".equals(flag)) {
			myEmployeeList = jdbcTemplate.query(SELECT_SQL3, parameters,
					(rs, rowNum) -> new MyEmployee(rs.getString("userName"), rs.getString("deptName"),
							rs.getString("skill"), rs.getString("userNo")));
		} else if ("4".equals(flag)) {
			myEmployeeList = jdbcTemplate.query(SELECT_SQL4, parameters,
					(rs, rowNum) -> new MyEmployee(rs.getString("userName"), rs.getString("deptName"),
							rs.getString("skill"), rs.getString("userNo")));
		}
		return myEmployeeList;
	}

	/**
	 * @param userNo
	 * @return
	 */
	public Employee byUserNo(String userNo) {
		String sql = "SELECT JSON_CONTENT FROM USERS WHERE userNo=:userNo;";
		return jdbcTemplate.queryForObject(sql, of("userNo", userNo), employeeMapper());
	}

	private RowMapper<Employee> employeeMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Employee.class);
	}

//	private RowMapper<Education> educationMapper() {
//		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Education.class);
//	}

	public boolean updatePwd(String newPwd, String phoneNo) {
//		newPwd =Md5.crypt(newPwd);
		String sql = "update my_user set password =:newPwd and set first_login = '1' WHERE phone=:phoneNo;";
		Map<String, String> paramMap = of("newPwd", newPwd, "phoneNo", phoneNo);
		jdbcTemplate.update(sql, paramMap);
		return true;
	}

	public List<Education> employeeEducationById(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int pageSize = 0;
		int pageIndex = 10000;
		parameters.addValue("userId", userId);
		parameters.addValue("limit", 5);
		parameters.addValue("offset", 0);
		List<Education> educationList = null;
		String sql = "SELECT JSON_CONTENT FROM my_education WHERE userId=:userId LIMIT :limit OFFSET :offset;";
		educationList = jdbcTemplate.query(sql, parameters,
				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Education.class));
		return educationList;

	}

	private RowMapper<Education> educationMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Education.class);
	}

	public  Employee employeeBaseinfoById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_employee WHERE userId=:userId;";
		Employee  employee=jdbcTemplate.queryForObject(sql, of("userId", userId), employeeMapper());
		
		return employee;
	}

	

	public List<WorkExperience> employeeWorkExperienceById(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int pageSize = 0;
		int pageIndex = 10000;
		parameters.addValue("userId", userId);
		parameters.addValue("limit", 5);
		parameters.addValue("offset", 0);
		List<WorkExperience> workExperienceList = null;
		String sql = "SELECT JSON_CONTENT FROM my_workexperience WHERE userId=:userId LIMIT :limit OFFSET :offset;";
		workExperienceList = jdbcTemplate.query(sql, parameters,
				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), WorkExperience.class));
		return workExperienceList;

	}


	public WorkExperience workExperienceById(String id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		String sql = "SELECT JSON_CONTENT FROM my_workexperience WHERE id=:id;";
		return jdbcTemplate.queryForObject(sql, of("id", id), workExperienceMapper());
		

	}
	private RowMapper<WorkExperience> workExperienceMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), WorkExperience.class);
	}
	
	public List<EmployeeProject> employeeProjectById(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int pageSize = 0;
		int pageIndex = 10000;
		parameters.addValue("userId", userId);
		parameters.addValue("limit", 5);
		parameters.addValue("offset", 0);
		List<EmployeeProject> projectList = null;
		String sql = "SELECT JSON_CONTENT FROM my_project WHERE userId=:userId LIMIT :limit OFFSET :offset;";
		projectList = jdbcTemplate.query(sql, parameters,
				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeProject.class));
		return projectList;

	}

	public List<EmployeeChildren> employeeChildrenById(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int pageSize = 0;
		int pageIndex = 10000;
		parameters.addValue("userId", userId);
		parameters.addValue("limit", 5);
		parameters.addValue("offset", 0);
		List<EmployeeChildren> childrenList = null;
		String sql = "SELECT JSON_CONTENT FROM my_children WHERE userId=:userId LIMIT :limit OFFSET :offset;";
		childrenList = jdbcTemplate.query(sql, parameters,
				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeChildren.class));
		return childrenList;

	}

	public List<LyWorkExperience> employeeLyWorkExperienceById(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int pageSize = 1;
		int pageIndex = 10000;
		parameters.addValue("userId", userId);
		parameters.addValue("limit", 5);
		parameters.addValue("offset", 0);
		List<LyWorkExperience> lyworkExperienceList = null;
		String sql = "SELECT JSON_CONTENT FROM my_lyworkexperience WHERE userId=:userId LIMIT :limit OFFSET :offset;";
		lyworkExperienceList = jdbcTemplate.query(sql, parameters,
				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), LyWorkExperience.class));
		return lyworkExperienceList;

	}

	public List<LyWorkExperience> employeeLyWorkExperienceByUserId(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		List<LyWorkExperience> lyworkExperienceList = null;
		String sql = "SELECT JSON_CONTENT FROM my_lyworkexperience WHERE userId=:userId;";
		lyworkExperienceList = jdbcTemplate.query(sql, parameters,
				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), LyWorkExperience.class));
		return lyworkExperienceList;

	}
	
	
	
	
	public EmployeeProject queryEmployeeProjectById(String  id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		String sql = "SELECT JSON_CONTENT FROM my_project WHERE id=:id;";
		return  jdbcTemplate.queryForObject(sql, of("id", id), employeeProjectMapper());

	}
	
	private RowMapper<EmployeeProject> employeeProjectMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeProject.class);
	}
	
	
	public ContractInfo employeeContractById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_contractinfo WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), contractInfoMapper());
	}

	private RowMapper<ContractInfo> contractInfoMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), ContractInfo.class);
	}

	
	public eductionNewType employeeMaxEduById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_employee WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), eductionNewTypeMapper());
	}

	private RowMapper<eductionNewType> eductionNewTypeMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), eductionNewType.class);
	}

	/**
	 * 个人信息页面保存基本信息
	 * @param createEmployeeCommand
	 * @param userId
	 * @return
	 */
	
	public boolean updateUserStatus(String userId, String isPass) {
		String sql = "UPDATE my_employee set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.emStatus',:isPass) where userId =:userId;";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("isPass", isPass);
		parameters.addValue("userId", userId);
		jdbcTemplate.update(sql, parameters);
		return true;
	}

	public boolean updateEmployeeByUserId(Employee employee, String userId) {
		String sql = "UPDATE my_employee set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.name',:name,'$.idcard',:idcard) where userId =:userId;";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		parameters.addValue("name", employee.getName());
		parameters.addValue("idcard", employee.getIdcard());
		jdbcTemplate.update(sql, parameters);
		return true;
	}

	
	

	/**
	 * lj编辑个人信息页面，保存子女信息
	 * @param createEmployeeCommand
	 * @param userId
	 * @return
	 */
	public boolean updateChildren(CreateEmployeeCommand createEmployeeCommand, String userId) {
		String sql = "UPDATE my_children set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.childName',:childName,'$.childSex',:childSex,'$.childBirthdate',:childBirthdate,'$.childIdCard',:childIdCard,'$.applyCardNo',:applyCardNo) where userId =:userId;";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		EmployeeChildren employeeChildren=createEmployeeCommand.getEmployeeChildren();
		if(employeeChildren!=null) {
			
			parameters.addValue("childName", employeeChildren.getChildName());
			parameters.addValue("childSex", employeeChildren.getChildSex());
			parameters.addValue("childBirthdate", employeeChildren.getChildBirthdate());
			parameters.addValue("childIdCard", employeeChildren.getChildIdCard());
			parameters.addValue("applyCardNo", employeeChildren.getApplyCardNo());
	        
			parameters.addValue("userId", userId);
			jdbcTemplate.update(sql, parameters);
			log.info("子女信息更新成功！");
		}
		
		return true;
	}
	
	
	
	public void checkChild() {
		
	}
	
	
	
	
	
	/**
	 * 保存个人信息页面的内部工作经验
	 * @param createEmployeeCommand
	 * @param userId
	 * @return
	 */
//	public boolean updateLyWorkExperience(CreateEmployeeCommand createEmployeeCommand, String userId) {
//		
//		String sql = "UPDATE my_lyworkexperience set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.startTime',:startTime,'$.endTime',:endTime,'$.departName',:departName,'$.offices',:offices,'$.position',:position,'$.positions',:positions) where id =:id;";
//		List<LyWorkExperience> lyworkExperienceList = createEmployeeCommand.getLyworkExperienceList();
//		if(lyworkExperienceList!=null) {
//			for(int i=0;i<lyworkExperienceList.size();i++) {
//				LyWorkExperience lyWorkExperience=lyworkExperienceList.get(i);
//			if(lyWorkExperience.getId()==null||"".equals(lyWorkExperience.getId())) {
//				saveLyWorkExperience(lyWorkExperience);
//			}else {
//				MapSqlParameterSource parameters = new MapSqlParameterSource();
//				parameters.addValue("startTime", lyWorkExperience.getStartTime());
//				parameters.addValue("endTime", lyWorkExperience.getEndTime());
//				parameters.addValue("departName",lyWorkExperience.getDepartName());
//				parameters.addValue("offices", lyWorkExperience.getOffices());
//				parameters.addValue("position", lyWorkExperience.getPosition());
//				parameters.addValue("positions",lyWorkExperience.getPositions());
//		
//				parameters.addValue("id", lyWorkExperience.getId());
//				jdbcTemplate.update(sql, parameters);
//				log.info("内部工作信息更新成功！");
//			}
//		}
//	  }
//		return true;
//	}
	
	/**
	 * 保存个人信息页面的工作经验
	 * @param command
	 * @param userId
	 * @return
	 */
//	public boolean updateWorkExperience(CreateEmployeeCommand command, String userId) {
//		String sql = "UPDATE my_workexperience set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.startTime',:startTime,'$.endTime',:endTime,'$.company',:company,'$.companyType',:companyType,'$.position',:position,'$.description',:description) where id =:id;";
//		List<WorkExperience> workExperienceList = command.getWorkExperienceList();
//		if(workExperienceList!=null) {
//			for(int i=0;i<workExperienceList.size();i++) {
//				WorkExperience workExperience=workExperienceList.get(i);
//				if(workExperience.getId()==null||"".equals(workExperience.getId())) {
//					saveNewWorkExperience(workExperience);
//				}else {
//					MapSqlParameterSource parameters = new MapSqlParameterSource();
//					parameters.addValue("startTime", workExperience.getStartTime());
//					parameters.addValue("endTime", workExperience.getEndTime());
//					parameters.addValue("company", workExperience.getCompany());
//					parameters.addValue("companyType", workExperience.getCompanyType());
//					parameters.addValue("position", workExperience.getPosition());
//					parameters.addValue("description",workExperience.getDescription());
//			
//					parameters.addValue("id", workExperience.getId());
//					jdbcTemplate.update(sql, parameters);
//					log.info("外部工作经验更新成功！");
//				}
//			}
//		}
//		return true;
//	}
	
		
	/**
	* 保存个人信息页的项目经验
	* @param command
	* @param userId
	* @return
	*/
//	public boolean updateProject(CreateEmployeeCommand command, String userId) {
//		String sql = "UPDATE my_project set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.startTime',:startTime,'$.endTime',:endTime,'$.projectName',:projectName,'$.roleName',:roleName,'$.description',:description) where id =:id;";
//		List<EmployeeProject> projectList = command.getProjectList();
//		if(projectList!=null) {
//			for(int i=0;i<projectList.size();i++) {
//				EmployeeProject employeeProject=projectList.get(i);
//				if(employeeProject.getId()==null||"".equals(employeeProject.getId())) {
//					saveEmployeeProject(employeeProject);
//				}else {
//					MapSqlParameterSource parameters = new MapSqlParameterSource();
//					parameters.addValue("startTime", employeeProject.getStartTime());
//					parameters.addValue("endTime", employeeProject.getEndTime());
//					parameters.addValue("projectName", employeeProject.getProjectName());
//					parameters.addValue("roleName", employeeProject.getRoleName());
//					parameters.addValue("description", employeeProject.getDescription());
//			
//					parameters.addValue("id", employeeProject.getId());
//					jdbcTemplate.update(sql, parameters);
//					log.info("项目经验更新成功！");
//				}
//			}
//		}
//		return true;
//	}

	
		
	
	
//	/**
//	 * 新增内部项目经历保存
//	 * @param employeeProject
//	 */
//	public void saveNewInnerEmployeeProject(EmployeeProject employeeProject) {
//		String sql = "INSERT INTO my_InnerProject (ID,JSON_CONTENT) VALUES (:id,:json) "
//					+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
//		Map<String, String> paramMap = of("id",newUuid(), "json",
//					objectMapper.writeValueAsString(employeeProject));
//		jdbcTemplate.update(sql, paramMap);
//		log.info("新增内部项目经历保存成功！");
//	}
//	
//	
	
	
	
	

	

	
	/**
	 * lj导入上会审批表保存履历信息
	 * @param resumption
	 * @param userId
	 */
	public boolean updateShanghuiResumption(Resumption resumption, String userId) {
		String sql = "UPDATE my_resumption set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,"
				+ "'$.officeDate',:officeDate,'$.sendOfferTime',:sendOfferTime,"
				+ "'$.comInsideLevel',:comInsideLevel,'$.deptName',:deptName,"
				+ "'$.comInsidepost',:comInsidepost,'$.csName',:csName,"
				+ "'$.csProject',:csProject,'$.hxLevl',:hxLevl,'$.territory',:territory,"
				+ "'$.headman',:headman,'$.hr',:hr) where userId =:userId;";
		
		String sql2 = "UPDATE my_resumption set JSON_CONTENT=:json where userId=:userId;";
		
		Map<String, Object> paramMap2 = of ("json",
				objectMapper.writeValueAsString(resumption),"userId", userId);
		
		
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("officeDate", resumption.getCardNo());
		parameters.addValue("sendOfferTime", resumption.getCardNo());
		parameters.addValue("comInsideLevel", resumption.getCardNo());
		parameters.addValue("deptName", resumption.getCardNo());
		parameters.addValue("comInsidepost", resumption.getCardNo());
		parameters.addValue("csName", resumption.getCardNo());
		parameters.addValue("csProject", resumption.getCardNo());
		parameters.addValue("hxLevl", resumption.getCardFlag());
		parameters.addValue("territory", resumption.getFirstSocialSecurity());
		parameters.addValue("headman", resumption.getUserStatus());
		parameters.addValue("hr", resumption.getDistrict());
		parameters.addValue("userId", userId);
		//jdbcTemplate.update(sql, parameters);
		
		jdbcTemplate.update(sql2, paramMap2);
		return true;
	}
	
	public void updateResumption(CreateEmployeeCommand createEmployeeCommand, String userId) {
		String sql = "UPDATE my_resumption set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.cardNo',:cardNo,'$.cardFlag',:cardFlag,'$.firstSocialSecurity',:firstSocialSecurity,'$.userStatus',:userStatus,'$.district',:district,'$.contractStatus',:contractStatus,'$.openbankname',:openbankname) where userId =:userId;";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cardNo", createEmployeeCommand.getCardNo());
		parameters.addValue("cardFlag", createEmployeeCommand.getCardFlag());
		parameters.addValue("firstSocialSecurity", createEmployeeCommand.getFirstSocialSecurity());
		parameters.addValue("userStatus", createEmployeeCommand.getUserStatus());
		parameters.addValue("district", createEmployeeCommand.getDistrict());
		parameters.addValue("contractStatus", createEmployeeCommand.getContractStatus());
		parameters.addValue("openbankname", createEmployeeCommand.getOpenbankname());
		parameters.addValue("userId", userId);
		jdbcTemplate.update(sql, parameters);
	
	}

	
	
	
	
//	public boolean saveResumptions(CreateEmployeeCommand createEmployeeCommand, String userId) {
//		String sql = "UPDATE my_resumption set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,"
//				+ "'$.firstSocialSecurity',:firstSocialSecurity,'$.district',:district,'$.contractStatus',:contractStatus,"
//				+ "'$.userStatus',:userStatus,'$.cardFlag',:cardFlag,'$.cardNo',:cardNo,"
//				+ "'$.openbankname',:openbankname,'$.graduateDate',:graduateDate,'$.officialWorkDate',:officialWorkDate) where userId =:userId;";
//		if(createEmployeeCommand!=null) {
//			MapSqlParameterSource parameters = new MapSqlParameterSource();
//			parameters.addValue("firstSocialSecurity", createEmployeeCommand.getFirstSocialSecurity());
//			parameters.addValue("district", createEmployeeCommand.getDistrict());
//			parameters.addValue("contractStatus", createEmployeeCommand.getContractStatus());
//			parameters.addValue("userStatus", createEmployeeCommand.getUserStatus());
//			parameters.addValue("cardFlag",createEmployeeCommand.getCardFlag());
//			parameters.addValue("cardNo", createEmployeeCommand.getCardNo());
//			parameters.addValue("openbankname", createEmployeeCommand.getOpenbankname());
//			parameters.addValue("graduateDate", createEmployeeCommand.getGraduateDate());
//			parameters.addValue("officialWorkDate", createEmployeeCommand.getOfficialWorkDate());
//			parameters.addValue("userId", userId);
//			jdbcTemplate.update(sql, parameters);
//			log.info("履职信息更新成功！");
//		}
		
		//resumption.setFirstSocialSecurity(firstSocialSecurity);
//		return true;
	//}

	
	
	
	
	//public boolean saveEmployeeInfo(CreateEmployeeCommand createEmployeeCommand, String userId) {
		//String sql = "UPDATE my_employee set JSON_CONTENT = JSON_REPLACE(JSON_CONTENT,'$.bearStatus',:bearStatus,'$.birthDate',:birthDate,'$.domicile',:domicile,'$.domicileAddr',:domicileAddr,'$.familyPhone',:familyPhone,'$.registeredType',:registeredType,'$.familyLive',:familyLive,'$.postalCode',:postalCode,
//		'$.nation',:nation,'$.sOSPersonName',:sOSPersonName,'$.sosPersonRelation',
//		:sosPersonRelation,'$.sOSPersonPhone',:sOSPersonPhone,'$.emStatus',:emStatus) 
//where userId =:userId;";
		//MapSqlParameterSource parameters = new MapSqlParameterSource();

//		parameters.addValue("bearStatus",createEmployeeCommand.getBearStatus());
//		parameters.addValue("birthDate", createEmployeeCommand.getBirthDate());
//		parameters.addValue("domicile", createEmployeeCommand.getDomicile());
//		parameters.addValue("domicileAddr", createEmployeeCommand.getDomicileAddr());
//		parameters.addValue("familyPhone", createEmployeeCommand.getFamilyPhone());
//		parameters.addValue("registeredType", createEmployeeCommand.getRegisteredType());
//		parameters.addValue("familyLive", createEmployeeCommand.getFamilyLive());
//		parameters.addValue("postalCode", createEmployeeCommand.getPostalCode());
//		parameters.addValue("nation", createEmployeeCommand.getNation());
//		parameters.addValue("sOSPersonName", createEmployeeCommand.getSOSPersonName());
//		parameters.addValue("sosPersonRelation", createEmployeeCommand.getSosPersonRelation());
//		parameters.addValue("sOSPersonPhone", createEmployeeCommand.getSOSPersonPhone());
//		parameters.addValue("emStatus", emStatus);
//		parameters.addValue("userId", userId);
//		parameters.addValue("educationType  ", createEmployeeCommand.getEducationType());
//		jdbcTemplate.update(sql, parameters);
	//	log.info("个人信息页面成功保存用户基本信息！");
	//	return true;
	//}
	

	
	public EmployeeChildren queryEmployeeChildrenById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_children WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), employeeChildrenMapper());
	}

	private RowMapper<EmployeeChildren> employeeChildrenMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeChildren.class);
	}
	
	public void saveEmployeeChildree(EmployeeChildren employeeChildren) {
		String sql = "INSERT INTO my_children (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", employeeChildren.getId(), "json", objectMapper.writeValueAsString(employeeChildren));
		jdbcTemplate.update(sql, paramMap);
	
	}
	
	public void saveSkill(Skill skill) {
		String sql = "INSERT INTO my_skill (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", skill.getId(), "json", objectMapper.writeValueAsString(skill));
		jdbcTemplate.update(sql, paramMap);
	}
	
	public Skill employeeSkillById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_skill WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), skillMapper());
	}

	private RowMapper<Skill> skillMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Skill.class);
	}

	/**
	 * 保存新增的外部项目经历
	 * @param workExperience
	 */
	public void saveEmployeeProject(EmployeeProject employeeProject) {
		String sql = "INSERT INTO my_project (ID,JSON_CONTENT) VALUES (:id,:json) "
					+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id",newUuid(), "json",
					objectMapper.writeValueAsString(employeeProject));
		jdbcTemplate.update(sql, paramMap);
		log.info("新增外部项目经验保存成功！");
	}
	
	public void saveNewEmployeeProject(EmployeeProject employeeProject) {
		String sql = "INSERT INTO my_project (ID,JSON_CONTENT) VALUES (:id,:json) "
					+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id",employeeProject.getId(), "json",
					objectMapper.writeValueAsString(employeeProject));
		jdbcTemplate.update(sql, paramMap);
		log.info("项目经验保存成功！");
	}
	
	
	/**
	 * 保存新增的外部工作经历
	 * @param workExperience
	 */
	public void saveNewWorkExperience(WorkExperience workExperience) {
		String sql = "INSERT INTO my_workexperience (ID,JSON_CONTENT) VALUES (:id,:json) "
					+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id",newUuid(), "json",
					objectMapper.writeValueAsString(workExperience));
		jdbcTemplate.update(sql, paramMap);
		log.info("新增外部工作经验保存成功！");
	}
	
	public void saveWorkExperience(WorkExperience workExperience) {
		String sql = "INSERT INTO my_workexperience (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", workExperience.getId(), "json",
				objectMapper.writeValueAsString(workExperience));
		jdbcTemplate.update(sql, paramMap);
		
	}
	
	/**
	 * 保存新增的内部工作经验
	 * @param lyWorkExperience
	 */
	public void saveNewLyWorkExperience(LyWorkExperience lyWorkExperience){
		String sql = "INSERT INTO my_lyworkexperience (ID,JSON_CONTENT) VALUES (:id,:json)"+   
			         "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, Object> paramMap = of("id", newUuid(), "json",
				objectMapper.writeValueAsString(lyWorkExperience));
		jdbcTemplate.update(sql, paramMap);
		log.info("新增内部工作经验保存成功！");
	}
	
	
	
	public LyWorkExperience lyWorkExperienceById(String id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		String sql = "SELECT JSON_CONTENT FROM my_lyworkexperience WHERE id=:id;";
		return jdbcTemplate.queryForObject(sql, of("id", id), lyExperienceMapper());
	}
	
	private RowMapper<LyWorkExperience> lyExperienceMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), LyWorkExperience.class);
	}
	public Resumption employeeSumptionById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_resumption WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), resumptionMapper());
	}

	private RowMapper<Resumption> resumptionMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Resumption.class);
	}
	
	
	public void saveResumption(Resumption resumption) {
		String sql = "INSERT INTO my_resumption (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, Object> paramMap = of("id", resumption.getId(), "json",
				objectMapper.writeValueAsString(resumption));
		jdbcTemplate.update(sql, paramMap);
	}

	
	
}
