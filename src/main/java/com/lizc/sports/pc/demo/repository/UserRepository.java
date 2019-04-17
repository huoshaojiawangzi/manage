package com.lizc.sports.pc.demo.repository;


import com.lizc.sports.common.repository.BaseRepository;
import com.lizc.sports.pc.demo.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends BaseRepository<User, String>
{

}
