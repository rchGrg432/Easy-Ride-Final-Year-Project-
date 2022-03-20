package com.richa.easyride.api;
import com.richa.easyride.api.response.CategoryResponse;
import com.richa.easyride.api.response.CycleResponse;
import com.richa.easyride.api.response.LoginResponse;
import com.richa.easyride.api.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("api/v1/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/v1/register")
    Call<RegisterResponse> register(@Field("name") String names, @Field("email") String email, @Field("password") String password,
                                    @Field("dateofbirth") String dateofbirth, @Field("contact") String contact);

    @GET("api/v1/get-all-cycle")
    Call<CycleResponse> getAllCycles();

    @FormUrlEncoded
    @POST("/api/v1/cart")
    Call<RegisterResponse> addToCart(@Header("Apikey") String apikey, @Field("p_id") int p, @Field("quantity") int q);

    @GET("api/v1/get-categories")
    Call<CategoryResponse> getCategories();

    @GET("api/v1/get-cycle-by-category")
    Call<CycleResponse> getCyclesByCategory(@Query("c_id") int catID);
}
