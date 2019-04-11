package com.lizc.sports.pc.demo.service;

import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User,String,UserRepository>
{
}
