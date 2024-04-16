import 'dart:async';
import 'dart:isolate';

import 'package:flutter/material.dart';
import 'package:logger/logger.dart';

import '../Model/AdressJboss/getAdress.dart';
import '../Model/Futures/one1C/GetFutures1C.dart';
import '../Model/Jsons/One1C/Polo/Person1C.dart';
import '../Model/Jsons/One1C/Polo/Person1Cspoler.dart';


var logger;

Future<void> main() async {
  try {
     logger = Logger(
      printer: PrettyPrinter(
      lineLength: 50,
      colors: true,
      printEmojis: true,
      printTime: true,
    ),);
  // throw ('This is an error !!!');
  // runApp(const CommingPrices());

  //proccedtvoid(nik: 121);




    //TODO адрес пинга к серверу  Jboss Debug
    var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
    //TODO
    print('adressCurrent1C .. $adressCurrent1C');

     List<Person1Cspoler>?  personSpoler   =await GetFutures1C().getPing1C(url: adressCurrent1C, IdUser: 8); ///await  GetFutures1C(). getPing1C(  url: adressCurrent1C, IdUser: 8);//TODO 'https://jsonplaceholder.typicode.com/photos'
    //TODO
    logger.i('personSpoler  .. $personSpoler ');


    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
}













//TODO  просто метод  void
void proccedtvoid({String? firstName, String lastName = 'Todo',required int nik}) async{
  try {

    print('firstName $firstName');
    print('lastName $lastName');



    final Future <String> futureString= Isolate.run(
            () =>  Future<String>.sync(()=> getDataFuture() ));

         futureString
        .then((value) => gettt(  ss: value) )
        .catchError((e) {throw Exception('Some arbitrary error');})
        .whenComplete(() =>  print('nik $nik'));
    //TODO error
    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
}


void  gettt( { required String ss })  {
    try{
    print('ss $ss');
      //TODO error
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
}









class CommingPrices extends StatelessWidget {
  const CommingPrices({super.key});
  @override
  Widget build(BuildContext context) {
    return  MaterialApp(
      home: const MyHomePage(),
    );
  }

}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});
  @override
  State<MyHomePage> createState() => _MyHomePageState2();
}



//TODO класс dart future проекта Два #1
class _MyHomePageState2 extends State<MyHomePage>  {




  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      backgroundColor: Colors.grey,
        body: Column(children: <Widget>[



          Row(
            //ROW 1
            children: [
              Container(
                color: Colors.orange,
                margin: EdgeInsets.all(25.0),
                child: FlutterLogo(
                  size: 60.0,
                ),
              ),
            ],
          ),



          Row(//ROW 2
              children: [

                Container(
                  color: Colors.blue,
                  margin: EdgeInsets.all(25.0),
                  child: FlutterLogo(
                    size: 60.0,
                  ),
                ),

              ]),


          Row(// ROW 3
              children: [
                Container(
                  color: Colors.purple,
                  margin: EdgeInsets.all(25.0),
                  child: FlutterLogo(
                    size: 60.0,
                  ),
                ),
              ]),
        ]));
  }

  }



