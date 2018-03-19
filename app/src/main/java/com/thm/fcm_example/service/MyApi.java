package com.thm.fcm_example.service;

import com.thm.fcm_example.model.ResponseMessage;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by thm on 15/03/2018.
 */
public interface MyApi {
    @POST("notification/topic")
    Call<ResponseMessage> sendTopicMessage(@Query("topic") String topic,
                                           @Query("body") String body,
                                           @Query("title") String title);
}
