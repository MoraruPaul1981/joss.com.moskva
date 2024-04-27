
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

  Future<void> startingTest(Logger logger) async {

    try {
      SynchronousFuture<String>? synchronousFuture;

      Completer<int> completer = new Completer<int> ();


      //TODO





      Future<int> _onPressed() async {
            int result = await compute(loop, 100 );
            return result;
          }


      int result = await Future<int>.sync( () =>   _onPressed( ));
      logger.i('result'+result.toString());

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
    } catch (e) {
      print(e);
    }
    print(startingTest);
  }

//TODO Complete

  Future<void> startingTestCompleter(Logger logger) async {

    try {
      SynchronousFuture<String>? synchronousFuture=new SynchronousFuture('');
      Completer<int> completer = new Completer<int> ();
      // Emulating a long running task

      //Future<int> f() => Future.value(compute(loop, 100 ));
       // int result = await compute(loop, 100 );
      // ....
      logger.i('start value ..  '+'Isolate.current.debugName'+Isolate.current.debugName.toString());

    /*  completer.future.then((value) {
        print(value);
      }).catchError((error) {
        print('Caught error: $error');
        completer.completeError(error);
      });*/


      completer.complete(compute(loop, 100 ));
      //TODO
    int ff= await  completer.future  ;
      logger.i('start value ..  '+'Isolate.current.debugName'+Isolate.current.debugName.toString());

      logger.i('start ff ..  '+ff.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());

    } catch (e) {
      print(e);
    }
    print(startingTest);
  }

  int loop(int val) {
    int count = 0;
    try {

      for (int i = 1; i <= val; i++) {
            count += i;
          }
    } catch (e) {
      print(e);
    }
    return count;
  }

}