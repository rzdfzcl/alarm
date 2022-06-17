package com.ftms.alarm.controller;

import com.ftms.alarm.domain.RS485;
import com.ftms.alarm.service.IRS485Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class RS485Controller {
    @Resource
    private IRS485Service rs485Service;

    public void test() {
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

    public static void main(String[] args) {
        RS485Controller rs485Controller = new RS485Controller();
        rs485Controller.test();
    }
}
