package com.easymvp.simple.clean.usecase;

import com.easymvp.simple.clean.repository.abs.DbDataSource;

import java.util.ArrayList;

import cc.easyandroid.easyclean.UseCase;
import cc.easyandroid.easysqlite.core.EasyDbObject;


/**
 * Created by cgpllx on 2016/8/16.
 */
public class InsertDatasFromDbUseCase<T extends EasyDbObject> extends UseCase<InsertDatasFromDbUseCase.RequestValues<T>, InsertDatasFromDbUseCase.ResponseValue> {
    public final DbDataSource mDbDataSource;

    public InsertDatasFromDbUseCase(DbDataSource dbDataSource) {
        this.mDbDataSource = dbDataSource;
    }

    @Override
    protected void executeUseCase(final RequestValues<T> values) {
        boolean seccess = this.mDbDataSource.insertAll(values.getTabeName(), values.getDatas());
        if (seccess) {
            getUseCaseCallback().onSuccess(new ResponseValue());
        } else {
            getUseCaseCallback().onError(null);
        }
    }

    public static final class RequestValues<T extends EasyDbObject> implements UseCase.RequestValues {

        private final String mTabeName;
        private final ArrayList<T> mDatas;

        public RequestValues(String tabeName, ArrayList<T> datas) {
            mTabeName = tabeName;
            mDatas = datas;
        }

        public String getTabeName() {
            return mTabeName;
        }

        public ArrayList<T> getDatas() {
            return mDatas;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        public ResponseValue() {
        }

    }
}