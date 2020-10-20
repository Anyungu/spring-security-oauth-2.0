package com.kseensei.springsecurityoauth20.requests;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kseensei.springsecurityoauth20.entities.Permission;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@ToString
public class UserRequest implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    private String username;

    private String password;

    @JsonProperty("account_non_expired")
    private Boolean accountNonExpired;

    @JsonProperty("account_non_locked")
    private Boolean accountNonLocked;

    @JsonProperty("credentials_non_expired")
    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private Collection<Permission> authorities;
}
