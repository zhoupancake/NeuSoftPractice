package com.system.neusoftpractice.util;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import xyz.downgoon.snowflake.Snowflake;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SnowflakeUtil {

    private static final Snowflake snowflake;

    static {
        Long workerId = getWorkId();
        Long datacenterId = getDatacenterId();
        snowflake = new Snowflake(datacenterId, workerId);
    }

    public static String genId() {
        return String.valueOf(snowflake.nextId());
    }

    private static Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            return RandomUtils.nextLong(0,31);
        }
    }

    private static Long getDatacenterId(){
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        System.out.println(Arrays.toString(ints));
        int sums = 0;
        for (int i: ints) {
            sums += i;
        }
        return (long)(sums % 32);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(genId());
    }
}