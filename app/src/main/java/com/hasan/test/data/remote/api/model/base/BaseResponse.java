package com.hasan.test.data.remote.api.model.base;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private String numResults;
    @SerializedName("results")
    @Expose
    private T results;

    public BaseResponse() {
    }

    public BaseResponse(T results) {
        this.results = results;
    }

    public BaseResponse(String status, String copyright, String numResults, T results) {
        this.status = status;
        this.copyright = copyright;
        this.numResults = numResults;
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getNumResults() {
        return numResults;
    }

    public T getResults() {
        return results;
    }
}
