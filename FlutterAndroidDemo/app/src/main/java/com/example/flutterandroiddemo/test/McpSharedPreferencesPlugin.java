package com.example.flutterandroiddemo.test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

import static android.content.Context.MODE_PRIVATE;

public class McpSharedPreferencesPlugin implements MethodChannel.MethodCallHandler {


    public static final String SHARED_PREFERNCES_NAME = "com.picc.nmcp/sharedPreferences";
    //插入数据
    public static final String INSERT = "insert";
    //获取存储数据
    public static final String GET_DATA = "getData";
    //清除数据
    public static final String CLEAN_DATA = "cleanData";


    private Activity mActivity;

    private McpSharedPreferencesPlugin(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * Plugin registration.
     */
    public static void registerWithMethod(PluginRegistry.Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), SHARED_PREFERNCES_NAME); //这里对应dart端的 MethodChannel
        channel.setMethodCallHandler(new McpSharedPreferencesPlugin(registrar.activity()));
    }

    public static void newRegisterWithMethod(Activity activity, FlutterEngine engine) {
        Log.i("TAG", "newRegisterWithMethod: 实例化channel通道");
        final MethodChannel channel = new MethodChannel(engine.getDartExecutor().getBinaryMessenger(), SHARED_PREFERNCES_NAME); //这里对应dart端的 MethodChannel
        channel.setMethodCallHandler(new McpSharedPreferencesPlugin(activity));
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case INSERT:
                insert(methodCall, result);
                break;
            case GET_DATA:
                getData(methodCall, result);
                break;
            case CLEAN_DATA:
                cleanData(methodCall, result);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    /**
     * 根据key  获取存储数据
     *
     * @param methodCall
     * @param result
     */
    private void getData(MethodCall methodCall, MethodChannel.Result result) {

        if (methodCall != null && methodCall.arguments != null) {
            String key = methodCall.argument("key");
            SharedPreferences sharedPreferences = mActivity.getSharedPreferences(key, MODE_PRIVATE);
            Log.e("key---", key + "-----" + sharedPreferences.getString(key, null));
            result.success(sharedPreferences.getString(key, null));
        }
    }

    /**
     * 存入数据  data 存入的键值   content 存入的内容
     *
     * @param methodCall
     * @param result
     */
    private void insert(MethodCall methodCall, MethodChannel.Result result) {
        Log.i("TAG", "insert: "+methodCall.method);
        if (methodCall != null && methodCall.arguments != null) {
            //解析参数
            String data = methodCall.argument("key");
            String content = methodCall.argument("content");
//            if ("userInfo".equals(data)) {
//                UserInfo userInfo = new Gson().fromJson(content, UserInfo.class);
//                UserHelper.update(userInfo);
//            }
            SharedPreferences sharedPreferences = mActivity.getSharedPreferences(data, MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            //使用put方法将数据存入Shareprences中
            edit.putString(data, content);
            edit.commit();
            /**
             * 成功返回
             */
            result.success("success");
        } else {
            /**
             * 错误返回
             */
            result.error("Data is Null", null, null);
        }

    }

    /**
     * 清除  数据
     *
     * @param methodCall
     * @param result
     */
    private void cleanData(MethodCall methodCall, MethodChannel.Result result) {

        if (methodCall != null && methodCall.arguments != null) {
            String key = methodCall.argument("key");
            SharedPreferences sharedPreferences = mActivity.getSharedPreferences(key, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            result.success("清除数据");
        }
    }

}
