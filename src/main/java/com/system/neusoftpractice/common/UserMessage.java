package com.system.neusoftpractice.common;

// ThreadLocal配置
public class UserMessage {
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return local.get();
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public static void setUsername(String username) {
        local.set(username);
    }

    /**
     * 清除用户名
     */
    public static void remove() {
        local.remove();
    }
}
