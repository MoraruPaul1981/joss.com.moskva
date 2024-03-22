import 'package:flutter/material.dart';

//TODO errors all modules
  class PrintingErrors  {

  void printingError(Object error,String metoderr,String claserr){
    String texterr = error as String;
    final nowDateTime = DateTime.now();
    FlutterError.reportError(
      FlutterErrorDetails(
        exception: texterr+' :: bremy ::'+nowDateTime.toString(),
        library: claserr,
        context: ErrorSummary(metoderr),
      ),
    );
  }
}