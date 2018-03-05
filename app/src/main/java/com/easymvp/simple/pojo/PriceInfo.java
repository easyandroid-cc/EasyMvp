package com.easymvp.simple.pojo;

/**
 * 租价，卖价
 */
public class PriceInfo {

    /**
     * min : 0
     * desc : $700萬以下
     * max : 7000000
     */
//    http://hk.qfang.com/qfang-api/mobile/common/query/querySalePriceCondition
    private long min;
    private String desc;
    private long max;
//    private String easyId = "qfang";


    public PriceInfo() {
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }


}
