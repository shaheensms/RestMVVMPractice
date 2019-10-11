package com.metacoders.restmvvmpractice.requests;

import com.metacoders.restmvvmpractice.requests.responses.RecipeResponse;
import com.metacoders.restmvvmpractice.requests.responses.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    //    Search
    @GET("api/search")
    Call<RecipeSearchResponse> searchRecipe(
            @Query("key") String key, // ?
            @Query("q") String query, // &
            @Query("page") String page

    );

    //    Search
    @GET("api/get")
    Call<RecipeResponse> getRecipe(
            @Query("key") String key,
            @Query("rId") String recipe_id
    );
}
