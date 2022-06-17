package com.ftms.alarm.service.impl;

import com.ftms.alarm.domain.RS485;
import com.ftms.alarm.mapper.RS485Mapper;
import com.ftms.alarm.service.IRS485Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RS485ServiceImpl implements IRS485Service {

    @Resource
    private RS485Mapper rs485Mapper;

    @Override
    public int insertRS485(RS485 rs485) {
        return rs485Mapper.insertRS485(rs485);
    }
}
