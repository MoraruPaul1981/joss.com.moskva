
import 'dart:async';
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';


import '../../../data/entities/Entities1CListManual.dart';
import '../../../data/entities/Entities1CMap.dart';
import '../../../data/remote/Futures/getPing.dart';

///TODO class TEst
class Testing {

  void startingTest(Logger logger){

    SynchronousFuture<String>? synchronousFuture;

    Completer c2 = new Completer();


    //TODO

    int _counter;

    int loop(int val) {
      int count = 0;
      for (int i = 1; i <= val; i++) {
        count += i;
      }
      return count;
    }

    Future<int> _onPressed() async {
      int result = await compute(loop, 100 );
      return result;
    }


    Future<int>.sync( () =>   _onPressed( )).then((value) {
      //TODO then
      logger.i('start value .. ');
      logger.i('start value ..  '+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      return value;

    }).catchError(
            (Object error) {
          logger.i(' get ERROR $error  ');
        }
    )
        .whenComplete(() => {
      logger.i('start value ..  '+'Isolate.current.debugName'+Isolate.current.debugName.toString(),
      ),
    }
    );





    print(startingTest);
  }




}