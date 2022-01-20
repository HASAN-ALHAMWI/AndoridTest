package com.hasan.test.data.remote.api.model.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMostPopularArticlesRequest {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("apiKey")
    @Expose
    private String apiKey;

    public GetMostPopularArticlesRequest(String section, String period, String apiKey) {
        this.section = section;
        this.period = period;
        this.apiKey = apiKey;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
