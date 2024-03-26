import 'package:flutter/material.dart';
import '../Model/Errors/ErrorsPrint.dart';
import 'dart:async';
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'dart:io';
import 'package:http/http.dart' as http;



void main() {
  PrintingErrors? printingErrors;
  try {

   throw ('This is an error !!!');
  // runApp(const CommingPrices());
   //TODO error  genetator -26.03.2024--9.17
  } catch (e) {
    printingErrors?.printingError(e,'mainTextButton46.dart','main()');
  }
}


class CommingPrices extends StatelessWidget {
  const CommingPrices({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: const MyHomePage(),
    );
  }

}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(


    );
  }
}
