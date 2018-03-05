package com.easymvp.simple.pojo;


import cc.easyandroid.easyclean.httpextend.easycore.EAResult;

public class QfangBaseResult implements EAResult {

    private String msg;
    private String flag;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean isSuccess() {
        return "1".equals(flag);
    }

    @Override
    public String getEADesc() {
        return msg;
    }

    @Override
    public String getEACode() {
        return flag;
    }
}
