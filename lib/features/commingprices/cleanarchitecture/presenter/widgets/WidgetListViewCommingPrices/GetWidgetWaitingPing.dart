
import 'dart:isolate';

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
                  child: Theme(
                    data: Theme.of(context).copyWith(hintColor: Colors.white),
                    child: CircularProgressIndicator(
                        strokeWidth: 5.0,
                        backgroundColor: Colors.grey,
                        valueColor: AlwaysStoppedAnimation<Color>(Colors.red)),
                  ),
                ),
              ),

            ],
          ),
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
                  child:
                  Text(
                    'Hello!',
                    style: TextStyle(
                      color: Colors.grey,
                      fontSize: 40,
                      fontWeight: FontWeight.w200,
                    ),
                    textAlign: TextAlign.center,
                  ),
                ),
              ),

            ],

          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                  margin: new EdgeInsets.symmetric(vertical: 20.0),
                  height: 80,
                  width: 80,
                  // color: Colors.red,
                  decoration: BoxDecoration(
                    color: Colors.black, //assign either here or to the container
                    borderRadius: BorderRadius.circular(24),),
                  child: Padding(
                    padding: const EdgeInsets.all(2.0),
                    child: AnimatedContainer(
                      duration: const Duration(milliseconds: 500),
                      decoration: BoxDecoration(
                          color: Colors.blue,
                          borderRadius: BorderRadius.circular(80)),
                      child: IconButton(
                        icon: Icon(Icons.add,
                          color:Colors.red,
                          size: 25.0,), onPressed: () {
                        // Do something when the button is pressed
                        print('object;'); },
                      ),
                    ),
                  )
              ),
            ],
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                  margin: new EdgeInsets.symmetric(vertical: 20.0),
                  height: 50,
                  width: 50,
                  // color: Colors.red,
                  decoration: BoxDecoration(
                    color: Colors.black, //assign either here or to the container
                    borderRadius: BorderRadius.circular(24),),
                  child: Padding(
                    padding: const EdgeInsets.all(2.0),
                    child:
                    Tooltip(
                      message: 'Delete',
                      child: IconButton(
                        icon: const Icon(Icons.delete),
                        onPressed: () {
                          print('Delete button pressed');
                        },
                      ),
                    ),
                  )
              ),
            ],
          ),
          //TODO END ROW

        ],
      ),
    );
  }










}