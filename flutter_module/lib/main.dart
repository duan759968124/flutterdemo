
import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'mcp_sharedperences_plugin.dart';

class PlatformChannel extends StatefulWidget {
  const PlatformChannel({Key key}) : super(key: key);

  @override
  State<PlatformChannel> createState() => _PlatformChannelState();
}

class _PlatformChannelState extends State<PlatformChannel> {

  static const _channel =
  const MethodChannel('plugins.flutter.io/FlutterMessagePlugin');

  static const eventChannel =
  EventChannel('plugins.flutter.io/FlutterEventPlugin');

  @override
  void initState() {
    super.initState();
    eventChannel.receiveBroadcastStream().listen(_onEvent, onError: _onError);
  }

  void _onEvent(Object event) {
    print("收到Android传递消息");
    setState(() {
      // _chargingStatus =
      // "Battery status: ${event == 'charging' ? '' : 'dis'}charging.";
    });
  }

  void _onError(Object error) {
    print("收到Android传递消息");
    setState(() {
      // _chargingStatus = 'Battery status: unknown.';
    });
  }
///向Android发送消息
  void getResult() async {
    try {
      Map<String, Object> map = {"shareUrl": "https://www.baidu.com"};
      var result = await _channel.invokeMethod("getRealUrl");
      print("result"+result);
    } catch (e) {
      print("resulst:eeeeeeee"+e.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("ddd", key: const Key('Battery level label')),
              Padding(
                padding: const EdgeInsets.all(16.0),
                child: ElevatedButton(
                  onPressed: (){
                    this.getResult();
                  },
                  child: const Text('Refresh'),
                ),
              ),
            ],
          ),
          Text("ddddd"),
        ],
      ),
    );
  }
}

void main() {
  runApp(const MaterialApp(home: PlatformChannel()));
}