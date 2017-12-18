package com.jara.autotestdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Administrator on 2017-12-8.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class , sdk = 22)
public class SampleActivityTest {

    private SampleActivity sampleActivity;
    private Button forwardBtn;
    private Button dialogBtn;
    private Button toastBtn;

    @Before
    public void setUp() throws Exception {
        sampleActivity = Robolectric.setupActivity(SampleActivity.class);
        forwardBtn = (Button)sampleActivity.findViewById(R.id.btn_forward);
        dialogBtn = (Button)sampleActivity.findViewById(R.id.btn_dialog);
        toastBtn = (Button)sampleActivity.findViewById(R.id.btn_toast);
    }

    @Test
    public void testActivity() throws Exception {
        SampleActivity sampleActivity = Robolectric.setupActivity(SampleActivity.class);
        assertNotNull(sampleActivity);
        assertEquals(sampleActivity.getTitle(), "SampleActivity");
    }

    @Test
    public void testLifecycle() {
        ActivityController<SampleActivity> activityController = Robolectric.buildActivity(SampleActivity.class).create().start();
        Activity activity = activityController.get();
        TextView textView = activity.findViewById(R.id.tv_lifecycle_value);
        assertEquals("onCreate", textView.getText().toString());
        activityController.resume();
        assertEquals("onResume", textView.getText().toString());
        activityController.destroy();
        assertEquals("onDestroy", textView.getText().toString());
    }

    @Test
    public void testStartActivity() {
        forwardBtn.performClick();
        Intent expectedIntent = new Intent(sampleActivity, LoginActivity.class);
        Intent actualIntent = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }

    @Test
    public void testToast() {
        toastBtn.performClick();
        assertEquals(ShadowToast.getTextOfLatestToast(), "we love UT");
    }

    @Test
    public void testDialog() {
        dialogBtn.performClick();
        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(latestAlertDialog);
    }

    /**
     * 测试控件状态
     */
    @Test
    public void testViewState() {
        CheckBox checkBox = (CheckBox) sampleActivity.findViewById(R.id.checkbox);
        Button inverseBtn = (Button) sampleActivity.findViewById(R.id.btn_inverse);
        assertTrue(inverseBtn.isEnabled());

        checkBox.setChecked(true);
        //点击按钮，CheckBox反选
        inverseBtn.performClick();
        assertTrue(!checkBox.isChecked());
        inverseBtn.performClick();
        assertTrue(checkBox.isChecked());
    }

    /**
     * 资源文件访问测试
     */
    @Test
    public void testResources() {
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        String activityTitle = application.getString(R.string.title_activity_simple);
        assertEquals("LoveUT", appName);
        assertEquals("SimpleActivity", activityTitle);
    }

    /**
     * 测试广播
     */
    @Test
    public void testBoradcast() {
        ShadowApplication shadowApplication = ShadowApplication.getInstance();

        String action = "com.jara.autotestdemo.login";
        Intent intent = new Intent(action);
        intent.putExtra("EXTRA_USERNAME", "geniusmart");

        //测试是否注册广播接收者
        assertTrue(shadowApplication.hasReceiverForIntent(intent));

        //以下测试广播接受者的处理逻辑是否正确
        MyReceiver myReceiver = new MyReceiver();
        myReceiver.onReceive(RuntimeEnvironment.application, intent);
        SharedPreferences preferences = RuntimeEnvironment.application.getSharedPreferences("account", Context.MODE_PRIVATE);
        assertEquals("geniusmart", preferences.getString("USERNAME", ""));
    }

    /**
     * 测试Fragment
     */
    @Test
    public void testFragment() {
        SampleFragment sampleFragment = new SampleFragment();
        //此api可以主动添加Fragment到Activity中，因此会触发Fragment的onCreateView()
        SupportFragmentTestUtil.startFragment(sampleFragment);
        assertNotNull(sampleFragment.getView());
    }

    /**
     * 测试DelayedTask
     */
    @Test
    public void testDelayedTask(){
        Button delayedTaskBtn = (Button) sampleActivity.findViewById(R.id.btn_delay_task);
        assertFalse(sampleActivity.isTaskFinish);
        delayedTaskBtn.performClick();
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        assertTrue(sampleActivity.isTaskFinish);
    }

}
