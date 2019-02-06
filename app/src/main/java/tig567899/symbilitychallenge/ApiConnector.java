package tig567899.symbilitychallenge;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.jar.JarOutputStream;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiConnector{
    private static final String TAG = "ApiConnector";
    private static Retrofit retrofit;
    static private List<Card> cards;

    static void get(ResponseInterface responseInterface)
    {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://omgvamp-hearthstone-v1.p.mashape.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(ApiInterface.class).getData()
                .enqueue(new Callback<CardList>() {
                    @Override
                    public void onResponse(Call<CardList> call, retrofit2.Response<CardList> response) {
                        CardList json = response.body();
                        try
                        {
                            cards = json.allCards();
                        }
                        catch(Exception e)
                        {
                            Log.d (TAG, e.getMessage());
                            responseInterface.onFailure();
                            return;
                        }
                        responseInterface.onSuccess();
                    }

                    @Override
                    public void onFailure(Call<CardList> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });
    }

    public static List<Card> getData()
    {
        return cards;
    }

    public interface ResponseInterface
    {
        void onSuccess();
        void onFailure();
    }
}
