package com.lyzd.om.spring.common.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyzd.om.shared.entity.admin.MyRole;
import com.lyzd.om.shared.entity.admin.MyUser;

import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
public class JwtUserDto implements UserDetails {

    /**
     * 用户数据
     */
    private MyUser myUser;

    private List<MyRole> roleInfo;
    
    
    public List<MyRole> getRoleInfo() {
		return roleInfo;
	}


	public void setRoleInfo(List<MyRole> roleInfo) {
		this.roleInfo = roleInfo;
	}




	/**
     * 用户权限的集合
     */
    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public List<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }


    /**
     * 加密后的密码
     * @return
     */
    @Override
    public String getPassword() {
        return myUser.getPassword();
    }


    /**
     * 用户名
     * @return
     */
    @Override
    public String getUsername() {
        return myUser.getUserName();
    }


    /**
     * 是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * 凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return myUser.getStatus() == 1 ? true : false;
    }




    public JwtUserDto(MyUser myUser, List<GrantedAuthority> authorities) {
        this.myUser = myUser;
        this.authorities = authorities;
    }
}