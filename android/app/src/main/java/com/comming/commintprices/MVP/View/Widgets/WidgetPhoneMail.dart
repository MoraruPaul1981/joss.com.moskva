import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import '../mainFuture.dart';




//TODO Виджет сотоящий из трех строк Телефон и Две Почты

class WidgetPhoneMail extends State<StatefulWidgetCommingPrices>  {
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
                'Союз-Автодор',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 40.0,
                  fontFamily: 'Pacifico',
                  color: Colors.white,
                ),
              ),
              Text(
                'г.Иваново ул.Проездная 27/18',
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
                      '+79158111806',
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
                      'SOUS@gmial.com',
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
                      'SOUS2@gmial.com',
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
