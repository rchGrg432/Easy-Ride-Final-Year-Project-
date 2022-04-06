package com.richa.easyride.api;
import com.richa.easyride.api.response.CategoryResponse;
import com.richa.easyride.api.response.CycleResponse;
import com.richa.easyride.api.response.DashResponse;
import com.richa.easyride.api.response.LoginResponse;
import com.richa.easyride.api.response.RegisterResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("api/v1/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password_hash") String password_hash);

    @FormUrlEncoded
    @POST("api/v1/register")
    Call<RegisterResponse> register(@Field("name") String names, @Field("email") String email, @Field("password") String password,
                                    @Field("dateofbirth") String dateofbirth, @Field("contact") String contact);

    @FormUrlEncoded
    @POST("api/v1/forget-password")
    Call<RegisterResponse> forgotpassword(@Header("Apikey") String apikey, @Field("password") String password);

    @GET("api/v1/get-all-cycle")
    Call<CycleResponse> getAllCycles();

    @FormUrlEncoded
    @POST("/api/v1/cart")
    Call<RegisterResponse> addToCart(@Header("Apikey") String apikey, @Field("p_id") int p, @Field("quantity") int q);

    @GET("api/v1/get-categories")
    Call<CategoryResponse> getCategories();

    @GET("api/v1/get-cycle-by-category")
    Call<CycleResponse> getCyclesByCategory(@Query("c_id") int catID);

    @DELETE("/api/v1/category")
    Call<RegisterResponse> deleteCategory(@Header("Apikey") String apikey, @Query("c_id") int catID);

    @POST("/api/v1/upload-product")
    Call<RegisterResponse> uploadProduct(
            @Header("Apikey") String apikey,
            @Part MultipartBody.Part[] files,
            @Part("cycle_name") RequestBody cycle_name,
            @Part("rental_rate") RequestBody rental_rate,
            @Part("description") RequestBody description,
            @Part("quantity") RequestBody quantity,
            @Part("availability") RequestBody availability,
            @Part("categories") RequestBody categories
    );

    @GET("/api/v1/dash")
    Call<DashResponse> getDash(@Header("Apikey") String apikey);

    @Multipart
    @POST("/api/v1/upload-category")
    Call<RegisterResponse> uploadCategory(
            @Header("Apikey") String apikey,
            @Part MultipartBody.Part file,
            @Part("name") RequestBody name);


    @FormUrlEncoded
    @POST("/api/v1/order")
    Call<RegisterResponse> order(@Header("Apikey") String apikey,
                                @Field("payment_type") int p_type,
                                @Field("payment_status")  String paymentRefrence);
}
