package com.lizc.sports.pc.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizc.sports.common.controller.TreeBaseController;
import com.lizc.sports.pc.demo.entity.Office;
import com.lizc.sports.pc.demo.service.OfficeService;


@RestController
@RequestMapping("home/office")
public class OfficeController extends TreeBaseController<Office, OfficeService>
{
}
