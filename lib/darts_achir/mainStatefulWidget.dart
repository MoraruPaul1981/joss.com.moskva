import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  try{

    runApp(
     //TODO start po ful
     //   MyStateFulWidgets()
        MyStatelessWidget3()
    );



    print(" main class BL Hello !!!! " );

    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
}//TODO END void main() {




class MyStatelessWidget3  extends StatelessWidget {

@override
Widget build(BuildContext context) {
  return Scaffold(
    appBar: AppBar(
      title: Text('Woolha.com Flutter Tutorial'),
    ),
    body: Center(
      child: TextButton(
        child: Text('Woolha.com'),
        style: TextButton.styleFrom(
          foregroundColor: Colors.teal, textStyle: TextStyle(
              color: Colors.black,
              fontSize: 40,
              fontStyle: FontStyle.italic
          ),
        ),
        onPressed: () {
          print('Pressed');
        },
      ),
    ),
  );
}
}



