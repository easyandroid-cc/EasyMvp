package com.easymvp.simple.clean.repository.abs;

import java.util.ArrayList;

import cc.easyandroid.easysqlite.core.EasyDbObject;


/**
 * Created by cgpllx on 2016/8/16.
 */
public interface DbDataSource {


    <T extends EasyDbObject> void getAll(String tabName, LoadDatasCallback<T> callback);

    <T extends EasyDbObject> boolean deleteAll(String tabName);

    <T extends EasyDbObject> boolean deleteById(String tabName, String id);

    <T extends EasyDbObject> boolean insert(String tabName, T dto);

    <T extends EasyDbObject> boolean insertAll(String tabName, ArrayList<T> arrayList);

    interface LoadDatasCallback<T> {

        void ondDatasLoaded(ArrayList<T> datas);

        void onDataNotAvailable();
    }


}
