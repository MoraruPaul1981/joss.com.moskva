import 'package:flutter/material.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

// This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const Home(),
    );
  }
}

class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("AppBar верхний "),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Padding(
              padding: const EdgeInsets.only(bottom: 35),
              child: Image.network(
                  "https://media.geeksforgeeks.org/wp-content/uploads/20220112153639/gfglogo-300x152.png"),
            ),
            TextButton(
              child: Text(
                "Visit GeeksforGeeks",
                style: TextStyle(fontSize: 25),
              ),
              onPressed: () async {
                const String _url = "https://www.geeksforgeeks.org";

                print("object");

              },
            )
          ],
        ),
      ),
    );
  }
}
