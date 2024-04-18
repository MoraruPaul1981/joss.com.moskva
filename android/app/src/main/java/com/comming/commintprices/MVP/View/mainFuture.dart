import 'dart:async';

import 'package:flutter/material.dart';
import 'package:logger/logger.dart';

import '../Model/AdressJboss/getAdress.dart';
import '../Model/Futures/one1C/GetFutures1C.dart';
import '../Model/Jsons/One1C/Polo/Person1CList.dart';
import '../Model/Jsons/One1C/Polo/Person1CMap.dart';
import '../Model/Loggers/GetErrors.dart';
import 'Widgets/WidgetListViewCommingPrices.dart';
import 'Widgets/WidgetPhoneMail.dart';
import 'Widgets/WidgetRow.dart';

late Logger logger;


 Future<void> main()  async {
  try {
    //TODO get LOGGER int
    logger=  await  Future<Logger>.value(GetErros().loggers());
    logger.i('Future<void> main() $logger');


    //TODO starting UI
    runApp( const MainWidgetCommingPrices());

    logger.i('starting CommingPrices()');



    //TODO starting DATA
   /// await mainGettingData();
  logger.i('starting mainGettingData()');

    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
}



























































///TODO START DATA
 Future<void> mainGettingData() async {
   try{

    //TODO After Get PING
    String? ping1C=await  getJson1cPing() as String?     ;
    logger.i('ping1C  .. $ping1C '+'ping1C..$ping1C');


    //TODO Get After JSON LIST
    List<Person1CList>   getJSon1CFuture=  await  getJson1cGetJson(   ping1C : ping1C) as List<Person1CList> ;
   logger.i('ping1C  .. $ping1C '+'getJSon1CFuture..$getJSon1CFuture');


     //TODO Get After JSON MAP

/*    List<Person1CMap>   getJSon1CMapFuture=  await  getJson1cMapGetJson(   ping1C : ping1C) as List<Person1CMap> ;
    logger.i('ping1C  .. $ping1C '+'getJSon1CMapFuture..$getJSon1CMapFuture');*/



/*   //TODO After Complete JSON
    getJsonCompeling(getJSon1CFuture: getJSon1CFuture);
    logger.i('END getJsonCompeling() ping1C  .. $ping1C '+'getJSon1CFuture..$getJSon1CFuture');

    logger.i('Isolate.current.debugName  ..  '+Isolate.current.debugName.toString());*/

   //TODO error
 }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
}
 }

















//TODO main metod PING
Future<String?>  getJson1cPing() async {
  //TODO
 late  var    ping1C;
  try{
    //TODO адрес пинга к серверу  Jboss Debug
    var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
    //TODO
    print('adressCurrent1C .. $adressCurrent1C');

    ping1C   = await GetFutures1C().getPing1C(url: adressCurrent1C ) as    String?  ;

    logger.i('ping1C  .. $ping1C '+'ping1C..$ping1C');


    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
  return ping1C;
}






//TODO main metod   JSON
 Future<List<Person1CList>>     getJson1cGetJson( { required String?  ping1C } ) async {
   //TODO
  print('ping1C .. $ping1C');
  late var   getJSon1CFuture;
  //TODO
   try{
  //TODO адрес пинга к серверу  Jboss Debug
  var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
   logger.i('ping1C  .. $ping1C '+'ping1C..$ping1C'+'adressCurrent1C... $adressCurrent1C');
   if (   ping1C!=null) {
     //TODO
     if (ping1C.isNotEmpty) {
     //TODO запускаем
         getJSon1CFuture   =await GetFutures1C().getDownloadJsonList(url: adressCurrent1C, IdUser: 8, UUID: 0)   as   List<Person1CList>      ;
     logger.i('getJSon1CFuture  .. $getJSon1CFuture.last ');
       } else {
       logger.i(' ping1C.isNotEmpty .. $ping1C.isNotEmpty '+'ping1C..$ping1C');
       }
       }else{
     logger.i( 'ping1C..$ping1C');
       }
         //TODO error
       }   catch (e, stacktrace) {
        print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getJSon1CFuture;
}


//TODO main metod   JSON
Future<List<Person1CMap>>     getJson1cMapGetJson( { required String?  ping1C } ) async {
  //TODO
  print('ping1C .. $ping1C');
  late var   getJSon1CFuture;
  //TODO
  try{
    //TODO адрес пинга к серверу  Jboss Debug
    var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
    logger.i('ping1C  .. $ping1C '+'ping1C..$ping1C'+'adressCurrent1C... $adressCurrent1C');
    if (   ping1C!=null) {
      //TODO
      if (ping1C.isNotEmpty) {
        //TODO запускаем
        getJSon1CFuture   =await GetFutures1C().getDownloadJsonMap(url: adressCurrent1C, IdUser: 8, UUID: 0)   as   List<Person1CMap>      ;
        logger.i('getJSon1CFuture  .. $getJSon1CFuture.last ');
      } else {
        logger.i(' ping1C.isNotEmpty .. $ping1C.isNotEmpty '+'ping1C..$ping1C');
      }
    }else{
      logger.i( 'ping1C..$ping1C');
    }
    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
  return getJSon1CFuture;
}



//TODO main metod endinf CompelteN
  void  getJsonCompeling({ required List<Person1CList?>   getJSon1CFuture })   {
  try{
    //TODO yes
    if (  getJSon1CFuture.isNotEmpty) {
      logger.i( 'ping1C..$getJSon1CFuture'+ 'getJSon1CFuture.isNotEmpty '+getJSon1CFuture.isNotEmpty.toString());
    } else {
      logger.i( 'ping1C..$getJSon1CFuture'+ 'getJSon1CFuture.isNotEmpty '+getJSon1CFuture.isNotEmpty.toString());
    }
    logger.i( 'ping1C..$getJSon1CFuture');

    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }

}

//TODO end   DATA





























///TODO UI
class MainWidgetCommingPrices extends StatelessWidget {
  const MainWidgetCommingPrices({super.key});
  @override
  Widget build(BuildContext context) {
    return  MaterialApp(
      home: const StatefulWidgetCommingPrices(),
    );
  }

}

class StatefulWidgetCommingPrices extends StatefulWidget {
  const StatefulWidgetCommingPrices({super.key});
  @override

/*  //TODO widget ROW
  State<StatefulWidgetCommingPrices> createState() => WidgetRow();*/

/*//TODO widget Mail Phone
  State<StatefulWidgetCommingPrices> createState() => WidgetPhoneMail();*/

  //TODO widget Mail ListView.builder Commmign Prices
  State<StatefulWidgetCommingPrices> createState() => WidgetListViewCommingPrices();

}






