package com.lyzd.om.shared.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lyzd.om.shared.entity.admin.MyRoleUser;

/**
 * @author Thinker
 *
 */
@Mapper
public interface RoleUserDao {
    /**
     * 通过角色id返回所有用户
     * @param id
     * @return
     */
    @Select("select * from my_role_user ru where ru.role_id = #{roleId}")
    List<MyRoleUser> listAllRoleUserByRoleId(Integer id);



    /**
     * 通过用户id查询权限id
     * @param userId
     * @return
     */
    @Select("select * from my_role_user ru where ru.user_id = #{userId}")
    List<MyRoleUser> getMyRoleUserByUserId(Integer userId);

    /**
     * 通过用户id返回角色
     * @param intValue
     * @return
     */
    @Select("select * from my_role_user ru where ru.user_id = #{userId}")
    MyRoleUser getRoleUserByUserId(int intValue);

    /**
     * 更新
     * @param myRoleUser
     * @return
     */
    int updateMyRoleUser(MyRoleUser myRoleUser);

    /**
     * 新建
     * @param myRoleUser
     * @return
     */
    @Insert("insert into my_role_user(user_id, role_id) values(#{userId}, #{roleId})")
    int save(MyRoleUser myRoleUser);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from my_role_user where user_id = #{userId}")
    int deleteRoleUserByUserId(Integer id);
}
