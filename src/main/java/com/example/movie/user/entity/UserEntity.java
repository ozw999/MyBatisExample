package com.example.movie.user.entity;

import com.example.movie.login.role.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/3/31
 */
@Data
public class UserEntity implements UserDetails {
    private Integer userId;
    private String userName;
    private String password;
    private Boolean enabled;
    private Boolean deleted;

    private List<RoleEntity> roles;

    /**
     * 返回用户的权限集合
     *
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 账号是否失效，返回false账号失效，不可用
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否被锁，返回false，账号被锁，不可用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号认证是否过期，返回false，过期，不可用
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否启用。1启用，0禁用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
