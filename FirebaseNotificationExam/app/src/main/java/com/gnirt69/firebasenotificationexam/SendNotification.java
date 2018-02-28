package com.gnirt69.firebasenotificationexam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by youssef on 18/02/18.
 */

public class SendNotification {
    Dialog push_dialog;
    Button push_button;
    String text;
    TextView push_text;


    public SendNotification(Activity activity) {
        push_dialog = new Dialog(activity);
        push_dialog.setCancelable(false);
        push_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        push_dialog.setContentView(R.layout.dialog_notify);
        push_text = (TextView) push_dialog.findViewById(R.id.push_text);
        push_button = (Button) push_dialog.findViewById(R.id.push_button);
        push_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push("", String.valueOf(push_text.getText()));
                push_dialog.cancel();
            }
        });
    }

    public void show(){
        push_dialog.show();
    }

    public void push(String title, String body){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "key=AAAAXBCXGp4:APA91bFE20tEZExHE-rmNVbKSbArSqoW9GeXwBZmc3_531XonSMzOYCwrdj76LtDFeI7qNB81pruEf5wOA2G4FpgJxfdF2-_qfwNLCAa_RP9w4JgTMSAmeJGcVMuGKbTh9pXx710Ixm3"); // <-- this is the important line
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(logging);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com")//url of FCM message server
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
                .build();

        // prepare call in Retrofit 2.0
        FirebaseAPI firebaseAPI = retrofit.create(FirebaseAPI.class);

        //for messaging server
        NotifyData notifydata = new NotifyData(title,body);

        Call<Message> call2 = firebaseAPI.sendMessage(new Message("/topics/all", notifydata));

        call2.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                Log.d("Response ", "onResponse");

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("Response ", "onFailure");
            }


        });
    }
}
