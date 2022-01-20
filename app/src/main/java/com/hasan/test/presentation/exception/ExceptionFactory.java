package com.hasan.test.presentation.exception;

import androidx.annotation.StringRes;

import com.hasan.test.R;


public class ExceptionFactory {

    @StringRes
    public static int getString() {
        return R.string.no_internet_connection;
    }
}
