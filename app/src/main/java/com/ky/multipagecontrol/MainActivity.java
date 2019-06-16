package com.ky.multipagecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.ky.library.MultiPageControlManager;

public class MainActivity extends AppCompatActivity {

   // private MultiPageControlManager multiPageControlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = findViewById(R.id.mainRelativeLayout);

     //   multiPageControlManager = MultiPageControlManager.getInstance().init(relativeLayout,R.layout.base_loading,R.layout.base_retry,R.layout.base_empty);


    }

//    //加载
//    public void button1(View view) {
//        multiPageControlManager.showLoading();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                multiPageControlManager.showContent();
//            }
//        },3000);
//    }
//
//    //重试
//    public void button2(View view) {
//        multiPageControlManager.showRetry();
//        multiPageControlManager.setOnRetryChildClickListener(R.id.retryButton, new MultiPageControlManager.OnLoadingAndRetryListener() {
//            @Override
//            public void onClick() {
//                multiPageControlManager.showContent();
//            }
//        });
//    }
//
//    //空白状态
//    public void button3(View view) {
//        multiPageControlManager.showEmpty();
//        multiPageControlManager.setOnEmptyClickListener(new MultiPageControlManager.OnLoadingAndRetryListener() {
//            @Override
//            public void onClick() {
//                multiPageControlManager.showContent();
//            }
//        });
//
//    }

    public void button4(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
