package com.ftms.alarm.controller;

import com.ftms.alarm.domain.RS485;
import com.ftms.alarm.service.IRS485Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/rs485")
public class RS485Controller {
    @Autowired
    private IRS485Service rs485Service;

    @PostMapping("/insert")
    @ResponseBody
    public void insertRS485() {
        RS485 rs485 = new RS485();
        rs485.setPrefix((byte) 0xAA);
        rs485.setAddress((byte) 0x01);
        rs485.setLrc((byte) 0x55);
        rs485.setCurrentDate(new Date());
        System.out.println(rs485);
        int n = rs485Service.insertRS485(rs485);
        if (n > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

}
