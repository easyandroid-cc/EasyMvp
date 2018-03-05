package com.easymvp.simple.clean.usecase;

import com.easymvp.simple.clean.repository.abs.DbDataSource;

import cc.easyandroid.easyclean.UseCase;
import cc.easyandroid.easysqlite.core.EasyDbObject;

/**
 * Created by cgpllx on 2016/8/16.
 */
public class InsertDataFromDbUseCase<T extends EasyDbObject> extends UseCase<InsertDataFromDbUseCase.RequestValues<T>, InsertDataFromDbUseCase.ResponseValue> {
    public final DbDataSource mDbDataSource;

    public InsertDataFromDbUseCase(DbDataSource dbDataSource) {
        this.mDbDataSource = dbDataSource;
    }

    @Override
    protected void executeUseCase(final RequestValues<T> values) {
        boolean seccess = this.mDbDataSource.insert(values.getTabeName(), values.getData());
        if (seccess) {
            getUseCaseCallback().onSuccess(new ResponseValue());
        } else {
            getUseCaseCallback().onError(null);
        }
    }

    public static final class RequestValues<T extends EasyDbObject> implements UseCase.RequestValues {

        private final String mTabeName;
        private final T mData;

        public RequestValues(String tabeName, T data) {
            mTabeName = tabeName;
            mData = data;
        }

        public String getTabeName() {
            return mTabeName;
        }

        public T getData() {
            return mData;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        public ResponseValue() {
        }

    }
}