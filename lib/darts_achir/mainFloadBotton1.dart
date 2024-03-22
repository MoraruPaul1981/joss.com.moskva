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
      home: const WidgetFloats(),
    );
  }
}

class WidgetFloats extends StatelessWidget {
  const WidgetFloats({Key? key}) : super(key: key);

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
      size: 28.0,),
      color: Colors.black,
      onPressed: () {
        print("ddd");
      },

      ),
    )
],
      ),




  floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
  floatingActionButton: FloatingActionButton(
    onPressed: (){
      print("Click Floadt");
    },
    backgroundColor: Colors.redAccent,
    foregroundColor: Colors.black,
    child: Icon(Icons.add),
  ) ,

      bottomNavigationBar: BottomAppBar(
       notchMargin: 5.0,
        shape: CircularNotchedRectangle() ,
        color: Colors.redAccent,
        height: 60,
        child: Row(
mainAxisAlignment: MainAxisAlignment.spaceAround,
          mainAxisSize: MainAxisSize.max,
          children: [

            Padding(
                padding: const EdgeInsets.only(left: 10.0),
            child: Column (
              mainAxisSize: MainAxisSize.min,
              children: [
                Icon(
                  Icons.home,
                  color: Colors.black,
                ),
                Text(
                  "Home",
                  style:TextStyle(color:Colors.white),
                )
              ],

            ),
            ),
            Padding(
                padding: const EdgeInsets.only(right: 20.0,top:10.0,bottom: 10.0),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Icon(
                  Icons.home,
                  color: Colors.black,
                ),
                Text(
                  "Settingss",
                  style:TextStyle(color:Colors.white),
                )
              ],
            ),
            ),

            Padding(
              padding: const EdgeInsets.only(right: 20.0,top:10.0,bottom: 10.0),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Icon(
                    Icons.home,
                    color: Colors.black,
                  ),
                  Text(
                      "Adssd",
                    style:TextStyle(color:Colors.white),
                  )
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(right: 20.0,top:10.0,bottom: 10.0),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Icon(
                    Icons.home,
                    color: Colors.black,
                  ),
                  Text(
                      "fffff",
                    style:TextStyle(color:Colors.white),
                  )
                ],
              ),
            )

          ],

        ),
      ),

    );
  }
}
