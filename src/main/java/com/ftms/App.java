package com.ftms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;

import com.ftms.alarm.utils.MyUtils;
import gnu.io.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class App {

    // 从串口来的输入流
    static InputStream inputStream;

    // 向串口输出的流
    static OutputStream outputStream;

    //定义需要接收数据的端口(未打开)
    static CommPortIdentifier com4;

    // 串口的引用
    static SerialPort serialCom4;

    //记录已经到达串口COM4且未被读取的数据的字节（Byte）数
    static int availableBytes = 0;

    /**
     * 通过轮询模式监听串口COM4
     * 当串口COM4传来数据的时候读取数据
     */
    public static void main(String[] args) throws NoSuchPortException, PortInUseException {

        //获取并打开串口com4
        com4 = CommPortIdentifier.getPortIdentifier("COM4");
        serialCom4 = (SerialPort) com4.open("Com4Listener", 1000);

        System.out.println("开始监听端口COM4收到的数据");
        Thread writeThread = new WriteThread();
        Thread listenThread = new  ListenThread();
        writeThread.start();
        listenThread.start();

    }
    static class  WriteThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    byte[][] b = new byte[32][3];
                    b[0] = new byte[]{(byte) 0xFA, (byte) 0x00, (byte) 0xFA};
                    b[1] = new byte[]{(byte) 0xFA, (byte) 0x02, (byte) 0xF8};
                    b[2] = new byte[]{(byte) 0xFA, (byte) 0x04, (byte) 0xFE};
                    b[3] = new byte[]{(byte) 0xFA, (byte) 0x06, (byte) 0xFC};
                    b[4] = new byte[]{(byte) 0xFA, (byte) 0x08, (byte) 0xF2};
                    b[5] = new byte[]{(byte) 0xFA, (byte) 0x0A, (byte) 0xF0};
                    b[6] = new byte[]{(byte) 0xFA, (byte) 0x0C, (byte) 0xF6};
                    b[7] = new byte[]{(byte) 0xFA, (byte) 0x0E, (byte) 0xF4};
                    b[8] = new byte[]{(byte) 0xFA, (byte) 0x10, (byte) 0xEA};
                    b[9] = new byte[]{(byte) 0xFA, (byte) 0x12, (byte) 0xE8};
                    b[10] = new byte[]{(byte) 0xFA, (byte) 0x14, (byte) 0xEE};
                    b[11] = new byte[]{(byte) 0xFA, (byte) 0x16, (byte) 0xEC};
                    b[12] = new byte[]{(byte) 0xFA, (byte) 0x18, (byte) 0xE2};
                    b[13] = new byte[]{(byte) 0xFA, (byte) 0x1A, (byte) 0xE0};
                    b[14] = new byte[]{(byte) 0xFA, (byte) 0x1C, (byte) 0xE6};
                    b[15] = new byte[]{(byte) 0xFA, (byte) 0x1E, (byte) 0xE4};
                    b[16] = new byte[]{(byte) 0xFA, (byte) 0x01, (byte) 0xFB};
                    b[17] = new byte[]{(byte) 0xFA, (byte) 0x03, (byte) 0xF9};
                    b[18] = new byte[]{(byte) 0xFA, (byte) 0x05, (byte) 0xFF};
                    b[19] = new byte[]{(byte) 0xFA, (byte) 0x07, (byte) 0xFD};
                    b[20] = new byte[]{(byte) 0xFA, (byte) 0x09, (byte) 0xF3};
                    b[21] = new byte[]{(byte) 0xFA, (byte) 0x0B, (byte) 0xF1};
                    b[22] = new byte[]{(byte) 0xFA, (byte) 0x0D, (byte) 0xF7};
                    b[23] = new byte[]{(byte) 0xFA, (byte) 0x0F, (byte) 0xF5};
                    b[24] = new byte[]{(byte) 0xFA, (byte) 0x11, (byte) 0xEB};
                    b[25] = new byte[]{(byte) 0xFA, (byte) 0x13, (byte) 0xE9};
                    b[26] = new byte[]{(byte) 0xFA, (byte) 0x15, (byte) 0xEF};
                    b[27] = new byte[]{(byte) 0xFA, (byte) 0x17, (byte) 0xED};
                    b[28] = new byte[]{(byte) 0xFA, (byte) 0x19, (byte) 0xE3};
                    b[29] = new byte[]{(byte) 0xFA, (byte) 0x1B, (byte) 0xE1};
                    b[30] = new byte[]{(byte) 0xFA, (byte) 0x1D, (byte) 0xE7};
                    b[31] = new byte[]{(byte) 0xFA, (byte) 0x1F, (byte) 0xE5};
                    for (int i = 0; i < 32; i++) {
                        serialCom4.getOutputStream().write(b[i]);
                        serialCom4.getOutputStream().flush();
                        System.out.println("发送数据成功:" + MyUtils.byteArrToHex(b[i]));
                        Thread.sleep(20);
                    }
                } catch (IOException | InterruptedException e) {
                    // TODO Auto-generated catch block
                    serialCom4.close();
                    e.printStackTrace();
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class ListenThread extends Thread {
        @Override
        public void run() {
            try {
                //获取串口的输入流对象
                inputStream = serialCom4.getInputStream();
                //从串口读出数据

                //定义用于缓存读入数据的数组
                byte[] cache = new byte[16];

                //通过无限循环的方式来扫描COM4,检查是否有数据到达端口,间隔20ms
                while (true) {
                    //获取串口4收到的字节数
                    availableBytes = inputStream.available();

                    //如果可用字节数大于0则开始转换并获取数据
                    while (availableBytes > 0) {
                        //从串口的输入流对象中读入数据并将数据存放到缓存数组中
                        inputStream.read(cache);
                        //将获取到的数据进行转码并输出
//                        System.out.println(MyUtils.byteArrToHex(cache));
                        for (int i = 0; i < cache.length && i < availableBytes; i++) {
//                            if( cache[i] == (byte)0xF5
//                                    && (cache[i+1] >= (byte)0x00 && cache[i+1] <= (byte)0x31 || cache[i+1] >= (byte)0x80 && cache[i+1] <= (byte)0x6F)
//                                    && cache[i+2] == (cache[i] ^ cache[i+1])
//                            ) {
//                                System.out.println(MyUtils.byteToHex(cache[i])+" "+MyUtils.byteToHex(cache[i+1])+" "+MyUtils.byteToHex(cache[i+2])+" ");
//                                  i += 2;
//                            }
                        System.out.print(MyUtils.byteToHex( cache[i]) + " ");
                        }
                        System.out.println();
                        availableBytes = inputStream.available();
                    }
                    //线程睡眠20ms
                    ListenThread.sleep(20);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}