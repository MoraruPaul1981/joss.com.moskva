

class GetConstants {

  late String  serverJboss="http://" + "80.70.108.165" + ":" + "8888" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss".trim() as String;
  String get  GetttingAdressJboss  =>serverJboss;


  late String   serverJbossDebug="http://" + "192.168.3.4" + ":" + "8080" + "/"+ "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss".trim() as String;
  String get  GetttingAdressJbossDebug  =>serverJbossDebug;


  late String   serverPrices="http://uat.dsu1.ru:55080/dds/hs/jsonto1ccena/listofdocuments".trim() as String;
  String get  GetttingserverPrices  =>serverPrices;


  late String   serverPricesDebug="http://uat.dsu1.ru:55080/dds/hs/jsonto1ccena/listofdocuments".trim() as String;
  String get  GetttingserverPricesDebug  =>serverPricesDebug;

  late  String   server1CPay  ="http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments".trim() as String;
  String get  Getttingserver1CPay  =>server1CPay;

}