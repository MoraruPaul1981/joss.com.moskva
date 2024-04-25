
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';

import 'IntrafaceNasDataError/InterfaceNasDataError.dart';


/////////TODO класс ожидание ответа пинга от 1с
class GetWidgetHasData implements IntarfaceNasDataError  {
  @override
  Widget getWidgeterrorOrhas({required BuildContext context, required AsyncSnapshot<String> snapshot}) {
    // TODO: implement getWidgetHasData
    ////TODO сам виджет

    return     Scaffold(
      backgroundColor: Colors.black,
      body: Column(
        children: <Widget>[
          Container(
            height: 150.0,
            width: 120.0,
            child: Center(child: new Text(
                ' Нет данных !!!! : ${snapshot.data}',

              style: TextStyle(
                color: Colors.black,
                fontSize: 20,
                fontWeight: FontWeight.w300,
              ),
              textAlign: TextAlign.center,
            )),
          ),
        ],
      ),
    );
  }











}