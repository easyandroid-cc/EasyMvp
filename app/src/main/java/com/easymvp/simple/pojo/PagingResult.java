package com.easymvp.simple.pojo;

import java.util.List;

public class PagingResult<T> {
    /**
     * recordCount : 45
     * pageCount : 5
     * pageSize : 10
     * list : [{"salePrice":"1000000000.0000","rentPrice":null,"indoorPictures":null,"bedRoom":3,"livingRoom":1,"area":323,"title":"平测试1","labelDesc":"供平過租|換樓首選","gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":1,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"30000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":2,"livingRoom":1,"area":323,"title":"测试楼盘","labelDesc":"供平過租|換樓首選","gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":1,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"3000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":2,"livingRoom":1,"area":1098,"title":"265265296","labelDesc":"換樓首選|地鐵上蓋|名校網","gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":1,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"3000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":2,"livingRoom":1,"area":269,"title":"售盘达大厦的","labelDesc":null,"gardenId":"4066583","gardenName":"金麗閣","gardenAddress":"功樂道81號","suiteRoom":1,"areaName":"觀塘","parentAreaName":"觀塘/藍田/油塘","parentAreaParentName":"九龍"},{"salePrice":null,"rentPrice":null,"indoorPictures":null,"bedRoom":2,"livingRoom":1,"area":161,"title":"测试楼盘","labelDesc":null,"gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":1,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"30000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":2,"livingRoom":1,"area":269,"title":"aaaaa","labelDesc":"供平過租","gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":1,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"50000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":3,"livingRoom":1,"area":323,"title":"fffff","labelDesc":null,"gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":2,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"3000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":1,"livingRoom":1,"area":215,"title":"gggg","labelDesc":null,"gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":0,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"3000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":4,"livingRoom":0,"area":248,"title":"靠118了","labelDesc":"供平過租|換樓首選","gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":3,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"},{"salePrice":"50000000.0000","rentPrice":"0.0000","indoorPictures":null,"bedRoom":2,"livingRoom":1,"area":323,"title":"DRAMATIC SEA VIEW","labelDesc":"換樓首選|地鐵上蓋","gardenId":"4069394","gardenName":"世界花園","gardenAddress":"龍柏街2號","suiteRoom":1,"areaName":"火炭","parentAreaName":"火炭/馬鞍山","parentAreaParentName":"新界"}]
     * pageIndex : 1
     */
    private int recordCount;
    private int pageCount;
    private int pageSize;
    private int pageIndex;
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }
}
