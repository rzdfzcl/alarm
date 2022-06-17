package com.ftms;

import static org.junit.Assert.assertTrue;

import com.ftms.alarm.domain.RS485;
import com.ftms.alarm.service.IRS485Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Resource
    private IRS485Service rs485Service;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
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
}
