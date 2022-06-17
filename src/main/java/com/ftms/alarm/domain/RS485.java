package com.ftms.alarm.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 通讯记录
 * @author zcl
 * @date 2022-06-15
 */
@Data
public class RS485  implements Serializable {

    /** id  */
    private Integer id;

    /** 帧头 */
    private byte prefix;

    /** 地址 */
    private byte address;

    /** 校验码 */
    private byte lrc;

    /** 发生时间 */
    private Date currentDate;

}

