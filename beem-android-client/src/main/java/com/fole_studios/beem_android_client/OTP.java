package com.fole_studios.beem_android_client;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.fole_studios.beem_android_client.BeemCredentials.API_KEY;
import static com.fole_studios.beem_android_client.BeemCredentials.BEEM_APP_ID;
import static com.fole_studios.beem_android_client.BeemCredentials.REQUEST_URL;
import static com.fole_studios.beem_android_client.BeemCredentials.SECRET_KEY;
import static com.fole_studios.beem_android_client.BeemCredentials.VERIFY_URL;

public class OTP
{
    public static final String OTP = "BEEM_OTP";


    public JSONObject sendOtp(String recipient)
    {
        OkHttpClient _client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .build();

        MediaType _mediaType = MediaType.parse("application/json");
        RequestBody _body = RequestBody.create(_mediaType, "{\"appId\":"+ BEEM_APP_ID + ",\"msisdn\":\""+ recipient +"\"}");

        Request _request = new Request.Builder()
                .url(REQUEST_URL)
                .post(_body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Credentials.basic(API_KEY, SECRET_KEY))
                .build();

        JSONObject _jsonResponse = null;
        try (Response response = _client.newCall(_request).execute())
        {

            if(response.body() != null)
            {
                String _responseBody = response.body().string();
                _jsonResponse = new JSONObject(_responseBody);
                Log.i(OTP, "sendOtp: " + _jsonResponse);
            }
            // ... do something with response

        }
        catch (IOException | JSONException e)
        {
            // ... handle IO exception
            e.printStackTrace();
        }

        return _jsonResponse;
    }

    public JSONObject validateOTP(String pinId, String pin)
    {
        OkHttpClient _client = new OkHttpClient();
        MediaType _mediaType = MediaType.parse("application/json");
        RequestBody _body = RequestBody.create(_mediaType, "{\"pinId\":\""+ pinId +"\",\"pin\":\""+ pin +"\"}");

        Request _request = new Request.Builder()
                .url(VERIFY_URL)
                .post(_body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Credentials.basic(API_KEY, SECRET_KEY))
                .build();

        JSONObject _jsonResponse = null;
        try (Response response = _client.newCall(_request).execute())
        {

            if(response.body() != null)
            {
                String _responseBody = response.body().string();
                _jsonResponse = new JSONObject(_responseBody);
                //Code 117(Success) 114(Error)
                Log.i(OTP, "validateOTP: " + _jsonResponse);
            }
            // ... do something with response

        } catch (IOException | JSONException e)
        {
            // ... handle IO exception
            e.printStackTrace();
        }

        return _jsonResponse;
    }

}
