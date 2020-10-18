package com.tcl.uf.common.base.util;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RandomGenerator
 * @Description:
 * @date 2018/2/6 下午2:11
 */

public class RandomGenerator {

    /****
     * 通过随机数字,映射ASIC码,转化成字符串即可.随机出一个 0-9 A-Z a-z的字符
     *
     * 0-9 -> 48-57
     * A-Z -> 65-90
     * a-z -> 97-122
     */
    private static int randomInt() {
        int i = (int) (Math.random() * 62) + 48;
        if (i > 57) {
            i += 7;
        }
        if (i > 90) {
            i += 6;
        }
        return i;
    }


    /**
     * 获取随机字符串
     *
     * @param len
     * @return
     */
    public static String getStr(int len) {
        if (len <= 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append((char) randomInt());
        }

        return stringBuilder.toString();
    }

    /**
     * 获取随机int id
     * @param len
     * @return
     */
    public static int getInt(int len) {
        double pow = Math.pow(10, len);
        return (int) (Math.random() * pow / 10 + pow);
    }

}
