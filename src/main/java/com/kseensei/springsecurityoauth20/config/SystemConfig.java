package com.kseensei.springsecurityoauth20.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
public class SystemConfig {

    private Oauth2 oauth2;

    
    @Getter(value = AccessLevel.PUBLIC)
    @Setter(value = AccessLevel.PUBLIC)
    @AllArgsConstructor
    public class Oauth2{
        private String tokenGenerator;
    }
}