Widget _futureBuilder() {
  return FutureBuilder<String>(
    future: getDataFuture(),
    builder: (context, AsyncSnapshot snapshot) {

      if (snapshot.connectionState == ConnectionState.waiting) {
        return getWaitingFutureBuilder(snapshot);
      } else   {
          return getAfterFutureBuilderColumn(snapshot);
      }

    }
    );
}



    //TODO метод возвраяет ыиджет ошибочный
    ErrorWidget getFutureBuilderError(AsyncSnapshot snapshot) {
      late ErrorWidget errwid;
      try {
        errwid=ErrorWidget(snapshot.error.toString());
        //TODO error
      }   catch (e, stacktrace) {
        print(' get ERROR $e get stacktrace $stacktrace ');
      }
      return errwid;
    }


        //TODO метод возвраяет виджет после Операции
         Widget getAfterFutureBuilderColumn(AsyncSnapshot snapshot) {

           if (snapshot.hasData && snapshot.data.toString().isNotEmpty ) {
             return Column(
               mainAxisSize: MainAxisSize.min,
               children: [
                 Text(
                   snapshot.data.toString(),textAlign: TextAlign.center,
                 ),
                 ElevatedButton(
                     onPressed: () {},
                     child: const Text(
                         'Refrech'
                     ))
               ],
             );
           }else if(snapshot.hasError){

             return getFutureBuilderError(snapshot);

           } else{
             return Column(
               mainAxisSize: MainAxisSize.min,
               children: [
                 Text(
                   snapshot.data.toString(),textAlign: TextAlign.center,
                 ),
                 ElevatedButton(
                     onPressed: () {},
                     child: const Text(
                         'Refrech NULLL'
                     ))
               ],
             );
           }
        }





                //TODO метод   Операции в ПРОЦЕССЕ......
                Widget getWaitingFutureBuilder(AsyncSnapshot snapshot) {
                  return Padding(
                    padding: const EdgeInsets.only(
                        top: 200, bottom: 0, left: 0, right: 0),
                    child: SizedBox(
                      height: 20.0,
                      width: 20.0,
                      child: Transform.scale(
                        scale: 2,
                        child: CircularProgressIndicator(
                          strokeWidth: 3,
                          valueColor: AlwaysStoppedAnimation(
                              Colors.red),

                        ),
                      ),
                    ),
                  );
                }




    //TODO пользовательские метод Future
    Future<String> getDataFuture() async{
      try{
        await  Future.delayed(
          const Duration(
              seconds: 5
          ),
        );
        //TODO error
      }   catch (e, stacktrace) {
        print(' get ERROR $e get stacktrace $stacktrace ');
      }
     return 'FutureBuilder success    '+new DateTime.now().toString();
    //  return '';
      //throw 'Erross!!';
    }











//TODO класс dart future проекта Два #2
class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.blue,
        body: SafeArea(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children:<Widget> [
                CircleAvatar(
                  radius:50,
                ),
                Text(
                  'Priyanshu',
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 40.0,
                    fontFamily: 'Pacifico',
                    color: Colors.white,
                  ),
                ),
                Text(
                  '2222Flutter Developer',
                  style: TextStyle(
                    fontSize: 20.0,
                    fontFamily: 'Source Sans Pro',
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Card(

                  margin: EdgeInsets.all(20.0),
                  color: Colors.white,
                  child: ListTile(
                      leading:
                      Icon(
                        Icons.phone,
                        color: Colors.black,
                        size: 20.0,
                      ),
                      title:
                      Text(
                        '+91 7355689902',
                        style: TextStyle(
                          fontFamily: 'Pacifico',
                          fontSize: 20.0,
                          color: Colors.black54,
                        ),
                      )

                  ),
                ),
                Card(

                  margin: EdgeInsets.all(20.0),
                  color: Colors.white,
                  child: ListTile(
                      leading:
                      Icon(
                        Icons.email,
                        color: Colors.black,
                        size: 20.0,
                      ),
                      title:
                      Text(
                        'priyanshutaru@gmial.com',
                        style: TextStyle(
                          fontFamily: 'Pacifico',
                          fontSize: 20.0,
                          color: Colors.black54,
                        ),
                      )

                  ),
                ),
                Card(
                  margin: EdgeInsets.all(20.0),
                  color: Colors.white,
                  child: ListTile(
                      leading:
                      Icon(
                        Icons.email,
                        color: Colors.black,
                        size: 20.0,
                      ),
                      title:
                      Text(
                        'priyanshutaru@gmial.com',
                        style: TextStyle(
                          fontFamily: 'Pacifico',
                          fontSize: 20.0,
                          color: Colors.black54,
                        ),
                      )

                  ),
                )
              ],
            )
        ),
      );
  }
}

