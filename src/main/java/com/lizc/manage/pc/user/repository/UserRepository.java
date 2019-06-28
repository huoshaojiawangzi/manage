package com.lizc.manage.pc.user.repository;


import com.lizc.manage.common.repository.BaseRepository;
import com.lizc.manage.pc.user.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends BaseRepository<User, String>
{

}
