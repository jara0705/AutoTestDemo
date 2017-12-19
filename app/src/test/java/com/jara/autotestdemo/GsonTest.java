package com.jara.autotestdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

import junit.extensions.TestSetup;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-12-6.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class GsonTest {

    private static final String TAG = "GsonTest";
    private static final String JSON_ROOT_PATH = "/json/";
    private static String jsonFullPath;
    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
    }

    @Test
    public void test() throws URISyntaxException {
//        String path = "E:\\workspace\\AutoTestDemo\\app\\src\\test\\resources\\cmd.json";
        String path = getClass().getResource(JSON_ROOT_PATH).toURI().getPath() + "cmd.json";
        String result = FileUtils.readFile(path, "UTF-8").toString();
        Log.i(TAG, "--->" + result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("cmd_down_list");
            Gson gson = new Gson();
            List<HiCmd> hiCmdList = new ArrayList<>();
            Type type = new TypeToken<ArrayList<HiCmd>>(){}.getType();
            hiCmdList = gson.fromJson(jsonArray.toString(), type);
            for (HiCmd hiCmd : hiCmdList) {
                Log.i("testJson", "---->" + hiCmd.to_app_client_id);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
