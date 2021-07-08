package com.example.flutterandroiddemo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.*
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugins.GeneratedPluginRegistrant

class KtMianActivity : FlutterActivity() {
    private  val TAG = "KtMianActivity"

    lateinit var button:Button
    lateinit var messenger:BinaryMessenger

//    lateinit var basicMessageChannel:BasicMessageChannel<String>
val BATTERY_CHANNEL = "samples.flutter.io/battery"


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        messenger = flutterEngine.dartExecutor.binaryMessenger
        //创建一个channel对象
        val channel = MethodChannel(messenger, BATTERY_CHANNEL)
        //为channel设置回调
        channel.setMethodCallHandler { call, result ->
            // 根据方法名，分发不同的处理
            when(call.method){
                "getBatteryLevel" -> {
                    // 获取传入的参数
                    val msg = call.argument<String>("msg")
                    Log.i("ZHP", "正在执行原生方法，传入的参数是：「$msg」")
                    // 通知执行成功
                    result.success("这是执行的结果")
                }
                else->{
                    // 如果有未识别的方法名，通知执行失败
                    result.error("error_code", "error_message", null)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button_send)
        button.setOnClickListener {
            startActivity(withNewEngine().initialRoute("index").build(this))
            sendMessage()
        }
        initMethod()
    }

    //初始化接收
    private fun initMethod() {

    }

    //发送消息
    private fun sendMessage() {

    }


}