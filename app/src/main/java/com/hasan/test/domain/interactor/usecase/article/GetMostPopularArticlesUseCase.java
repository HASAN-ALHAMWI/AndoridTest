package com.hasan.test.domain.interactor.usecase.article;


import com.hasan.test.data.remote.api.model.article.GetMostPopularArticlesRequest;
import com.hasan.test.data.remote.api.model.article.GetMostPopularArticlesResponse;
import com.hasan.test.data.remote.api.model.base.PagingData;
import com.hasan.test.data.repository.Repository;
import com.hasan.test.domain.executor.ThreadExecutor;
import com.hasan.test.domain.executor.UIExecutor;
import com.hasan.test.domain.interactor.usecase.base.ParamUseCase;

import javax.inject.Inject;
import io.reactivex.Observable;

public class GetMostPopularArticlesUseCase extends ParamUseCase<GetMostPopularArticlesResponse, GetMostPopularArticlesRequest> {

    private final Repository repository;

    @Inject
    public GetMostPopularArticlesUseCase(UIExecutor uiExecutor, ThreadExecutor threadExecutor, Repository repository) {
        super(uiExecutor, threadExecutor);
        this.repository = repository;
    }

    @Override
    protected Observable<GetMostPopularArticlesResponse> buildObservable(GetMostPopularArticlesRequest request) {
        return repository.getMostPopularArticles(request)
                .toObservable();
    }

}
