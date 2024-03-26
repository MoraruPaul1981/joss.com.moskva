import 'package:flutter/material.dart';
import '../Model/Errors/ErrorsPrint.dart';
import 'dart:async';
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'dart:io';
import 'package:http/http.dart' as http;



void main() {
  try {

  // throw ('This is an error !!!');
   runApp(const CommingPrices());
   //TODO error  genetator -26.03.2024--9.17
  } catch (e) {
    PrintingErrors printingErrors= new PrintingErrors();
    printingErrors.printingError(e,'mainTextButton46.dart','main()');
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
  //TODO пользовательские метод Future
  //TODO пользовательские метод Future
  Future<String> getDataFuture() async{
    try{
      await Future.delayed(
        const Duration(
            seconds: 5
        ),
      );
    } catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
  return 'Super!!';
  //  throw 'Erross!!';
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
   return Center(
     child: FutureBuilder<String>(
       future: getDataFuture(),
       builder: (context, AsyncSnapshot snapshot) {
         if(snapshot.connectionState==ConnectionState.waiting){
           return const CircularProgressIndicator();
         }else{

         }
         if(snapshot.hasError){
           return ErrorWidget(
             snapshot.error.toString(),
           );
         }else{
           return Column(
             mainAxisSize: MainAxisSize.min,
             children: [
               Text(
                 snapshot.data.toString(),
               ),
               ElevatedButton(
                   onPressed: (){
                     setState(() {
             print("ffff");
                     });
                   },
                   child: const Text(
                     'Refrech'
                   ))
             ],
           );
         }


         return const CircularProgressIndicator();
       },
     ),
   );
  }
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
