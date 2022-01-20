package com.hasan.test.presentation.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hasan.test.data.entity.Article;
import com.hasan.test.data.local.SharedPreferencesManager;
import com.hasan.test.data.remote.api.model.article.GetMostPopularArticlesRequest;
import com.hasan.test.data.remote.api.model.article.GetMostPopularArticlesResponse;
import com.hasan.test.domain.interactor.BaseObserver;
import com.hasan.test.domain.interactor.usecase.article.GetMostPopularArticlesUseCase;
import com.hasan.test.presentation.ui.base.view.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends BaseViewModel {

    private final GetMostPopularArticlesUseCase getMostPopularArticlesUseCase;

    public MutableLiveData<List<Article>> data = new MutableLiveData<>();

    @Inject
    public MainViewModel(SharedPreferencesManager session,
                         GetMostPopularArticlesUseCase getMostPopularArticlesUseCase) {
        super(session);
        this.getMostPopularArticlesUseCase = getMostPopularArticlesUseCase;
        registerUseCases(getMostPopularArticlesUseCase);
        fetchFirstData();
    }

    @Override
    public void fetchData(boolean loading, boolean loadingRefresh, boolean loadingPage) {
        super.fetchData(loading, loadingRefresh, loadingPage);
        getMostPopularArticlesUseCase.execute(new GetMostPopularArticlesObserver(),
                new GetMostPopularArticlesRequest("all-sections", "7", "59iFDTvjB1aCRoD2gDn8jXO0DjBSRXrV"));
    }

    public class GetMostPopularArticlesObserver extends BaseObserver<GetMostPopularArticlesResponse> {

        @Override
        public void onNext(@NonNull GetMostPopularArticlesResponse response) {
            stopLoading();
            data.setValue(response.getResults());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            stopLoading();
        }
    }
}
