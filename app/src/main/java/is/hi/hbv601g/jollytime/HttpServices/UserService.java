package is.hi.hbv601g.jollytime.HttpServices;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class UserService {
    private OkHttpClient okHttpClient;
    private Request request;
    private String url;

    public UserService() {
        this.url = "http://localhost:/8080/users";
    }

    public String signIn(String email, String name, String password) {
        okHttpClient = new OkHttpClient();

        request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, response.body().string());
            }
        });
        return "bla";

    }

    public JSONObject createAccount(String email, String name, String password) {
        okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        JSONObject data = new JSONObject();
        try {
            data.put("email", email);
            data.put("name", name);
            data.put("password", password);
        } catch (JSONException e) {
            Log.d("OKHTTP3", "JSON Exception");
        }
        RequestBody body = RequestBody.create(JSON, data.toString());
        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.d("OKHTTP3", "Post request done, got the response");
            Log.d("OKHTTP3", response.body().string());
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("OKHTTP3", "Exception while posting request");
        }
        return null;

    }


}
