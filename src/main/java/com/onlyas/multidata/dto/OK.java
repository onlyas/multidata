package com.onlyas.multidata.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author Danny
 */
public class OK<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code = 200;

    @Getter
    @Setter
    private String msg = "success";

    @Getter
    @Setter
    private T data;

    public OK() {
        super();
    }

    public OK(T data) {
        super();
        this.data = data;
    }

    public OK(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public OK(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public OK(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = 0;
    }

}
