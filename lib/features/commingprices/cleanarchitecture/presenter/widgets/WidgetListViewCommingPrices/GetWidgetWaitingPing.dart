
import 'dart:isolate';

import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';


/////////TODO класс ожидание ответа пинга от 1с
class GetWidgetWaitingPing {

  //TODO Виджет ожидание пипнга от сервера 1С
  Widget getWidgetWaitingPing({required BuildContext context,
    required AsyncSnapshot<String> snapshot,required Color alwaysStop}){
    ////TODO сам виджет

    return     Scaffold(
      backgroundColor: Colors.black,
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[




          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                margin: new EdgeInsets.symmetric(vertical: 20.0),
                height: 50,
                width: 300,
                // color: Colors.red,
                decoration: BoxDecoration(
                  color: Colors.black, //assign either here or to the container
                  borderRadius: BorderRadius.circular(24),),
                child:  Padding(
                  padding: EdgeInsets.all(2.0),
                  child:

                    AnimatedTextKit(
                        animatedTexts: [
                          ColorizeAnimatedText('Союз-Автодор', textStyle: TextStyle(color: Colors.grey,
                            fontSize: 35,
                            fontWeight: FontWeight.w200,),textAlign:  TextAlign.center,
                              colors:[Colors.grey,Colors.blue,Colors.white,Colors.red] ),

                        ]),

//
                 /* Text(
                    'Союз-Автодор',
                    style: TextStyle(
                      color: Colors.grey,
                      fontSize: 30,
                      fontWeight: FontWeight.w200,
                    ),
                    textAlign: TextAlign.center,
                  ),*/
                ),
              ),

            ],

          ),

/*
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                margin: new EdgeInsets.symmetric(vertical: 20.0),
                height: 150,
                width: 150,
                // color: Colors.red,
                decoration: BoxDecoration(
                  color: Colors.black, //assign either here or to the container
                  borderRadius: BorderRadius.circular(24),),
                child: Padding(
                  padding: const EdgeInsets.all(2.0),
                  child:  Theme(
                    data: Theme.of(context).copyWith(hintColor: Colors.white),
                    child: CircularProgressIndicator(
                        strokeWidth: 10.0,
                        backgroundColor: Colors.grey,
                        valueColor: AlwaysStoppedAnimation<Color>(alwaysStop)),
                  ),
                ),
              ),

            ],
          ),
*/



          //TODO END ROW

        ],
      ),
    );
  }










}