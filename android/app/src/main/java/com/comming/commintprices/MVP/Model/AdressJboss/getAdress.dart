
import 'InFuture.dart';




class GetAdressJbossDebug implements InAdressJboss {
  //TODO
  @override
  String? adressJboss({required int IdUser, required String JobForServer}) {
    // TODO: implement adressJboss
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+
          "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser.toString()
          + "&" + "VersionData=0" as String;
      jbossserverfinal = jbossserverlink + jbossserverparams;
      //TODO
      print('jbossserverfinal .. $jbossserverfinal');
    } catch (e) {
      print(e);
    }
    return  jbossserverfinal;
  }
}





class GetAdressJboss implements InAdressJboss{
  @override
  String? adressJboss({required int IdUser, required String JobForServer}) {
    // TODO: implement adressJboss
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser.toString()
          + "&" + "VersionData=0" as String;
      jbossserverfinal = jbossserverlink + jbossserverparams;
      //TODO
      print('jbossserverfinal .. $jbossserverfinal');
    } catch (e) {
      print(e);
    }
    return  jbossserverfinal;
  }
}




class GetAdress1CDebug implements InAdress1C{
  @override
  String? adress1C({required int IdUser, required String JobForServer}) {
    // TODO: implement adress1C
// TODO: implement adressJboss
    String?  serverfinal1c;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser.toString()
          + "&" + "VersionData=0" as String;
      serverfinal1c = jbossserverlink + jbossserverparams;
      //TODO
      print('serverfinal1c .. $serverfinal1c');
    } catch (e) {
      print(e);
    }
    return  serverfinal1c;
  }
}





class GetAdress1C implements InAdress1C{
  @override
  String? adress1C({required int IdUser, required String JobForServer}) {
    // TODO: implement adress1C
    // TODO: implement adressJboss
    String?   serverfinal1c;
    try {
      var serverfinal1clink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var serverfinal1cparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser.toString()
          + "&" + "VersionData=0" as String;
      serverfinal1c = serverfinal1clink + serverfinal1cparams;
      //TODO
      print('jbossserverfinal .. $serverfinal1c');
    } catch (e) {
      print(e);
    }
    return  serverfinal1c;
  }
}