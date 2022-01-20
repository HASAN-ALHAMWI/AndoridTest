package com.hasan.test.presentation.ui.base.view.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hasan.test.data.local.SharedPreferencesManager;
import com.hasan.test.data.remote.api.model.base.BaseResponse;
import com.hasan.test.domain.interactor.BaseObserver;
import com.hasan.test.domain.interactor.usecase.base.BaseUseCase;
import com.hasan.test.presentation.exception.ExceptionFactory;
import com.hasan.test.utils.lifecycle.Event;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BaseViewModel extends ViewModel {

    private final List<BaseUseCase> useCases = new ArrayList<>();
    private final MutableLiveData<Event<String>> _toastMessage = new MutableLiveData<>();
    private final MutableLiveData<Event<Integer>> _toastMessageResource = new MutableLiveData<>();
    private final MutableLiveData<Event<Boolean>> _hideKeyboard = new MutableLiveData<>();
    public SharedPreferencesManager session;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isLoadingPage = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isLoadingRefresh = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isFirstLoading = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isError = new MutableLiveData<>(false);
    public MutableLiveData<String> toolbarTitle = new MutableLiveData<>("");
    public LiveData<Event<String>> toastMessage = _toastMessage;
    public LiveData<Event<Integer>> toastMessageResource = _toastMessageResource;
    public LiveData<Event<Boolean>> hideKeyboard = _hideKeyboard;


    @Inject
    public BaseViewModel(SharedPreferencesManager session) {
        this.session = session;
    }

    protected void showMessage(String message) {
        _toastMessage.setValue(new Event<>(message));
    }

    protected void showMessage(int stringId) {
        _toastMessageResource.setValue(new Event<>(stringId));
    }

    protected void showMessage(Throwable error) {
        if (error.getMessage() != null)
            showMessage(error.getMessage());
        else
            showMessage(ExceptionFactory.getString());
    }


    protected void showError() {
        isError.setValue(true);
        stopLoading();
    }

    protected void hideKeyboard() {
        _hideKeyboard.setValue(new Event<>(true));
    }

    protected void startLoading(boolean loading, boolean loadingRefresh, boolean loadingPage) {
        isError.setValue(false);
        isFirstLoading.setValue(loading);
        isLoading.setValue(loading);
        isLoadingRefresh.setValue(loadingRefresh);
        isLoadingPage.setValue(loadingPage);
    }

    protected void startLoadingPage() {
        isLoadingPage.setValue(true);
    }

    protected void startLoading() {
        isError.setValue(false);
        isLoading.setValue(true);
    }

    protected void stopLoading() {
        isLoading.setValue(false);
        isLoadingPage.setValue(false);
        isLoadingRefresh.setValue(false);
        isFirstLoading.setValue(false);
    }

    protected boolean isLoading() {
        return isLoading.getValue();
    }

    protected void registerUseCases(BaseUseCase... useCases) {
        this.useCases.addAll(Arrays.asList(useCases));
    }

    public void setToolbarTitle(String title) {
        toolbarTitle.setValue(title);
    }


    public void fetchFirstData() {
        loadData(true, false, false);
    }

    public void fetchDataPage() {
        loadData(false, false, true);
    }

    public void refreshData() {
        loadData(false, true, false);
    }

    public void fetchData(boolean loading, boolean loadingRefresh, boolean loadingPage) {
        startLoading(loading, loadingRefresh, loadingPage);
    }

    public void loadData(boolean loading, boolean loadingRefresh, boolean loadingPage) {
        if (isLoading.getValue() ||
                isLoadingRefresh.getValue() ||
                isLoadingPage.getValue())
            return;
        fetchData(loading, loadingRefresh, loadingPage);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (BaseUseCase useCase : useCases)
            useCase.dispose();
    }
}
