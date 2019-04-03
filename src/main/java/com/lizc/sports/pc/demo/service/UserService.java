package com.lizc.sports.pc.demo.service;

import com.lizc.sports.pc.demo.entity.User;
import com.lizc.sports.pc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    
    public void saveAndFlush(User user)
    {
        userRepository.saveAndFlush(user);
    }
    
    public void save(User user)
    {
        userRepository.save(user);
    }


    public User get(String id) throws Exception
    {
        if(userRepository.findById(id)!=null)
        {
            return userRepository.findById(id).get();
        }
        else
        {
            throw new Exception("该id下找不到user");
        }
    }
}
