package com.lyzd.om.shared.service.security;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.lyzd.om.shared.dao.admin.MenuDao;
import com.lyzd.om.shared.dto.admin.MenuIndexDto;
import com.lyzd.om.shared.entity.admin.MyDept;
import com.lyzd.om.shared.entity.admin.MyRole;
import com.lyzd.om.shared.entity.admin.MyRoleUser;
import com.lyzd.om.shared.entity.admin.MyUser;
import com.lyzd.om.shared.service.admin.DeptService;
import com.lyzd.om.shared.service.admin.RoleService;
import com.lyzd.om.shared.service.admin.RoleUserService;
import com.lyzd.om.shared.service.admin.UserService;
import com.lyzd.om.spring.common.dto.JwtUserDto;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Thinker
 *
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuDao menuDao;
    
    @Autowired
    private DeptService  deptService;

    @Override
//    @ResponseBody
    public JwtUserDto loadUserByUsername(String userName){
        //根据用户名获取用户
        MyUser user = userService.getUserByName(userName);
        if (user == null ){
            throw new BadCredentialsException("用户名或密码错误");
        }else if (user.getStatus().equals(MyUser.Status.LOCKED)) {
            throw new LockedException("用户被锁定,请联系管理员解锁");
        }
        String firstLogin = userService.getUserByPhone(userName);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<MenuIndexDto> list = menuDao.listByUserId(user.getUserId());
        List<String> collect = list.stream().map(MenuIndexDto::getPermission).collect(Collectors.toList());
        for (String authority : collect){
            if (!("").equals(authority) & authority !=null){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
                grantedAuthorities.add(grantedAuthority);
            }
        }
        
        MyDept myDept = deptService.getDeptById(user.getDeptId());
        user.setDeptName(myDept.getDeptName());
        
        //将用户所拥有的权限加入GrantedAuthority集合中
        JwtUserDto loginUser =new JwtUserDto(user,grantedAuthorities);
        loginUser.setRoleInfo(getRoleInfo(user));
        return loginUser;
    }


    public List<MyRole> getRoleInfo(MyUser myUser) {
        MyUser userByName = userService.getUserByName(myUser.getPhone());
        List<MyRoleUser> roleUserByUserId = roleUserService.getMyRoleUserByUserId(userByName.getUserId());
        List <MyRole> roleList = new ArrayList<>();
        for (MyRoleUser roleUser:roleUserByUserId){
            Integer roleId = roleUser.getRoleId();
            MyRole roleById = roleService.getRoleById(roleId);
            roleList.add(roleById);
        }
        return roleList;
    }

}