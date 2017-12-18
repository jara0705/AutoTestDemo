package com.jara.autotestdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-12-8.
 */

public class DialogActivity extends Activity {

    private TextView textView;
    private TextView textView2;
    private int i = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        textView = findViewById(R.id.status_text);
        textView2 = findViewById(R.id.status_text2);

        findViewById(R.id.confirm_dialog_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自动化测试");
        builder.setMessage("千万别眨眼，这是自动弹出的");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("取消");
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("确定");
                dialog.dismiss();
            }
        });
        textView2.setText("" + i);
        i++;
        builder.create().show();
    }
}
