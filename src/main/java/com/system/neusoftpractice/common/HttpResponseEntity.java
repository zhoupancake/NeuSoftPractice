package com.system.neusoftpractice.common;

import lombok.Getter;

@Getter
public class HttpResponseEntity {
    private String code;     // 响应状态码
    private Object data;     // 响应数据
    private String message;  // 响应消息

    public HttpResponseEntity() {
    }

    // 构造方法，用于创建HttpResponseEntity对象
    public HttpResponseEntity(String code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    // 创建一个表示错误响应的HttpResponseEntity对象
    public static HttpResponseEntity error(String message) {
        return new HttpResponseEntity("0", null, message);
    }

    // 创建一个表示成功响应的HttpResponseEntity对象，带有数据
    public static HttpResponseEntity success(String message, Object data) {
        return new HttpResponseEntity("200", data, message);
    }

    // 创建一个表示成功响应的HttpResponseEntity对象，不带数据
    public static HttpResponseEntity success(String message) {
        return new HttpResponseEntity("666", null, message);
    }

    // 根据条件创建响应对象，如果条件为真，表示成功，否则表示失败
    public static HttpResponseEntity response(boolean flag, String message, Object data) {
        if (flag) {
            message = message + "成功";
            return success(message, data);
        } else {
            message = message + "失败";
            return error(message);
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private Object data;
        private String message;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        // 设置响应数据
        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        // 设置响应消息
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public HttpResponseEntity build() {
            return new HttpResponseEntity(code, data, message);
        }
    }
}
