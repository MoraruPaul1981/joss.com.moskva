import 'package:flutter/material.dart';

import 'GetError.dart';

//TODO errors all modules
  class PrintingErrors implements GetError {
    @override
    late String abstracNameError;
  @override
  void printingError(Object error,String metoderr,String claserr ){
    String texterr = error as String;
    final nowDateTime = DateTime.now();
    FlutterError.reportError(
      FlutterErrorDetails(
        exception: texterr+' :: bremy ::'+nowDateTime.toString(),
        library: claserr,
        context: ErrorSummary(metoderr),//
      ),
    );
  }




}