import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  try{

    runApp(
       // MyStatelessWidget2()
        MyStatelessWidget() //TODO
    );

    //BL b=  BL();
    print(" main class BL Hello !!!! " );

    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
}//TODO END void main() {


class MyStatelessWidget extends StatelessWidget {
  const MyStatelessWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return   Container(
        color: Colors.red,
        child: Center(
          child: Text(" Цены ",
              textDirection: TextDirection.ltr,style: TextStyle(fontSize: 35.0,
                  color:Colors.white)
          ),
        )
    );

  }

}


class MyStatelessWidget2 extends StatelessWidget {
  const MyStatelessWidget2({super.key});

  @override
  Widget build(BuildContext context) {
    return   Container(
        color: Colors.blueAccent,
        child: Center(
          child: Text(" Контракты ",
              textDirection: TextDirection.ltr,style: TextStyle(fontSize: 35.0,
                  color:Colors.white)
          ),
        )
    );

  }

}




