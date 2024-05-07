
import 'dart:isolate';

import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:logger/src/logger.dart';

import '../../../data/entities/Entities1CMap.dart';
import 'Intarface/IntarfaceWaiting.dart';




/////////TODO класс ожидание ответа пинга от 1с
class GetWidgetWaitingDontData  implements   IntarfaceWaiting {

  //TODO Виджет ожидание пипнга от сервера 1С
  @override
  Widget getWidgetWaitingPing({required BuildContext context,
    required AsyncSnapshot<List<Map<String, List<Entities1CMap>>>?> snapshot,required Color alwaysStop,required String currentText}){
    ////TODO сам виджет
    return new Scaffold(
      backgroundColor: Colors.grey[200],
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[




          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                margin: new EdgeInsets.only(left: 5,top:350,right: 5,bottom: 5),
                height: 80,
                width: 300,
                // color: Colors.red,
                decoration: BoxDecoration(
                  color: Colors.grey[200], //assign either here or to the container
                  borderRadius: BorderRadius.circular(24),),
                child:  Padding(
                  padding: EdgeInsets.all(2.0),
                  child:

                    AnimatedTextKit(
                        animatedTexts: [
                          ColorizeAnimatedText(currentText, textStyle: TextStyle(color: Colors.grey,
                            fontSize: 25,
                            fontWeight: FontWeight.w200,),textAlign:  TextAlign.center,
                              colors:[Colors.black,Colors.white,Colors.grey,Colors.black] ),]
                            ,
                      pause: Duration(microseconds: 1),
                      isRepeatingAnimation: true,
                      repeatForever: false,
                    ),
                ),
              ),

            ],

          ),

          //TODO END ROW

        ],
      ),
    );
  }










}