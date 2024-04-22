import 'dart:convert';
import 'dart:typed_data';

import 'package:commintprices/commingprices/features/mvp/model/Converts/InGetConverts.dart';
import 'package:flutter/material.dart';



//TODO
  class GetConverts implements InGetConverts {

    //TODO метод генерации в base64
  @override
 String? convertBase64({ required String user ,required String  password}) {
    // TODO: implement convertBase64
    String?   basicAuthbyte64;
    try{
      /* String credentials ='Basic ' + user+":"+password;
     Codec<String, String> stringToBase64 = utf8.fuse(base64);
      userbyte64 = stringToBase64.encode(credentials.trim());      // dXNlcm5hbWU6cGFzc3dvcmQ=
     /// String decoded = stringToBase64.decode(encoded);*/

      basicAuthbyte64 =
          'Basic ' + base64.encode(utf8.encode('$user:$password'));
      // TODO
      print('basicAuthbyte64 $basicAuthbyte64');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return basicAuthbyte64;
  }




  @override
  String? convertBase64URL({required String user, required String password}) {
    // TODO: implement convertBase64URL
    String?   basicAuthbyte64URL;
    try{
      String credentials =user+ ":"+password;
      Codec<String, String> stringToBase64Url = utf8.fuse(base64Url);
      basicAuthbyte64URL ='Basic ' + stringToBase64Url.encode(credentials.trim());      // dXNlcm5hbWU6cGFzc3dvcmQ=
      //String decoded = stringToBase64Url.decode(encoded);
      // TODO
      print('basicAuthbyte64URL $basicAuthbyte64URL');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return basicAuthbyte64URL;
  }





}