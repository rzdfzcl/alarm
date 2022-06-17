package com.ftms.alarm.mapper;

import com.ftms.alarm.domain.RS485;
import org.springframework.stereotype.Repository;

/**
 * Mapper接口
 *
 * @author zcl
 * @date 2022-06-15
 */
@Repository
public interface RS485Mapper
{


    /**
     * 新增通讯记录
     *
     * @param rs485 通讯记录
     * @return 结果
     */
    int insertRS485(RS485 rs485);


}
