package com.easymvp.simple.clean.usecase;

import com.easymvp.simple.clean.repository.abs.DbDataSource;

import java.util.ArrayList;

import cc.easyandroid.easyclean.UseCase;
import cc.easyandroid.easysqlite.core.EasyDbObject;

/**
 * Created by cgpllx on 2016/8/16.
 */
public class GetDatasFromDbUseCase<T extends EasyDbObject> extends UseCase<GetDatasFromDbUseCase.RequestValues, GetDatasFromDbUseCase.ResponseValue<T>> {
    public final DbDataSource mDbDataSource;

    public GetDatasFromDbUseCase(DbDataSource dbDataSource) {
        this.mDbDataSource = dbDataSource;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        this.mDbDataSource.getAll(values.getTabeName(), new DbDataSource.LoadDatasCallback<T>() {
            @Override
            public void ondDatasLoaded(ArrayList<T> tasks) {
                ResponseValue responseValue = new ResponseValue(tasks);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError(null);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final String mTabeName;

        public RequestValues(String tabeName) {
            mTabeName = tabeName;
        }

        public String getTabeName() {
            return mTabeName;
        }
    }

    public static final class ResponseValue<T extends EasyDbObject> implements UseCase.ResponseValue {

        private ArrayList<T> mList;

        public ResponseValue(ArrayList<T> list) {
            this.mList = list;
        }

        public ArrayList<T> getDatas() {
            return mList;
        }
    }
}