import 'dart:convert';
import 'dart:typed_data';

import 'package:flutter/material.dart';

import '../Errors/ErrorsPrint.dart';
import 'InGetConverts.dart';

//TODO
  class GetConverts implements InGetConverts {

    //TODO метод генерации в base64
  @override
 String? convertBase64({ required String user ,required String  password}) {
    // TODO: implement convertBase64
    String?   userbyte64;
    try{
      String credentials =user+ ":"+password;
      Codec<String, String> stringToBase64 = utf8.fuse(base64);
      String encoded = stringToBase64.encode(credentials);      // dXNlcm5hbWU6cGFzc3dvcmQ=
     /// String decoded = stringToBase64.decode(encoded);
      // TODO
      print('userbyte64 $userbyte64');
    }   catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
    return userbyte64;
  }




  @override
  String? convertBase64URL({required String user, required String password}) {
    // TODO: implement convertBase64URL
    String?   userbyte64URL;
    try{
      String credentials =user+ ":"+password;
      Codec<String, String> stringToBase64Url = utf8.fuse(base64Url);
      userbyte64URL = stringToBase64Url.encode(credentials);      // dXNlcm5hbWU6cGFzc3dvcmQ=
      //String decoded = stringToBase64Url.decode(encoded);
      // TODO
      print('userbyte64URL $userbyte64URL');
    }   catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
    return userbyte64URL;
  }





}