package com.jara.autotestdemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Administrator on 2017-12-8.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DialogActivityTest{

    @Rule
    public ActivityTestRule<DialogActivity> dialogActivityRule = new ActivityTestRule<>(DialogActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test() {
        SystemClock.sleep(1000);
        onView(withId(R.id.confirm_dialog_button)).perform(click());
        SystemClock.sleep(1000);
        onView(withText("自动化测试")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
    }

    @Test
    public void testClickOkButton() {
        for (int i = 0; i < 10; i++) {
            SystemClock.sleep(1000);
            onView(withId(R.id.confirm_dialog_button)).perform(click());
            SystemClock.sleep(1000);
            onView(withId(android.R.id.button1)).perform(click());
            SystemClock.sleep(1000);
            onView(withId(R.id.status_text)).check(matches(withText("确定")));
            SystemClock.sleep(1000);
        }
    }

    @Test
    public void testClickCancelButton() {
        SystemClock.sleep(1000);
        onView(withId(R.id.confirm_dialog_button)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(android.R.id.button2)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.status_text)).check(matches(withText("取消")));
        SystemClock.sleep(1000);
        Intent intent = new Intent(dialogActivityRule.getActivity(), MainActivity.class);
        mainActivityRule.launchActivity(intent);
        SystemClock.sleep(1000);
        onView(withId(R.id.button))
                .perform(click())
                .check(matches(isDisplayed()));
        SystemClock.sleep(5000);
    }

}
