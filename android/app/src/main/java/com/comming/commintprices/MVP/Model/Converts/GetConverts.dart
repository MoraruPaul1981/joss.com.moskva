import 'dart:convert';
import 'dart:typed_data';

import 'package:flutter/material.dart';

import '../Errors/ErrorsPrint.dart';
import 'InGetConverts.dart';

//TODO
  class GetConverts implements InGetConverts {

    //TODO метод генерации в base64
  @override
  Uint8List? convertBase64(String userorpassword) {
    // TODO: implement convertBase64
    Uint8List?   userbyte64;
    try{
      final bytes = utf8.encode(userorpassword);
      userbyte64 = base64.encode(bytes) as Uint8List  ;
      // TODO
      print('userbyte64 $userbyte64');
    }   catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
    return userbyte64;
  }





}