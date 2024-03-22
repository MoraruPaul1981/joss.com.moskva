import 'package:flutter/material.dart';
import 'package:flutter/services.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

// This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          appBarTheme: AppBarTheme(
            color: Colors.redAccent,
            elevation:1,
            systemOverlayStyle: SystemUiOverlayStyle.dark,
            //other options
          )

      ),
      //TODO запускаем сам выджет
      home: const WidgetFloatsTwo(),
    );
  }
}

class WidgetFloatsTwo extends StatelessWidget {
  const WidgetFloatsTwo({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      backgroundColor: Colors.grey.shade300,
      appBar: AppBar(
        elevation: 5,
        title: Align(
          alignment: Alignment.center,
          child: Padding(
            padding: const EdgeInsets.all(5.0),
            child: const Text(
              'This is the title !',
              style: TextStyle(color: Colors.black),
            ),
          ),
        ),
actions: [
    Padding(
      padding: const EdgeInsets.all(5.0),
      child: IconButton(
      icon: const Icon(
        Icons.settings,
      size: 18.0,),
      color: Colors.black,
      onPressed: () {
        print("ddd");
      },

      ),
    )
],
      ),
        bottomNavigationBar: BottomNavigationBar(
          backgroundColor: Colors.redAccent,

          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              icon: Column(
                children: [
                  Icon(Icons.call),
                ],
              ),
              label: 'Calls',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.camera),
              label: 'Camera',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.chat),
              label: 'Chats',
            ),
          ],
        )
    );


  }//TODO end Widget build(BuildContext context) {

  }//TODO class WidgetFloatsTwo extends StatelessWidget {

