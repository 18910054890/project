package com.lyzd.om.shared.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lyzd.om.shared.entity.admin.MyUser;

/**
 * @author Thinker
 *
 */
@Mapper
public interface UserDao {

	/**
	 * 分页返回所有用户
	 * 
	 * @param myUser
	 * @return
	 */
	List<MyUser> getFuzzyUserByPage(MyUser myUser);

	// 计算所有用户数量
	// @Select("select count(*) from My_user")
	// Long countAllUser();

	/**
	 *
	 * 通过id返回用户
	 * 
	 * @param userId
	 * @return
	 */
	@Select("select u.user_id,u.dept_id,u.user_name,u.password,u.nick_name,u.phone,u.email,u.status,u.create_time,u.update_time,u.first_login from my_user u where u.user_id = #{userId}")
	MyUser getUserById(Integer userId);

	/**
	 * 通过手机返回用户
	 * 
	 * @param phone
	 * @return
	 */
	MyUser checkPhoneUnique(String phone);

	/**
	 * 更新用户
	 * 
	 * @param myUser
	 * @return
	 */
	int updateUser(MyUser myUser);

	/**
	 * 插入用户
	 * 
	 * @param myUser
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	@Insert("insert into my_user(dept_id,user_name, password, nick_name, phone, email, status, create_time, update_time) values(#{deptId},#{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, now(), now())")
	int save(MyUser myUser);

	/**
	 * 通过id删除用户
	 * 
	 * @param userId
	 * @return
	 */
	@Delete("delete from my_user where user_id = #{userId}")
	int deleteUserById(Integer userId);

	/**
	 * 根据用户名查询用户
	 * 
	 * @param userName
	 * @return 
	 */
//    @Select("select * from my_user t where t.user_name = #{userName}")
	@Select("select * from my_user t where t.phone = #{userName}")
	MyUser getUser(String userName);

    /**
     *  根据手机号查询用户
     * @param phoneNo
     * @return
     */
    @Select("select t.first_login from my_user t where t.phone = #{userName}")
    String getUserflag(String userName);
    
    /**
     *  根据手机号查询用户
     * @param phoneNo
     * @return
     */
    @Update("update my_user set password =#{newPwd} and set first_login = '1' WHERE user_name=#{phoneNo}")
    String updateUserByPhone(String phone,String newPwd);
}
