

import 'package:flutter/services.dart';

class SharedPerences {

  static const String mcpChannelName = 'com.picc.nmcp/sharedPreferences';

  static const String insert_data = 'insert';
  static const String get_data = 'getData';
  static const String clean_data = 'cleanData';

  static const MethodChannel _methodChannel =
  const MethodChannel(mcpChannelName);

  ///插入数据  data  为键值名 content 为内容
  Future<String> insert(String key,String content) async{

    Map<String,String> map = {'key':key,'content':content};

    String result = await _methodChannel.invokeMethod(insert_data,map);

    print(result);
    return result;
  }
  /// 获取存储数据  key 为键值
  Future<String> getData(String key,{Function success}) async{

    Map<String,String> map = {'key':key};

    String result = await _methodChannel.invokeMethod(get_data,map);

    if (success != null){//返回请求数据
      success(result);
    }
    return result;
  }
  /// 清除制定数据  key 为键值
  Future<String> cleanData(String key) async{

    Map<String,String> map = {'key':key};

    String result = await _methodChannel.invokeMethod(clean_data,map);

    print(result);
    return result;
  }
}