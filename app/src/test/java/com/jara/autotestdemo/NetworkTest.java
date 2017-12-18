package com.jara.autotestdemo;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017-12-6.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class NetworkTest {

    private static final String TAG = "NetworkTest";
    private static final String JSON_ROOT_PATH = "/json/";
    private String jsonFullPath;
    GithubService mockGithubService;

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        jsonFullPath = getClass().getResource(JSON_ROOT_PATH).toURI().getPath();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetInterceptor(jsonFullPath))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        mockGithubService = retrofit.create(GithubService.class);
    }

    @Test
    public void test() throws IOException {
        mockGithubService.user("geniusmart")
                .subscribe(new DefaultObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        System.out.println(user.login);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable.toString());
                        Log.i(TAG, "throwable---> " + throwable);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                        Log.i(TAG, "onComplete");
                    }
                });

    }

}
