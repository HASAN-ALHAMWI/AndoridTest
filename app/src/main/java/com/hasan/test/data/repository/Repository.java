package com.hasan.test.data.repository;

import com.hasan.test.data.exception.NoInternetException;
import com.hasan.test.data.remote.RemoteSingleOnSubscribe;
import com.hasan.test.data.remote.api.RetrofitService;
import com.hasan.test.data.remote.api.model.article.GetMostPopularArticlesRequest;
import com.hasan.test.data.remote.api.model.article.GetMostPopularArticlesResponse;
import com.hasan.test.data.remote.networkchecker.INetworkChecker;
import com.hasan.test.data.remote.util.RemoteUtil;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class Repository {
    private final RetrofitService retrofitService;
    private final INetworkChecker networkChecker;

    @Inject
    public Repository(RetrofitService retrofitService, INetworkChecker networkChecker) {
        this.retrofitService = retrofitService;
        this.networkChecker = networkChecker;
    }

    // Articles //
    public Single<GetMostPopularArticlesResponse> getMostPopularArticles(GetMostPopularArticlesRequest request) {
        return Single.create(new RemoteSingleOnSubscribe<>(networkChecker, emitter -> {
            Response<GetMostPopularArticlesResponse> response = retrofitService
                    .getMostPopularArticles(request.getSection(),request.getPeriod(), request.getApiKey())
                    .execute();
            if (response.isSuccessful() && response.body() != null && response.body().getResults() != null) {
                emitter.onSuccess(response.body());
            } else if (response.errorBody() != null) {
                emitter.onError(new NoInternetException(RemoteUtil.getErrorMessage(response.errorBody().string())));
            }
        }));
    }

}

