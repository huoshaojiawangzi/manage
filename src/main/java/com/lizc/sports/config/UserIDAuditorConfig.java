package com.lizc.sports.config;

import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.sevice.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author: lizc@sdhuijin.cn
 * @date: 2019-04-11 10:16
 **/
@Configuration
public class UserIDAuditorConfig implements AuditorAware<CommonUser> {

    @Autowired
    private CommonUserService userService;

    @Override
    public Optional<CommonUser> getCurrentAuditor() {
        CommonUser commonUser = userService.get("1");
        return Optional.of(commonUser);
    }
}
