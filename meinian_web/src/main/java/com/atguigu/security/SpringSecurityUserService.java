package com.atguigu.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.pojo.Permission;
import com.atguigu.pojo.Role;
import com.atguigu.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息,以及用户对应的角色，角色对应的权限
        com.atguigu.pojo.User user = userService.findUserByUsername(username);
        if(user == null){//不存在用户
            return null;//返回null给框架，框架会抛异常，跳转到登录页面
        }

        //构建权限集合
        Set<GrantedAuthority> authorities = new HashSet<>();

        Set<Role> roles = user.getRoles();//集合数据由RoleDao帮忙的方法来查询得到的
        for(Role role:roles){
            Set<Permission> permissions = role.getPermissions();//由PermissionDao帮忙方法查到的
            for(Permission permission:permissions){
                authorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        User securityUser = new User(user.getUsername(), user.getPassword(), authorities);
        return securityUser;
    }
}
