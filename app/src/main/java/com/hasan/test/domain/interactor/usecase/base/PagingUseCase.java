package com.hasan.test.domain.interactor.usecase.base;


import com.hasan.test.domain.executor.ThreadExecutor;
import com.hasan.test.domain.executor.UIExecutor;

public abstract class PagingUseCase<T> extends ParamUseCase<T, PagingUseCaseParams> {
    public PagingUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor) {
        super(uiExecutor, threadExecutor);
    }
}
