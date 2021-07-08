package com.example.flutterandroiddemo;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.example.flutterandroiddemo.test.FlutterEventPlugin;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;


public class MainActivity extends FlutterActivity  {
    private static final String TAG = "MainActivity";

    Button btnSend;
    FlutterEventPlugin flutterEventPlugin;
    MethodChannel methodChannel ;
    public static FlutterEngine flutterEngines;


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        this.flutterEngines = flutterEngine;
        //创建通道
        flutterEventPlugin = new FlutterEventPlugin();
        flutterEngine.getPlugins().add(flutterEventPlugin);


//        McpSharedPreferencesPlugin.newRegisterWithMethod(this, flutterEngine);
        methodChannel = new MethodChannel(flutterEngine.getDartExecutor(),"plugins.flutter.io/FlutterMessagePlugin");
        methodChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                Log.i(TAG, "onMethodCall: "+call.method);
                if (call.method.equals("getRealUrl")){
                    result.success("收到消息");
                }else{
                    result.error("0","发送失败","错误信息提示");
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSend = findViewById(R.id.button_send);
        btnSend.setOnClickListener(view -> {
            startActivity(FlutterActivity.createDefaultIntent(this));
            flutterEventPlugin.sendContent("发送消息——————————————");
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }




}