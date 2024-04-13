
import 'InFuture.dart';

class getAdressJbossDebug implements InAdressJboss{


  @override
  String? adressJboss() {
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer=Хотим Получить Статус Реальной Работы SQL SERVER"+
          "&" + "IdUser=5"
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


class getAdressJbossReliz implements InAdressJboss{


  @override
  String? adressJboss() {
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer=Хотим Получить Статус Реальной Работы SQL SERVER"+
          "&" + "IdUser=5"
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


class getAdress1C implements InAdressJboss{


  @override
  String? adressJboss() {
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer=Хотим Получить Статус Реальной Работы SQL SERVER"+
          "&" + "IdUser=5"
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
