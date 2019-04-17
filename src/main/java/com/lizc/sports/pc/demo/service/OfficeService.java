package com.lizc.sports.pc.demo.service;


import com.lizc.sports.common.service.BaseService;
import com.lizc.sports.pc.demo.entity.Office;
import com.lizc.sports.pc.demo.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfficeService extends BaseService<Office, String, OfficeRepository>
{
    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> findRoots()
    {
        return officeRepository.findRoots();
    }
}
