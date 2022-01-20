package com.hasan.test.data.remote.api;


import static com.hasan.test.data.remote.api.ApiEndPoints.MOST_POPULAR_ARTICLES;

import com.hasan.test.data.remote.api.model.article.GetMostPopularArticlesResponse;
import com.hasan.test.data.remote.api.model.base.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET(MOST_POPULAR_ARTICLES + "/" + "{section}" + "/" + "{period}.json")
    Call<GetMostPopularArticlesResponse> getMostPopularArticles(@Path(value = "section", encoded = true) String section,
                                                                @Path(value = "period", encoded = true) String period,
                                                                @Query(value = "api-key") String apiKey);


}

