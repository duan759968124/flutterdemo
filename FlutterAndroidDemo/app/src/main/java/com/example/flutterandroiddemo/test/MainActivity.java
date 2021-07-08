package com.example.flutterandroiddemo.test;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;


import androidx.annotation.NonNull;



import org.json.JSONException;
import org.json.JSONObject;

import java.security.Permissions;
import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterFragmentActivity;
import io.flutter.embedding.engine.FlutterEngine;


public class MainActivity extends FlutterFragmentActivity {

    private static final String TAG = "MainActivity";
//    McpCameraPlugin instance;
//    private McpPushPlugin mcpPushPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doWriteExternalStorage();
//        String apiName = "commonApi";
        String apiName = "fcRegistInfoApi/select";
//        getSwitchs(apiName, getParameters());
//        getSwitchs(apiName, getStringParameters());
    }

    private Map<String, String> getParameters() {
        Map<String, String> map = new HashMap<>();
//        map.put("methodName", "switchs");
        // TODO map类型的请求会返回数据，json格式的报错
        map.put("registNo", "RDAA2021320100N0001161");
        return map;
    }

    private String getStringParameters() {
        JSONObject jsonObject = new JSONObject();
        try {
            // TODO map类型的请求会报错，json格式的会返回数据
            jsonObject.put("methodName", "switchs");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public void getSwitchs(String apiName, Map<String, String> data){
//        String json = null;
////        json = map2json(data).toString();
////        json = data.toString();
//        EasyHttp.post(apiName)
////                .upJson(data)
//                .params(data)
//                .accessToken(true)
//                .timeStamp(true)
//                .execute(new SimpleCallBack<String>() {
//                    @Override
//                    public void onError(ApiException e) {
//                        ToastUtil.showToast(e.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(String s) {
//                        ToastUtil.showToast(s);
//
//                    }
//                });
    }

    @SuppressLint("MissingPermission")
    private void doWriteExternalStorage() {

//        Permissions permissions = Permissions.build(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
//        SoulPermission.getInstance().checkAndRequestPermissions(permissions, new CheckRequestPermissionsListener() {
//            @Override
//            public void onAllPermissionOk(Permission[] allPermissions) {
//            }
//
//            @Override
//            public void onPermissionDenied(Permission[] refusedPermissions) {
////                SoulPermission.getInstance().goApplicationSettings();
//            }
//        });
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        registerPlugin(flutterEngine);
    }


    private void registerPlugin(FlutterEngine flutterEngine) {
//        //McpCommonPlugin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(McpCommonPlugin.CHANNEL_NAME));
//        McpCommonPlugin.newRegisterWithMethod(this, flutterEngine);
//
//        //McpTMFPlugin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(McpTMFPlugin.CHANNEL_NAME));
//        McpTMFPlugin.newRegisterWithMethod(this, flutterEngine);

        //McpSharedPreferencesPlugin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(McpSharedPreferencesPlugin.SHARED_PREFERNCES_NAME));
        McpSharedPreferencesPlugin.newRegisterWithMethod(this, flutterEngine);

//        //McpDownloadImgPlugin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(McpDownloadImgPlugin.DOWNLOAD_IMG_NAME));
//        McpDownloadImgPlugin.newRegisterWithMethod(this, flutterEngine);
//
//        //McpNotificationBarPlugin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(MacpNotificationBarPlugin.NOTIFICATION_BAR_NAME));
//        McpNotificationBarPlugin.newRegisterWithMethod(this, flutterEngine);
//
//        //McpCameraPlugin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(McpCameraPlugin.CHANNEL_NAME));
//        McpCameraPlugin.newRegisterWithMethod(this, flutterEngine);
//
////        McpPushPlugin.registerWith(flutterView.getPluginRegistry().registrarFor(McpPushPlugin.PUSH_NAME));
//        mcpPushPlugin = McpPushPlugin.newRegisterWithMethod(this, flutterEngine);
////
//        //McpUserManagerPulgin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(McpUserManagerPulgin.USER_MANAGER_NAME));
//        McpUserManagerPulgin.newRegisterWithMethod(this, flutterEngine);
////
//        //McpLoginPlugin.registerWithMethod(flutterView.getPluginRegistry().registrarFor(McpLoginPlugin.LOGIN_NAME));
//        McpLoginPlugin.newRegisterWithMethod(this, flutterEngine);
//        McpMediaPlugin.newRegisterWithMethod(this,flutterEngine);
//        McpAMapPlugin.newRegisterWithMethod(this,flutterEngine);
//         StartAMapActivity.newRegisterWithMethod(this,flutterEngine);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mcpPushPlugin.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

//    //处理识别返回的结果
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (instance != null) {
//            if (instance.onActivityResult(requestCode, resultCode, intent))
//                super.onActivityResult(requestCode, resultCode, intent);
//        }
//    }
}
