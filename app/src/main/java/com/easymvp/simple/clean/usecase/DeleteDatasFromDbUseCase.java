package com.easymvp.simple.clean.usecase;

import cc.easyandroid.easyclean.UseCase;
import cc.easyandroid.simple.clean.repository.abs.DbDataSource;

/**
 * Created by cgpllx on 2016/8/16.
 */
public class DeleteDatasFromDbUseCase extends UseCase<DeleteDatasFromDbUseCase.RequestValues, DeleteDatasFromDbUseCase.ResponseValue> {
    public final DbDataSource dbRepository;

    public DeleteDatasFromDbUseCase(DbDataSource dbRepository) {
        this.dbRepository = dbRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        boolean seccess = this.dbRepository.deleteAll(values.getTabeName());
        if (seccess) {
            getUseCaseCallback().onSuccess(new ResponseValue());
        } else {
            getUseCaseCallback().onError(null);
        }

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

    public static final class ResponseValue implements UseCase.ResponseValue {


        public ResponseValue() {
        }


    }
}