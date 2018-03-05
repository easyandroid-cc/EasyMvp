package com.easymvp.simple.pojo;

/**
 * 通用結果對象
 */
public class QfangResult<T> extends QfangBaseResult {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
