package com.edutainer.in.workplace.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommonFunctions {
    private static String BASE_URL = "https://www.edutainer.in/edutainer_api/";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void loadCategory(final OkHttpAsyncResponse okHttpAsyncResponse){
        final Request request = new Request.Builder().
                url(BASE_URL + "category.php").
                get().
                build();

        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });
    }

    public static void login(String email_password, String password, final OkHttpAsyncResponse okHttpAsyncResponse){
        RequestBody body = new FormBody.Builder().
                add("email", email_password).
                add("password", password).
                build();

        final Request request = new Request.Builder().
                url(BASE_URL + "login.php").
                post(body).
                build();


        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });
    }

    public static void forgotPassword(String email_password, final OkHttpAsyncResponse okHttpAsyncResponse){
        RequestBody body = new FormBody.Builder().
                add("email", email_password).
                build();

        final Request request = new Request.Builder().
                url(BASE_URL + "forgetpassword.php").
                post(body).
                build();


        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });

    }

    public static void register(String name, String email, String mobile, String password, String gen_code, String ref_code, final OkHttpAsyncResponse okHttpAsyncResponse){
        RequestBody body = new FormBody.Builder().
                add("name", name).
                add("email", email).
                add("mobile", mobile).
                add("imei", "").
                add("date", "").
                add("password", password).
                add("gen_code", gen_code).
                add("ref_code", ref_code).
                build();

        final Request request = new Request.Builder().
                url(BASE_URL + "register.php").
                post(body).
                build();


        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });

    }

    public static void loadLesson(String id, final OkHttpAsyncResponse okHttpAsyncResponse){
        RequestBody body = new FormBody.Builder().
                add("id", id).
                build();

        final Request request = new Request.Builder().
                url(BASE_URL + "lessons.php").
                post(body).
                build();


        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });

    }

    public static void coupon_code(String coupon, final OkHttpAsyncResponse okHttpAsyncResponse){
        RequestBody body = new FormBody.Builder().
                add("coupon_code", coupon).
                build();

        final Request request = new Request.Builder().
                url(BASE_URL + "apply_coupon.php").
                post(body).
                build();


        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });

    }

    public static void address(String street, String town, String state, String pinCode, String userId, final OkHttpAsyncResponse okHttpAsyncResponse){
        RequestBody body = new FormBody.Builder().
                add("street", street).
                add("town", town).
                add("state", state).
                add("pincode", pinCode).
                add("user_id", userId).
                build();

        final Request request = new Request.Builder().
                url(BASE_URL + "user_address.php").
                post(body).
                build();


        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });

    }

    public static void payment(String user_id, String course_id, String status, String order_id, String date, String coupon_code, final OkHttpAsyncResponse okHttpAsyncResponse){
        RequestBody body = new FormBody.Builder().
                add("user_id", user_id).
                add("course_id", course_id).
                add("status", status).
                add("order_id", date).
                add("coupon_code", coupon_code).
                build();

        final Request request = new Request.Builder().
                url(BASE_URL + "buy_course.php").
                post(body).
                build();


        AppController.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpAsyncResponse.processFinish(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpAsyncResponse.processFinish(response.body().string());
            }

        });

    }

}
