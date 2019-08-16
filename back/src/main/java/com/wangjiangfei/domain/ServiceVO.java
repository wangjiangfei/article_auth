package com.wangjiangfei.domain;

import java.io.Serializable;

/**
 * @author wangjiangfei
 * @date 2019/5/29 9:20
 * @description 后端返回的实体
 */
public class ServiceVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T info;

    public ServiceVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceVO(int code, String msg, T info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ServiceVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", info=" + info +
                '}';
    }
}
