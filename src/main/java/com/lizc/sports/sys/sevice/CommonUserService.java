package com.lizc.sports.sys.sevice;

import com.lizc.sports.sys.entity.CommonUser;
import com.lizc.sports.sys.repository.CommonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

/**系统用户业务逻辑
 * @author: lizc@sdhuijin.cn
 * @date: 2019-03-06 14:53
 **/
public class CommonUserService {

    @Autowired
    private CommonUserRepository commonUserRepository;

    public CommonUser findByUserName(String userName)
    {
        CommonUser commonUser = new CommonUser();
        commonUser.setUsername(userName);
        Example<CommonUser> example = Example.of(commonUser);
        return commonUserRepository.findOne(example).get();
    }

    /**
     * 逻辑删除
     * @param commonUser
     */
    public void delete(CommonUser commonUser)
    {
        commonUser.setStatus(CommonUser.DISABLED);
    }

    /**
     * 通过用户名进行逻辑删除
     * @param userName
     */
    public void deleteByUserName(String userName)
    {
        delete(findByUserName(userName));
    }
}
