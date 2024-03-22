import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  try{

    runApp(
        Container(
          color: Colors.red,
          child: Center(
            child: Text(" TextWidget  Hello from flutter",
                textDirection: TextDirection.ltr,style: TextStyle(fontSize: 45.0,
            color:Colors.white)
        ),
          )
        )
    );




    //BL b=  BL();
    print(" main class BL Hello !!!! " );

  }    catch (e){
    print(" Exception class BL Hello !!!! " );///

  }
}






