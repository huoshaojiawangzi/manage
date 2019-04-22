package com.lizc.sports.pc.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizc.sports.common.service.TreeBaseService;
import com.lizc.sports.pc.demo.entity.Office;
import com.lizc.sports.pc.demo.repository.OfficeRepository;


@Service
public class OfficeService extends TreeBaseService<Office, String, OfficeRepository>
{
    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository)
    {
        this.officeRepository = officeRepository;
    }

    @Override
    public List<Office> findRoots()
    {
        return officeRepository.findRoots();
    }
}
