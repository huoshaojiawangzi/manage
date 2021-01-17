package com.lizc.manage.config;


import com.lizc.manage.sys.entity.CommonUser;
import com.lizc.manage.sys.utils.UserUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


/**
 * @author: lizc
 * @date: 2019-04-11 10:16
 **/
@Configuration
public class UserIDAuditorConfig implements AuditorAware<CommonUser>
{

    @Override
    public Optional<CommonUser> getCurrentAuditor()
    {
        return Optional.ofNullable(UserUtils.getCurrentUser());
    }
}
