import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  static const name = "MethodChannelPlues";
  static const method = "sendData";
  MethodChannel _methodChannel = MethodChannel(name);

  BasicMessageChannel<String> _basicMessageChannel = BasicMessageChannel(name, StringCodec());

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    receiveMessage();
  }
   //接收消息
  void receiveMessage() {
    _methodChannel.setMethodCallHandler((call) {
      print('接收Android发过来的消息'+call.method);
      switch(call.method){
        case method:
          setState(() {
            print("接收数据");
          });
          break;
      }
    });
  }
  //发送单向消息
  void sendMessage() async {
    try {
      // String appKey =
      //     await _methodChannel.invokeMethod(method, "向Android发送消息");
      // print("appKey：" + appKey);

//basemessagechannel
      String appKey = await _basicMessageChannel.send(method);
      print("appKey：" + appKey);
    } catch (e) {
      print('捕获到了异常'+e.toString());
    }
  }

  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
      sendMessage();
    });
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(

        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
