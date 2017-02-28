package org.seckill.dto;

/**
 * Created by jianjun-wu on 2017/2/24.
 */


public class SeckillResult<T>
{

    private  boolean success;
    private T Data;
    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        Data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
