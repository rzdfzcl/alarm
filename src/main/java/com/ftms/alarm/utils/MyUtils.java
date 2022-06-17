package com.ftms.alarm.utils;

import org.springframework.stereotype.Component;

@Component
public class MyUtils {
    /**
    * 合并数组
    * @param firstArray  第一个数组
    * @param secondArray 第二个数组
    * @return 合并后的数组
    */
    public static byte[] concat(byte[] firstArray, byte[] secondArray) {
        if (firstArray == null || secondArray == null) {
            if (firstArray != null)
                return firstArray;
            if (secondArray != null)
                return secondArray;
            return null;
        }
        byte[] bytes = new byte[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);
        System.arraycopy(secondArray, 0, bytes, firstArray.length, secondArray.length);
        return bytes;
    }


    /**
     * 十进制转十六进制字符串
     * @param param 十进制参数
     * @return 十六进制字符串
     */
    public static String intToHex(int param){
        String result = Integer.toHexString(param);
        return result.length()%2==1?"0"+result:result;
    }

    /**
     * 十六进制字符串转字节数组
     * @param content 16进制字符串
     * @return 字节数组
     */
    public static byte[] hexString2Bytes(String content) {
        int l = content.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; i++) {
            result[i] = Integer.valueOf(content.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return result;
    }

    public static final char HexCharArr[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    /**
     * 字节数转化为十六进制字符串
     * @param byteNumber 字节数
     * @return 十六进制字符串
     */
    public static String byteToHex(byte byteNumber) {
        char result[] = new char[2];
        result[0] = HexCharArr[byteNumber >>> 4 & 0xf];
        result[1] = HexCharArr[byteNumber & 0xf];
        return String.valueOf(result);
    }

    /**
     * 字节数组转化为十六进制字符串
     * @param content 字节数组
     * @return 十六进制字符串
     */
    public static String byteArrToHex(byte[] content) {
        char[] strArr = new char[content.length * 2];
        int i = 0;
        for (byte bt : content) {
            strArr[i++] = HexCharArr[bt>>>4 & 0xf];
            strArr[i++] = HexCharArr[bt & 0xf];
        }
        return new String(strArr);
    }

    /**
     * 十六进制字符串中截取指定位置的字节对应的十六进制字符串
     * @param result 十六进制字符串
     * @return 指定字节对应的十六进制字符串
     */
    public static String byteArrToHex(String result) {

        //例如获取第28个字节
        byte parsing = Integer.valueOf(result.substring(27 * 2, 28 * 2), 16).byteValue();
        //将第28个字节以16进制的方式返回
        String parsingResult = Integer.toHexString(parsing & 0xFF);
        if (parsingResult.length() == 1) {
            parsingResult = "0" + parsingResult;
        }
        return parsingResult;
    }


}
