package com.easymvp.simple.clean.usecase;

import cc.easyandroid.easyclean.UseCase;
import cc.easyandroid.simple.clean.repository.abs.DbDataSource;

/**
 * Created by cgpllx on 2016/8/16.
 */
public class DeleteByIdFromDbUseCase extends UseCase<DeleteByIdFromDbUseCase.RequestValues, DeleteByIdFromDbUseCase.ResponseValue> {
    public final DbDataSource dbRepository;

    public DeleteByIdFromDbUseCase(DbDataSource dbRepository) {
        this.dbRepository = dbRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        boolean seccess = this.dbRepository.deleteById(values.getTabeName(), values.getId());
        if (seccess) {
            getUseCaseCallback().onSuccess(new ResponseValue());
        } else {
            getUseCaseCallback().onError(null);
        }

    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final String mTabeName;
        private final String mId;

        public RequestValues(String tabeName, String id) {
            mTabeName = tabeName;
            mId = id;
        }

        public String getTabeName() {
            return mTabeName;
        }

        public String getId() {
            return mId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        public ResponseValue() {

        }


    }
}