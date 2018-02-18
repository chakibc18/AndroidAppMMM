package com.example.youssef.myapplication.firebase;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FirebaseAPI {

    @POST("/fcm/send")
    Call<Message> sendMessage(@Body Message message);

}