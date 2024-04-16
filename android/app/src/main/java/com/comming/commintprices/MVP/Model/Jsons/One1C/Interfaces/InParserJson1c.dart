


import 'package:http/http.dart';

import '../Polo/Person1C.dart';

abstract  class  InParserJson1c {


  List<Person1C> parserPerson(String responseBody) ;

  List<dynamic> getList1cDynamic({ required Response response1C}) ;

   String  getPingDynamic({ required Response response1C}) ;

}















