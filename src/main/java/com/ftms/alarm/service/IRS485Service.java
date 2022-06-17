package com.ftms.alarm.service;


import com.ftms.alarm.domain.RS485;

/**
 * Service接口
 *
 * @author zcl
 * @date 2022-06-1
 */
public interface IRS485Service {
    /**
     * 新增通讯记录
     *
     * @param rs485 通讯记录
     * @return 结果
     */
    int insertRS485(RS485 rs485);


}
