package com.metacoders.restmvvmpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.metacoders.restmvvmpractice.models.Recipe;
import com.metacoders.restmvvmpractice.requests.RecipeApi;
import com.metacoders.restmvvmpractice.requests.ServiceGenerator;
import com.metacoders.restmvvmpractice.requests.responses.RecipeResponse;
import com.metacoders.restmvvmpractice.requests.responses.RecipeSearchResponse;
import com.metacoders.restmvvmpractice.util.Constants;
import com.metacoders.restmvvmpractice.viewmodels.RecipeListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        subscribeObserver();

//        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                /*if (mProgressBar.getVisibility() == View.VISIBLE) {
//
//                    showProgressBar(false);
//
//                } else {
//
//                    showProgressBar(true);
//
//                }*/
//
//                testRetrofitRequest();
//
//            }
//        });
    }

    private void subscribeObserver(){
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {

            }
        });
    }

    private void testRetrofitRequest() {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();

//        Call<RecipeSearchResponse> responseCall = recipeApi
//                .searchRecipe(
//                        Constants.API_KEY,
//                        "Chicken breast",
//                        "1"
//                );
//
//        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//
//                Log.d(TAG, "onResponse: Server response: " + response.toString());
//                if (response.code() == 200) {
//                    Log.d(TAG, "onResponse: " + response.body().toString());
//
//                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
//
//                    for (Recipe recipe : recipes) {
//                        Log.d(TAG, "onResponse: " + recipe.getTitle());
//                    }
//
//                } else {
//                    try {
//
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        @Override
//        public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//
//        }
//    });

        Call<RecipeResponse> responseCall = recipeApi
                .getRecipe(
                        Constants.API_KEY,
                        "35382"
                );

        responseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d(TAG, "onResponse: Server response: " + response.toString());
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());

                    Recipe recipe = response.body().getRecipe();

                    Log.d(TAG, "onResponse: RETRIEVED RECIPE" + recipe.toString());

                } else {
                    try {

                        Log.d(TAG, "onResponse: " + response.errorBody().string());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });


    }
}
