

import 'InFuture.dart';

////TODO jboss  ////TODO jboss ////TODO jboss ////TODO jboss



class GetAdressJboss implements InAdressJboss{
  @override
  String? adressJboss({ required String JobForServer,required int IdUser,required int VersionData}) {
    // TODO: implement adressJboss
    // TODO: implement adressJboss
    String?  serverJboss;
    try {
      // TODO: 18.03.2023 московский сервер
   //  8888,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
  //   8890,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
    // 8889,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский

      var jbossserverlink = "http://" + "80.70.108.165" + ":" + "8888" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss".trim() as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer.toString()+
          "&" + "IdUser="+IdUser.toString()
          + "&" + VersionData.toString();
    serverJboss = jbossserverlink + jbossserverparams;
      //TODO
      print('serverJboss .. $serverJboss');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  serverJboss;
  }
}










class GetAdressJbossDebug implements InAdressJboss {
  //TODO
  @override
  String? adressJboss({required String JobForServer,required int IdUser,required int VersionData}) {
    // TODO: implement adressJboss
    // TODO: implement adressJboss
    String?  serverJbossDebug;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+
          "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss".trim() as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer.toString()+
          "&" + "IdUser="+IdUser.toString()
          + "&" + VersionData.toString();
      serverJbossDebug = jbossserverlink + jbossserverparams;
      //TODO
      print('serverJbossDebug .. $serverJbossDebug');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  serverJbossDebug;
  }
}

















////TODO 1c ////TODO 1c ////TODO 1c ////TODO 1c

















//TODO Prices


class GetAdress1CPrices implements InAdress1C{
  @override
  String? adress1C() {
    // TODO: implement adress1C
    // TODO: implement adressJboss
    String?   serverPrices;
    try {
      serverPrices    ="http://uat.dsu1.ru:55080/dds/hs/jsonto1ccena/listofdocuments".trim() as String;
      //TODO
      print('serverPrices .. $serverPrices');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  serverPrices;
  }
}



class GetAdress1CPricesDebug implements InAdress1C{
  @override
  String? adress1C() {
    // TODO: implement adress1C
    // TODO: implement adressJboss
    String?   serverPricesDebug;
    try {
      serverPricesDebug    ="http://uat.dsu1.ru:55080/dds/hs/jsonto1ccena/listofdocuments".trim() as String;
      //TODO
      print('serverPricesDebug .. $serverPricesDebug');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  serverPricesDebug;
  }
}










//TODO Pay


class GetAdress1CPay implements InAdress1C{
  @override
  String? adress1C() {
    // TODO: implement adress1C
    // TODO: implement adressJboss
    String?   server1CPay;
    try {
      server1CPay    ="http://uat.dsu1.ru:55080/dds/hs/jsonto1c/listofdocuments".trim() as String;
      //TODO
      print('server1CPay .. $server1CPay');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  server1CPay;
  }
}



class GetAdress1CPayDebug implements InAdress1C{
  @override
  String? adress1C() {
    // TODO: implement adress1C
    // TODO: implement adressJboss
    String?   serverfinalPayDebug;
    try {
      serverfinalPayDebug    =        "http://192.168.3.10/dds_copy/hs/jsonto1c/listofdocuments".trim();// TODO: 18.01.2024 DEBUG as String;
      //TODO
      print('serverfinalPayDebug .. $serverfinalPayDebug');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  serverfinalPayDebug;
  }
}