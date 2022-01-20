package com.hasan.test.presentation.ui.base.view;

import androidx.annotation.StringRes;

public interface IBaseView {
    void showMessage(String message);

    void showMessage(@StringRes int stringId);

    void hideKeyboard();
}
