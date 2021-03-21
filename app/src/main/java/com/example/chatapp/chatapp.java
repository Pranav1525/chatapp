package com.example.chatapp;

<<<<<<< HEAD
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

=======
import android.util.Log;

>>>>>>> 4993ddd59d7a6fa7687db298c584a060d5ee622d
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;



public class chatapp{

    public  String result;

    public boolean request_complete;

    public String  endpoint(String data,String requstType,Context context) throws InterruptedException {
        request_complete = false;

<<<<<<< HEAD

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String URL = "http://139.59.8.238/requests/endpoint.php";

            final String requestBody = data;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Server", response);
                    request_complete = true;
                    result = response;
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
=======
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("request_type",requstType)
                .addFormDataPart("data",data)
                .build();

        String url = "http://139.59.8.238/requests/endpoint.php";

        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("myapp",e.toString());
                Log.d("myapp","error occured in postr request");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    request_complete = true;
                    result = response.body().string();
                    Log.d("myapp",result);
>>>>>>> 4993ddd59d7a6fa7687db298c584a060d5ee622d

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);

        while(!request_complete){
            TimeUnit.SECONDS.sleep(1);
        }
        return "{'status':false,'msg':'not such user'}";

    }
};
