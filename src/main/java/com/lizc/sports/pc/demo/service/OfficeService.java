package com.lizc.sports.pc.demo.service;


import com.lizc.sports.common.service.TreeBaseService;
import com.lizc.sports.pc.demo.entity.Office;
import com.lizc.sports.pc.demo.repository.OfficeRepository;
import org.springframework.stereotype.Service;


@Service
public class OfficeService extends TreeBaseService<Office, String, OfficeRepository>
{
}
